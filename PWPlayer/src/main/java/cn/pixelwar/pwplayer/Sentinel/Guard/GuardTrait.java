package cn.pixelwar.pwplayer.Sentinel.Guard;

import cn.pixelwar.pwplayer.PWPlayer;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

@TraitName("guard")
public class GuardTrait extends Trait {

    @Persist
    private Location fixedLoc;

    public Location getFixedLoc() {
        return fixedLoc;
    }

    public GuardTrait() {
        super("guard");
        plugin = JavaPlugin.getPlugin(PWPlayer.class);
    }

    PWPlayer plugin = null;

    boolean SomeSetting = false;

    // see the 'Persistence API' section
    @Persist("guard")
    boolean automaticallyPersistedSetting = false;

    // Here you should load up any values you have previously saved (optional).
    // This does NOT get called when applying the trait for the first time, only loading onto an existing npc at server start.
    // This is called AFTER onAttach so you can load defaults in onAttach and they will be overridden here.
    // This is called BEFORE onSpawn, npc.getEntity() will return null.
    public void load(DataKey key) {
        String world = key.getString("fixedLocation.world");
        double x = key.getDouble("fixedLocation.x");
        double y = key.getDouble("fixedLocation.y");
        double z = key.getDouble("fixedLocation.z");
        double yaw = key.getDouble("fixedLocation.yaw");
        Location location = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, 0);
        this.setFixedLoc(location);
    }

    public void setFixedLoc(Location fixedLoc) {
        this.fixedLoc = fixedLoc;
    }

    // Save settings for this NPC (optional). These values will be persisted to the Citizens saves file
    public void save(DataKey key) {
        key.setString("fixedLocation.world", fixedLoc.getWorld().getName());
        key.setDouble("fixedLocation.x", fixedLoc.getX());
        key.setDouble("fixedLocation.y", fixedLoc.getY());
        key.setDouble("fixedLocation.z", fixedLoc.getZ());
        key.setDouble("fixedLocation.yaw", fixedLoc.getYaw());
        key.setDouble("fixedLocation.pitch", 0);
    }


}
