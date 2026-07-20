package tornato.lootr.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import noobanidus.mods.lootr.common.api.interfaces.lootr.ILootrAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ILootrAPI.class)
public interface LootrAPIImplMixin {
    @SuppressWarnings("SameReturnValue")
    @ModifyExpressionValue(method = "handleInstanceOpen(Lnoobanidus/mods/lootr/common/api/data/ILootrContainerInstance;Lnet/minecraft/server/level/ServerPlayer;Lnoobanidus/mods/lootr/common/api/interfaces/container/IMenuBuilder;)V", at = @At(value = "INVOKE", target = "Lnoobanidus/mods/lootr/common/api/data/ILootrContainerInstance;hasServerOpened(Lnet/minecraft/world/entity/player/Player;)Z"))
    private boolean foo(boolean original) {
        return true;
    }
}
