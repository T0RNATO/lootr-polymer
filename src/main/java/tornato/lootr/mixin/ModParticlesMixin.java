package tornato.lootr.mixin;

import eu.pb4.polymer.rsm.api.RegistrySyncUtils;
import net.minecraft.registry.Registries;
import noobanidus.mods.lootr.fabric.init.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModParticles.class)
public class ModParticlesMixin {
    @ModifyArg(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object markServerOnly(Object entry) {
        //noinspection unchecked,rawtypes
        RegistrySyncUtils.setServerEntry((net.minecraft.registry.Registry) Registries.PARTICLE_TYPE, entry);
        return entry;
    }
}
