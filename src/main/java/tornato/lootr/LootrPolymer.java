package tornato.lootr;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import noobanidus.mods.lootr.fabric.init.ModBlocks;

import java.util.Map;

public class LootrPolymer implements ModInitializer {
    public static final String ID = "lootr_polymer";

    public static Identifier id(String path) {
        return Identifier.of(ID, path);
    }

    public static Map<Block, Block> BLOCKS = Map.of(
            ModBlocks.CHEST, Blocks.CHEST,
            ModBlocks.BARREL, Blocks.BARREL,
            ModBlocks.TRAPPED_CHEST, Blocks.TRAPPED_CHEST,
            ModBlocks.SHULKER, Blocks.SHULKER_BOX
    );

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(ID).ifPresent(modContainer ->
                ResourceManagerHelper.registerBuiltinResourcePack(id("lootr_polymer"), modContainer, Text.of("Lootr Polymer"), ResourcePackActivationType.DEFAULT_ENABLED));
    }
}
