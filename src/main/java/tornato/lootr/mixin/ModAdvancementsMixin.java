package tornato.lootr.mixin;

import net.minecraft.resources.Identifier;
import noobanidus.mods.lootr.fabric.init.ModAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModAdvancements.class)
public class ModAdvancementsMixin {
    @ModifyArg(method = "registerAdvancements", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 1)
    private static Identifier markServerOnly(Identifier location) {
//        RegistrySyncUtils.setServerEntry(BuiltInRegistries.TRIGGER_TYPES, location);
        return location;
    }
}
