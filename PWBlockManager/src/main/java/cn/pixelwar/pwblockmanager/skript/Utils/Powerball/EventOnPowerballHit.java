package cn.pixelwar.pwblockmanager.skript.Utils.Powerball;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventOnPowerballHit extends SkriptEvent {

    static {
        Skript.registerEvent("Powerball hit blocks", EventOnPowerballHit.class, PowerballHitBlockEvent.class, "powerballhit");
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
        return "Powerball hit event";
    }


}
