package tornato.lootr.mixin;

import noobanidus.mods.lootr.fabric.init.ModStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ModStats.class)
public class ModStatsMixin {
    /**
     * @author TorNato
     * @reason Cancelling registration of statistic. Needless bloat and idk how to make the client ignore it.
     */
    @Overwrite(remap = false)
    public static void registerStats() {

    }
}
