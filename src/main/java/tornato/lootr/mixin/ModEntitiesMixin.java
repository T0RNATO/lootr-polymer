package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.entity.PolymerEntityUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import noobanidus.mods.lootr.fabric.init.ModEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModEntities.class)
public class ModEntitiesMixin {
    @ModifyArg(method = "registerEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerEntities(Object entry) {
        //noinspection unchecked
        PolymerEntityUtils.registerOverlay((EntityType<LootrChestMinecartEntity>) entry, _ -> _ -> EntityTypes.CHEST_MINECART);
        return entry;
    }
}
