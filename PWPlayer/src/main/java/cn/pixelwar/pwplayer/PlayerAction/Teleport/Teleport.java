package cn.pixelwar.pwplayer.PlayerAction.Teleport;

import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDown;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.TimeFormat;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class Teleport {

    public static ConcurrentHashMap<Player, Boolean> couldTP = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Player, TPAData> TPARequest = new ConcurrentHashMap<>();

    //receiver 发起人
    public static ConcurrentHashMap<Player, Player> TPAReceivedRequest = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Location> warpsMap = new ConcurrentHashMap<>();

    public void teleportPlayer(Player player, Location location, boolean bypass) {
        if (couldTP.containsKey(player)) {
            if (!(couldTP.get(player))) {
                player.sendMessage(ChatColorCast.format("&d▸ &f你目前不能进行传送"));
                player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                return;
            }
        }
        if (!bypass) {
            TimeFormat timeFormat = new TimeFormat();
            if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
                if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.TELEPORT)) {
                    int lastTime = CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().get(CoolDownType.TELEPORT);
                    player.sendMessage(ChatColorCast.format("&d▸ &f你需要等待 &c&l" + timeFormat.getTimeFormat(lastTime) + " &f后才能进行传送"));
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                    return;
                }

            }
        }
        couldTP.put(player, false);

        Location orig = player.getLocation();
        Collection<PotionEffect> potionEffects = new ArrayList<>();
        potionEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, 200, 100, false, false, true));
        player.addPotionEffects(potionEffects);
        new BukkitRunnable() {
            //            int i = playerTPDataMap.get(player.getName()).getWaitTime();
            int i = IntDataManager.getIntData(player, IntDataType.TPWAIT);

            @Override
            public void run() {
                Location now = player.getLocation();
                if (orig.getX() != now.getX() || orig.getY() != now.getY() || orig.getZ() != now.getZ()) {
                    cancel();
                    player.sendMessage(ChatColorCast.format("&c▸ 你移动了，传送已取消..."));
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                    couldTP.remove(player);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                        }
                    }.runTask(PWPlayer.getPlugin());
                    return;
                }
                player.sendMessage(ChatColorCast.format("&d▸ &f你将在&b&l" + i + "秒&f后传送至目的地&7(请勿移动)"));
                teleportEffect(player);
                i--;
                if (i <= 0) {

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.teleport(location);
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            CoolDownManager.addPlayerCoolDown(player, CoolDownType.TELEPORT, IntDataManager.getIntData(player, IntDataType.TPCOOLDOWN));
                            couldTP.remove(player);
                            player.playSound(player.getEyeLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1f, 1f);
                        }
                    }.runTask(PWPlayer.getPlugin());
                    cancel();

                }
            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);


    }


    public void teleportEffect(Player player) {
        Location location = player.getLocation().add(0, 1, 0);

        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                location.add(x, y, z);
                location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 0);
                location.subtract(x, y, z);
            }
        }


//        for (int degree = 0; degree < 360; degree++) {
//            double radians = Math.toRadians(degree);
//            double x = Math.cos(radians);
//            double z = Math.sin(radians);
//            location.add(x,1.5,z);
//            location.getWorld().spawnParticle(Particle.FLAME, location, 0);
//            location.subtract(x,1.5,z);
//            degree++;
//        }


