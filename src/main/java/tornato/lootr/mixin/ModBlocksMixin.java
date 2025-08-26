package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.block.PolymerBlockUtils;
import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tornato.lootr.LootrInventoryRenderer;
import tornato.lootr.LootrPolymer;

@Mixin(ModBlocks.class)
public class ModBlocksMixin {
    @ModifyArg(method = "registerBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/util/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerBlocks(Object entry) {
        PolymerBlockUtils.registerOverlay((Block) entry, (state, context) ->
                LootrPolymer.BLOCKS.getOrDefault(entry, Blocks.STRUCTURE_BLOCK).getStateWithProperties(state)
        );
        BlockWithElementHolder.registerOverlay((Block) entry, new LootrInventoryRenderer());
        return entry;
    }
}
