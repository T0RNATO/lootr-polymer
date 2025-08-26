package tornato.lootr.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import noobanidus.mods.lootr.common.impl.DefaultLootrAPIImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DefaultLootrAPIImpl.class)
public class LootrAPIImplMixin {
    @ModifyExpressionValue(method = "handleProviderOpen", at = @At(value = "INVOKE", target = "Lnoobanidus/mods/lootr/common/api/data/ILootrInfoProvider;hasOpened(Lnet/minecraft/entity/player/PlayerEntity;)Z"))
    private boolean disableStatUpdates(boolean original) {
        return true;
    }
}
