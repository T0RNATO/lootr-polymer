package tornato.lootr;

import eu.pb4.polymer.virtualentity.api.ElementHolder;
import eu.pb4.polymer.virtualentity.api.elements.AbstractElement;
import eu.pb4.polymer.virtualentity.impl.SafeBundler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class LootrElementHolder extends ElementHolder {
    private final HashMap<AbstractElement, List<UUID>> trackedPlayers = new HashMap<>();

    @Override
    protected void onTick() {
        super.onTick();
        if (getAttachment() == null) return;

        ServerLevel world = getAttachment().getWorld();
        BlockEntity be = world.getBlockEntity(BlockPos.containing(getAttachment().getPos()));
        if (!(be instanceof ILootrBlockEntity)) return;

        getWatchingPlayers().forEach(listener -> {
            getElements().forEach(e -> {
                if (e instanceof AbstractElement element) {
                    ServerPlayer player = listener.getPlayer();
                    UUID uuid = player.getUUID();
                    var packets = new SafeBundler(listener::send);
                    var list = trackedPlayers.computeIfAbsent(element, _ -> new ArrayList<>());
                    if (list.contains(uuid) && !element.getVisibilityPredicate().test(player)) {
                        list.remove(uuid);
                        element.stopWatching(player, packets);
                    } else if (!list.contains(uuid) && element.getVisibilityPredicate().test(player)) {
                        list.add(uuid);
                        element.startWatching(player, packets);
                    }
                    packets.finish();
                }
            });
        });
    }
}
