package tornato.lootr;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import noobanidus.mods.lootr.fabric.init.ModBlocks;

import java.util.Map;

public class LootrPolymer implements ModInitializer {
    public static final String ID = "lootr_polymer";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(ID, path);
    }

    public static final Map<Block, Block> BLOCKS = Map.ofEntries(
            Map.entry(ModBlocks.CHEST, Blocks.CHEST),
            Map.entry(ModBlocks.TRAPPED_CHEST, Blocks.TRAPPED_CHEST),

            Map.entry(ModBlocks.BARREL, Blocks.BARREL),
            Map.entry(ModBlocks.SHULKER_BOX, Blocks.SHULKER_BOX),
            Map.entry(ModBlocks.DECORATED_POT, Blocks.DECORATED_POT),

            Map.entry(ModBlocks.COPPER_CHEST, Blocks.COPPER_CHEST.weathering().unaffected()),
            Map.entry(ModBlocks.EXPOSED_COPPER_CHEST, Blocks.COPPER_CHEST.weathering().exposed()),
            Map.entry(ModBlocks.WEATHERED_COPPER_CHEST, Blocks.COPPER_CHEST.weathering().weathered()),
            Map.entry(ModBlocks.OXIDIZED_COPPER_CHEST, Blocks.COPPER_CHEST.weathering().oxidized()),

            Map.entry(ModBlocks.SUSPICIOUS_GRAVEL, Blocks.SUSPICIOUS_GRAVEL),
            Map.entry(ModBlocks.SUSPICIOUS_SAND, Blocks.SUSPICIOUS_SAND)
    );

    @Override
    public void onInitialize() {
        FabricLoader.getInstance().getModContainer(ID).ifPresent(modContainer ->
                ResourceLoader.registerBuiltinPack(id("lootr_polymer"), modContainer, Component.literal("Lootr Polymer"), PackActivationType.ALWAYS_ENABLED));
        ConfigManager.loadConfig();
    }
}
