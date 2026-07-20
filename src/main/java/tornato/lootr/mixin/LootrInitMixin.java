package tornato.lootr.mixin;

import eu.pb4.polymer.rsm.api.RegistrySyncUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.fabric.Lootr;
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

        RegistrySyncUtils.setServerEntry(BuiltInRegistries.PARTICLE_TYPE, LootrConstants.UNOPENED_PARTICLE);
        RegistrySyncUtils.setServerEntry(BuiltInRegistries.PARTICLE_TYPE, LootrConstants.REFRESH_PARTICLE);
    }
}
