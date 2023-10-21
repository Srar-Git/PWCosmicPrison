package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.meteor.Meteor;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnMeteorDown implements Listener {
    Meteor meteor = new Meteor();

    @EventHandler
    public void OnMeteorDown(MeteorDownEvent event) {
        Location location = event.getLocation();
        meteor.removeMeteor(location);

    }
}
