package tornato.lootr.mixin;

import eu.pb4.polymer.rsm.api.RegistrySyncUtils;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.fabric.Lootr;
import noobanidus.mods.lootr.fabric.init.ModStats;
import noobanidus.mods.lootr.fabric.init.ModTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Lootr.class)
public class LootrInitMixin {
    @Inject(method = "onInitialize", at = @At("TAIL"))
    private void foo(CallbackInfo ci) {
        RegistrySyncUtils.setServerEntry(BuiltInRegistries.CREATIVE_MODE_TAB, ModTabs.LOOTR_TAB);
        RegistrySyncUtils.setServerEntry(BuiltInRegistries.LOOT_CONDITION_TYPE, LootrAPI.rl("loot_count"));

        RegistrySyncUtils.setServerEntry(BuiltInRegistries.PARTICLE_TYPE, LootrConstants.Identifiers.UNOPENED_PARTICLE);
        RegistrySyncUtils.setServerEntry(BuiltInRegistries.PARTICLE_TYPE, LootrConstants.Identifiers.REFRESH_PARTICLE);

        // Keep the looted-container stat registered so awardStat(...) works (pot breaking /
        // brushing depend on it), but hide it from vanilla clients' registry sync.
        // CUSTOM_STAT is a Registry<Identifier>, so widen to Registry<?> to force the
        // identifier-based overload instead of an ambiguous match against the value-based one.
        Registry<?> customStatRegistry = BuiltInRegistries.CUSTOM_STAT;
        RegistrySyncUtils.setServerEntry(customStatRegistry, ModStats.LOOTED_LOCATION);
    }
}
