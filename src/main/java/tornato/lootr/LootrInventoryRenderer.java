package tornato.lootr;

import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class LootrInventoryRenderer implements BlockWithElementHolder {
    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        if (!ConfigManager.config.enabled()) return null;

        var state = ConfigManager.config.block().value().defaultBlockState();
        float depth = (initialBlockState.is(ConventionalBlockTags.CHESTS) ? 14 / 16f : 1f) + 1 / 24f;

        float scale = 0.2f;
        double shortOffset = -scale / 2;
        double longOffset = -depth / 2;

        var display1 = new BlockDisplayElement();
        display1.setBlockState(state);
        display1.setScale(new Vector3f(scale, scale, depth));
        display1.setOffset(new Vec3(shortOffset, -0.4, longOffset));

        var display2 = new BlockDisplayElement();
        display2.setBlockState(state);
        display2.setScale(new Vector3f(depth, scale, scale));
        display2.setOffset(new Vec3(longOffset, -0.4, shortOffset));

        var holder = new ElementHolder();
        holder.addElement(display1);
        holder.addElement(display2);

        return holder;
    }
}
