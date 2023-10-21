package cn.pixelwar.pwplayer.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.File.YamlStorage;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import cn.pixelwar.pwplayer.Skript.PlayerStat.CoolDown.EffectAddCoolDown;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectReloadConfig extends Effect {


    static {
        Skript.registerEffect(EffectReloadConfig.class, new String[]{
                "reloadconfigofpwplayer",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "reload config: ";
    }

    @Override
    protected void execute(Event event) {
        PWPlayer.getPlugin().reloadConfig();
        YamlStorage yamlStorage = new YamlStorage();
        yamlStorage.loadWarps();


    }
}

