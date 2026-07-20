package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tornato.lootr.LootrInventoryRenderer;
import tornato.lootr.LootrPolymer;

@Mixin(ModBlocks.class)
public class ModBlocksMixin {
    @ModifyArg(method = "registerBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerBlocks(Object entry) {
        PolymerBlockUtils.registerOverlay((Block) entry, (state, _) ->
                LootrPolymer.BLOCKS.getOrDefault(entry, Blocks.STRUCTURE_BLOCK).withPropertiesOf(state)
        );
        BlockWithElementHolder.registerOverlay((Block) entry, new LootrInventoryRenderer());
        return entry;
    }
}
