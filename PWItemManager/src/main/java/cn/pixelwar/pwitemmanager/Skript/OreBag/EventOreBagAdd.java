package cn.pixelwar.pwitemmanager.Skript.OreBag;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import cn.pixelwar.pwitemmanager.OreBag.OreBagAddEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventOreBagAdd extends SkriptEvent {

    static {
        Skript.registerEvent("Level UP", EventOreBagAdd.class, OreBagAddEvent.class, "orebagadd");
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
        return "pixelwar ore bag added event";
    }
}
