package cn.pixelwar.pwlevel.skript.LevelUp;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwlevel.CustomEvents.LevelUpEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventLevelUp extends SkriptEvent {

    static {
        Skript.registerEvent("Level UP", EventLevelUp.class, LevelUpEvent.class, "pwlevelup");
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {

        return true;
    }

    @Override
    public boolean check(Event e) {
        //Explaining still
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "pixelwar levelup event";
    }
}
