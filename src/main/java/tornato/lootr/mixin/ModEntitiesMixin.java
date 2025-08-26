package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import net.minecraft.entity.EntityType;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import noobanidus.mods.lootr.fabric.init.ModEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModEntities.class)
public class ModEntitiesMixin {
    @ModifyArg(method = "registerEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2, remap = false)
    private static Object registerPolymerEntities(Object entry) {
        //noinspection unchecked
        PolymerEntityUtils.registerOverlay((EntityType<LootrChestMinecartEntity>) entry, entity -> context -> EntityType.CHEST_MINECART);
        return entry;
    }
}
