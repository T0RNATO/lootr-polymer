package tornato.lootr;

import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager {
    public record Config(boolean enabled, Holder<Block> block) {}
    public static final Config config;

    private static final Codec<Config> codec = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("lootr_indicator").forGetter(Config::enabled),
            BuiltInRegistries.BLOCK.holderByNameCodec().fieldOf("lootr_indicator_block").forGetter(Config::block)
    ).apply(instance, Config::new));

    interface ThrowingSupplier<T> {
        T get() throws Exception;
    }

    private static <T> T unchecked(ThrowingSupplier<T> s) {
        try {
            return s.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadConfig() {}

    // random variable spam to appease java compiler
    static {
        Config tmp;
        init: {
            var configFile = FabricLoader.getInstance().getConfigDir().resolve("lootr_polymer.json").toFile();
            if (!unchecked(configFile::createNewFile)) {
                try {
                    tmp = codec.decode(JsonOps.INSTANCE, JsonParser.parseReader(unchecked(() -> new FileReader(configFile)))).getOrThrow().getFirst();
                    break init;
                } catch (Exception ignored) {}
            }

            // if the config file doesn't exist, or has errors, create a default config
            Config conf = new Config(true, Blocks.LODESTONE.builtInRegistryHolder());

            unchecked(() -> {
                var writer = new FileWriter(configFile);
                writer.write(codec.encodeStart(JsonOps.INSTANCE, conf).getOrThrow().toString());
                writer.close();
                return null;
            });

            tmp = conf;
        }
        config = tmp;
    }
}
