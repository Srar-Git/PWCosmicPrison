package cn.pixelwar.pwitemmanager.Skript.Utils;//package cn.pixelwar.pixelwaritemmanager.Skript.Utils;
//
//import ch.njol.skript.Skript;
//import ch.njol.skript.lang.Effect;
//import ch.njol.skript.lang.Expression;
//import ch.njol.skript.lang.SkriptParser;
//import ch.njol.util.Kleenean;
//import cn.pixelwar.pixelwaritemmanager.NBT.SetItemNBT;
//import cn.pixelwar.pixelwaritemmanager.Utils.GiveItems;
//import com.sun.istack.internal.Nullable;
//import org.bukkit.entity.Player;
//import org.bukkit.event.Event;
//
//public class EffectGiveCustomItem extends Effect {
//    private Expression<Player> playerin;
//    private Expression<String> itemin;
//    private Expression<Number> amountin;
//
//    static {
//        Skript.registerEffect(EffectGiveCustomItem.class, new String[]{
//                "giveitem %player% %string% %number%"
//        });
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
//
//        this.playerin = (Expression<Player>) expressions[0];
//        this.moneyin = (Expression<Number>) expressions[1];
//        return true;
//    }
//
//    @Override
//    public String toString(@Nullable Event event, boolean debug) {
//        return "give player banknote: " + playerin.toString(event, debug);
//    }
//
//    @Override
//    protected void execute(Event e) {
//        GiveItems giveItems = new GiveItems();
//        SetItemNBT setItemNBT = new SetItemNBT();
//        Player player = playerin.getSingle(e);
//        double money = moneyin.getSingle(e).doubleValue();
//        giveItems.giveBanknote(player, money);
//
//    }
//
//
//}
