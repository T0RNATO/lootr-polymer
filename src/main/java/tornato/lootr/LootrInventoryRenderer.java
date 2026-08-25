package tornato.lootr;

import eu.pb4.polymer.virtualentity.api.BlockWithElementHolder;
import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.BlockDisplayElement;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Predicate;

public class LootrInventoryRenderer implements BlockWithElementHolder {
    @Override
    public boolean tickElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        return true;
    }

    @Override
    public @Nullable ElementHolder createElementHolder(ServerLevel world, BlockPos pos, BlockState initialBlockState) {
        if (!ConfigManager.config.enabled()) return null;

        var oldState = ConfigManager.config.oldBlock().value().defaultBlockState();
        var newState = ConfigManager.config.newBlock().value().defaultBlockState();
        float depth = (initialBlockState.is(ConventionalBlockTags.CHESTS) ? 14 / 16f : 1f) + 1 / 24f;

        float scale = 0.2f;
        double shortOffset = -scale / 2;
        double longOffset = -depth / 2;

        Predicate<ServerPlayer> isNew = player -> {
            if (world.getBlockEntity(pos) instanceof ILootrBlockEntity lootr) {
                return !lootr.hasServerOpened(player);
            }
            return true;
        };
        Predicate<ServerPlayer> isOld = player -> !isNew.test(player);

        var old1 = new BlockDisplayElement();
        old1.setBlockState(oldState);
        old1.setScale(new Vector3f(scale, scale, depth));
        old1.setOffset(new Vec3(shortOffset, -0.4, longOffset));
        old1.setVisibilityPredicate(isOld);

        var old2 = new BlockDisplayElement();
        old2.setBlockState(oldState);
        old2.setScale(new Vector3f(depth, scale, scale));
        old2.setOffset(new Vec3(longOffset, -0.4, shortOffset));
        old2.setVisibilityPredicate(isOld);

        var new1 = new BlockDisplayElement();
        new1.setBlockState(newState);
        new1.setScale(old1.getScale());
        new1.setOffset(old1.getOffset());
        new1.setVisibilityPredicate(isNew);

        var new2 = new BlockDisplayElement();
        new2.setBlockState(newState);
        new2.setScale(old2.getScale());
        new2.setOffset(old2.getOffset());
        new2.setVisibilityPredicate(isNew);

        var holder = new LootrElementHolder();
        holder.addElement(old1);
        holder.addElement(old2);
        holder.addElement(new1);
        holder.addElement(new2);

        return holder;
    }
}
