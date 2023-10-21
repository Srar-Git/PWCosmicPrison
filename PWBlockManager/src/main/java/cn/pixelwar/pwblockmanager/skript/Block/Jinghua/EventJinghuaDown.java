package cn.pixelwar.pwblockmanager.skript.Block.Jinghua;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwblockmanager.customevents.JinghuaDownEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventJinghuaDown extends SkriptEvent {

    static {
        Skript.registerEvent("Block boom", EventJinghuaDown.class, JinghuaDownEvent.class, "jinghuadown");
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
        return "EventJinghuaDown event";
    }
}
