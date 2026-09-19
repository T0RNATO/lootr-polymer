package tornato.lootr;

import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class LootrInventoryRenderer implements BlockWithElementHolder {
    @Override
    public boolean tickElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        return true;
    }

    private void createDisplays(ElementHolder holder, float offset, Predicate<ServerPlayer> predicate, BlockState state) {
        float scale = 0.2f;
        float depth = 0.02f;

        var a = new Vector3f(scale, scale, depth);
        var b = new Vector3f(depth, scale, scale);

        IntStream.range(0, 4).forEach(i -> {
            var bd = new BlockDisplayElement();
            bd.setBlockState(state);
            bd.setVisibilityPredicate(predicate);
            bd.setScale(i % 2 == 0 ? a : b);
            var dir = Direction.from2DDataValue(i);
            var dirVec = dir.getUnitVec3().scale(0.5 + depth/2 - offset);
            // 0.0001 moves them out of the block so lighting works
            var worldspaceAlignment = dir.getAxis().getPositive().getUnitVec3().scale(scale/2 - depth/2 + 0.0001);
            bd.setOffset(dirVec.add(worldspaceAlignment).add(-scale/2, -0.4, -scale/2));
            holder.addElement(bd);
        });
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        if (!ConfigManager.config.enabled()) return null;

        var openedState = ConfigManager.config.opened().value().defaultBlockState();
        var unopenedState = ConfigManager.config.unopened().value().defaultBlockState();

        float offset = initialBlockState.is(ConventionalBlockTags.CHESTS) ? 1/16f : 0;

        Predicate<ServerPlayer> opened = player -> world.getBlockEntity(pos) instanceof ILootrBlockEntity lootr && lootr.hasServerOpened(player);
        Predicate<ServerPlayer> unopened = player -> !opened.test(player);

        var holder = new LootrElementHolder();
        createDisplays(holder, offset, opened, openedState);
        createDisplays(holder, offset, unopened, unopenedState);

        return holder;
    }
}
