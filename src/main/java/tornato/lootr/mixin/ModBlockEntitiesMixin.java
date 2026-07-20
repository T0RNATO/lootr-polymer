package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

@Mixin(ModBlockEntities.class)
public class ModBlockEntitiesMixin {
    @Unique
    // no need to map other BEs, they're all just basic 36-slot containers so can all just be barrels.
    private final static Map<BlockEntityType<?>, BlockEntityType<?>> polymer$BLOCK_ENTITIES = Map.ofEntries(
            Map.entry(ModBlockEntities.BRUSHABLE_BLOCK, BlockEntityTypes.BRUSHABLE_BLOCK),
            Map.entry(ModBlockEntities.DECORATED_POT, BlockEntityTypes.DECORATED_POT)
    );

    @ModifyArg(method = "registerBlockEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerBlockEntities(Object entry) {
        PolymerBlockUtils.registerBlockEntity((BlockEntityType<?>) entry, (_, _) ->
                polymer$BLOCK_ENTITIES.getOrDefault(entry, BlockEntityTypes.BARREL));
        return entry;
    }
}
