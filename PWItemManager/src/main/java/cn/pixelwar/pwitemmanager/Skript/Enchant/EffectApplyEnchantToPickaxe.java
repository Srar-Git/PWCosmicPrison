package cn.pixelwar.pwitemmanager.Skript.Enchant;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToPickaxe;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectApplyEnchantToPickaxe extends Effect {
    private Expression<Player> playerin;
    private Expression<String> enchantEnglishNamein;
    private Expression<Number> levelin;
    private Expression<Number> successin;

    static {
        Skript.registerEffect(EffectApplyEnchantToPickaxe.class, new String[]{
                "applyenchantpickaxeinhand %player% %string% %number% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.enchantEnglishNamein = (Expression<String>) expressions[1];
        this.levelin = (Expression<Number>) expressions[2];
        this.successin = (Expression<Number>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add enchants to player's tool: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        ApplyEnchantToPickaxe applyEnchant = new ApplyEnchantToPickaxe();
        Player player = playerin.getSingle(e);
        String enchantEnglishName = enchantEnglishNamein.getSingle(e);
        int level = levelin.getSingle(e).intValue();
        int success = successin.getSingle(e).intValue();
        applyEnchant.applyHandItemEnchantToPickaxe(player, enchantEnglishName, level,success, false);
    }


}
