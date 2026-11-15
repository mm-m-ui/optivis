package ru.optivis.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.optivis.config.OptiVisConfig;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true, require = 0)
    private void optivis$disableSky(CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.disableSky) {
            ci.cancel();
        }
    }

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true, require = 0)
    private void optivis$disableClouds(CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.disableClouds) {
            ci.cancel();
        }
    }
}
