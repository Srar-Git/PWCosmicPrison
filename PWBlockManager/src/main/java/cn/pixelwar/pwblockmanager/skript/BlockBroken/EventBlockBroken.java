package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwblockmanager.customevents.BlockBrokenEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventBlockBroken extends SkriptEvent {

    static {
        Skript.registerEvent("Block boom", EventBlockBroken.class, BlockBrokenEvent.class, "blockbroken");
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
        return "Block broken event";
    }
}
