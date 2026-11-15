package ru.optivis.mixin;

import net.minecraft.client.render.item.HeldItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.optivis.config.OptiVisConfig;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true, require = 0)
    private void optivis$disableFirstPersonHand(CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.disableFirstPersonHand) {
            ci.cancel();
        }
    }
}