//        new BukkitRunnable() {
//            int degree = 0;
//            double up = 0.1;
//            @Override
//            public void run() {
//                double radians = Math.toRadians(degree);
//                double x = Math.cos(radians);
//                double z = Math.sin(radians);
//                location.add(x,up,z);
//                location.getWorld().spawnParticle(Particle.FLAME, location, 0);
//                location.subtract(x,up,z);
//                degree= degree+5;
//                up = up*2;
//                if (up>1){
//                    up = -0.1;
//                }
//                if (up<-1){
//                    up = -0.1;
//                }
//                if (degree>=360){
//                    cancel();
//                }
//            }
//        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 1l);


    }

    public void sendTPRequest(Player sender, Player receiver) {
        TimeFormat timeFormat = new TimeFormat();
        if (CoolDownManager.playerCoolDownMap.containsKey(sender.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(sender.getName()).getOneCoolDownMap().containsKey(CoolDownType.TELEPORT)) {
                int lastTime = CoolDownManager.playerCoolDownMap.get(sender.getName()).getOneCoolDownMap().get(CoolDownType.TELEPORT);
                sender.sendMessage(ChatColorCast.format("&d▸ &f你需要等待 &c&l" + timeFormat.getTimeFormat(lastTime) + " &f后才能进行传送"));
                sender.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                return;
            }
        }
        if (sender.equals(receiver)) {
            sender.sendMessage(ChatColorCast.format("&c▸ &f你输入的玩家是你自己!"));
            sender.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return;
        }
        if (TPARequest.containsKey(sender)) {
            sender.sendMessage(ChatColorCast.format("&c▸ &f你已经创建了传送请求，请等待过期再创建下一个!"));
            sender.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return;
        }
        if (!(receiver.isOnline())) {
            sender.sendMessage(ChatColorCast.format("&c▸ &f你输入的玩家不在线"));
            sender.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return;
        }
        sender.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
        sender.sendMessage("");
        sender.sendMessage(ChatColorCast.format("&d▸ &f你已经发送了传送请求，&e&l" + receiver.getName() + "&f有&b&l60秒&f同意时间!"));
        sender.sendMessage("");
        TPAData tpaData = new TPAData(receiver, 60);
        TPARequest.put(sender, tpaData);
        TPAReceivedRequest.put(receiver, sender);
        receiver.playSound(sender.getEyeLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
        receiver.sendMessage("");
        receiver.sendMessage(ChatColorCast.format("&c▸ &f你收到来自&a&l" + sender.getName() + "&f的传送请求"));
        receiver.sendMessage(ChatColorCast.format("&c&l 注意!&cPVP随处开放,请勿随意相信他人!"));
        receiver.sendMessage(ChatColorCast.format("&f 你可以输入&b&l/tpaccept&f来同意请求"));
        receiver.sendMessage("");
    }

    public void acceptTP(Player receiver) {
        if (!(TPAReceivedRequest.containsKey(receiver))) {
            receiver.sendMessage(ChatColorCast.format("&c▸ &f你没有收到任何请求!"));
            receiver.playSound(receiver.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return;
        }
        Player sender = TPAReceivedRequest.get(receiver);
        if (!(sender.isOnline())) {
            receiver.sendMessage(ChatColorCast.format("&c▸ &f该玩家已离线!"));
            receiver.playSound(receiver.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            TPAReceivedRequest.remove(receiver);
            TPARequest.remove(sender);
            return;
        }
        teleportPlayer(sender, receiver.getLocation(), false);
        receiver.sendMessage(ChatColorCast.format("&d▸ &f你同意了&b&l" + sender.getName() + "&f的传送请求!"));
        receiver.playSound(receiver.getEyeLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
        sender.playSound(receiver.getEyeLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
        sender.sendMessage(ChatColorCast.format("&d▸ &b&l" + receiver.getName() + "&f同意了你的传送请求!"));
        TPAReceivedRequest.remove(receiver);
        TPARequest.remove(sender);

    }

    public void tpaTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {

                TPARequest.forEach((key, value) -> {

                    if (value.getTime() <= 0) {
                        key.sendMessage(ChatColorCast.format("&c▸ &f你的传送请求已过期!"));
                        key.playSound(key.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
                        TPAReceivedRequest.remove(value.getReceiver());
                        TPARequest.remove(key);
                    } else {
                        TPAData tpaData = new TPAData(key, value.getTime() - 1);
                        TPARequest.put(key, tpaData);
                    }

                });

            }

        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);
    }

}
