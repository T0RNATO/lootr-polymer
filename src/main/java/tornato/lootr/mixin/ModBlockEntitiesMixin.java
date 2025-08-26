package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.block.entity.BlockEntityType;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModBlockEntities.class)
public class ModBlockEntitiesMixin {
    @ModifyArg(method = "registerBlockEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerBlockEntities(Object entry) {
        PolymerBlockUtils.registerBlockEntity((BlockEntityType<?>) entry, (object, context) -> BlockEntityType.BARREL);
        return entry;
    }
}
