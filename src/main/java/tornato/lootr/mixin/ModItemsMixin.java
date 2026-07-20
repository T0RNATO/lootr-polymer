package tornato.lootr.mixin;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import noobanidus.mods.lootr.fabric.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ModItems.class)
public class ModItemsMixin {
    @ModifyArg(method = "registerItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;register(Lnet/minecraft/core/Registry;Lnet/minecraft/resources/Identifier;Ljava/lang/Object;)Ljava/lang/Object;"), index = 2)
    private static Object registerPolymerItems(Object entry) {
        PolymerItemUtils.registerOverlay((Item) entry, (_, _) -> Items.BARRIER);
        return entry;
    }
}
