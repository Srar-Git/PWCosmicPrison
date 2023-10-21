package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketMeteor;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Meteor {
    Random random = new Random();
    PacketMeteor packetMeteor = new PacketMeteor();
    MeteorDataManager meteorDataManager = new MeteorDataManager();
    MeteorRegionDataManager meteorRegionDataManager = new MeteorRegionDataManager();
    MeteorFile meteorFile = new MeteorFile();

    public void spawnMeteor(Location location) {
        packetMeteor.createMeteor(location);
    }

    public void removeMeteor(Location location) {
        meteorDataManager.removeMeteorData(location);
        meteorFile.removeMeteorData(location);
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().setType(location, Material.AIR);
            }
        }.runTask(PWBlockManager.getPlugin());
        location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location.clone().add(0.5, 0.5, 0.5), 115, 0.3, 0.3, 0.3, 0);
        location.getWorld().playSound(location, Sound.ENTITY_WITHER_DEATH, 1.0F, 0.1F);
        if (location.getWorld().equals(Bukkit.getWorld("world"))) {
            MeteorDataManager.setTotalOfWorld(MeteorDataManager.getTotalOfWorld() - 1);
        } else {
            MeteorDataManager.setTotalOfNew(MeteorDataManager.getTotalOfNew() - 1);
        }
    }

    public void randomSpawnMeteor(World world) {
//        Bukkit.broadcastMessage("world: "+MeteorDataManager.getTotalOfWorld()+"/"+PWBlockManager.config.getInt("meteor.total.world"));
//        Bukkit.broadcastMessage("new: "+MeteorDataManager.getTotalOfNew()+"/"+PWBlockManager.config.getInt("meteor.total.new"));
        if (world.equals(Bukkit.getWorld("world"))) {
            int totalWorld = PWBlockManager.config.getInt("meteor.total.world");
            if (totalWorld <= MeteorDataManager.getTotalOfWorld()) {
                return;
            }
        }
        if (world.equals(Bukkit.getWorld("new"))) {
            int totalNew = PWBlockManager.config.getInt("meteor.total.new");
            if (totalNew <= MeteorDataManager.getTotalOfNew()) {
                return;
            }
        }
        String region = meteorRegionDataManager.getRandomRegion(world);
        int minx = MeteorRegionDataManager.getMeteorRegionDataMap().get(region).getMinx();
        int maxx = MeteorRegionDataManager.getMeteorRegionDataMap().get(region).getMaxx();
        int minz = MeteorRegionDataManager.getMeteorRegionDataMap().get(region).getMinz();
        int maxz = MeteorRegionDataManager.getMeteorRegionDataMap().get(region).getMaxz();
        int x = 0, z = 0;
        try {
            x = random.nextInt(maxx - minx + 1) + minx;
            z = random.nextInt(maxz - minz + 1) + minz;

        } catch (IllegalArgumentException e) {
        }

        Location orig = new Location(world, x, 251, z);
        for (int i = 0; i < 256; i++) {
            orig.subtract(0, 1, 0);
            if (world.getBlockAt(orig).getType().equals(Material.AIR)) {
                continue;
            }
            spawnMeteor(orig.add(0, 1, 0));
//            Bukkit.broadcastMessage(orig+"");
            return;
        }


    }


}
