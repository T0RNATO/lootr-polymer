package tornato.lootr.mixin;

import eu.pb4.polymer.rsm.api.RegistrySyncUtils;
import net.minecraft.registry.Registries;
import noobanidus.mods.lootr.fabric.init.ModTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModTabs.class)
public class ModTabsMixin {
    @ModifyArg(method = "registerTabs", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object markServerOnly(Object entry) {
        //noinspection unchecked,rawtypes
        RegistrySyncUtils.setServerEntry((net.minecraft.registry.Registry) Registries.ITEM_GROUP, entry);
        return entry;
    }
}
