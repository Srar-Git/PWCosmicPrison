package cn.pixelwar.pwlevel.Utils;//package cn.pixelwar.pwlevel.Utils;
//
//
//import net.minecraft.network.protocol.game.PacketPlayOutNamedSoundEffect;
//import net.minecraft.resources.MinecraftKey;
//import net.minecraft.sounds.SoundCategory;
//import net.minecraft.sounds.SoundEffect;
//import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
//import org.bukkit.entity.Player;
//
//public class PacketSound {
//    public static void PlayPacketSound(Player player, String sound, double x, double y, double z, float volume, float pitch){
//        MinecraftKey key = new MinecraftKey(sound);
//        SoundEffect effect = new SoundEffect(key);
//        PacketPlayOutNamedSoundEffect soundpacket = new PacketPlayOutNamedSoundEffect(effect, SoundCategory.PLAYERS , x, y, z, volume, pitch);
//        ((CraftPlayer) player).getHandle().connection.send(soundpacket);
//    }
//}
