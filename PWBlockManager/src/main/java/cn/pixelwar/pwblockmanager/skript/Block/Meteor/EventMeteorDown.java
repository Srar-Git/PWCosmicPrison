package cn.pixelwar.pwblockmanager.skript.Block.Meteor;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventMeteorDown extends SkriptEvent {

    static {
        Skript.registerEvent("Block boom", EventMeteorDown.class, MeteorDownEvent.class, "meteordown");
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
        return "EventMeteorDown event";
    }
}
