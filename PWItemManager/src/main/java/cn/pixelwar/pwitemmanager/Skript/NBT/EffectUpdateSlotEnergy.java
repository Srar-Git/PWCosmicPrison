package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdateSlotEnergy extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> xpin;
    private Expression<Number> needxpin;
    private Expression<Number> slotin;

    static {
        Skript.registerEffect(EffectUpdateSlotEnergy.class, new String[]{
                "updateslotenergy %player% %number% %number% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.xpin = (Expression<Number>) expressions[1];
        this.needxpin = (Expression<Number>) expressions[2];
        this.slotin = (Expression<Number>) expressions[3];

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update nbt of player's tool: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        Player player = playerin.getSingle(e);
        int xp = xpin.getSingle(e).intValue();
        int needxp = needxpin.getSingle(e).intValue();
        int slot = slotin.getSingle(e).intValue();
        updateSlotLore.updateSlotEnergy(player, xp, needxp, slot);
    }


}