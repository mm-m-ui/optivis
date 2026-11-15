package ru.optivis.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.optivis.config.OptiVisConfig;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void optivis$disableSky(Matrix4f modelViewMatrix, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean thickFog, Runnable fogCallback, CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.disableSky || OptiVisConfig.INSTANCE.disableSun || OptiVisConfig.INSTANCE.disableMoon || OptiVisConfig.INSTANCE.disableStars) {
            // Полное отключение неба (включая солнце/луну/звёзды)
            if (OptiVisConfig.INSTANCE.disableSky) {
                ci.cancel();
                return;
            }
        }
    }

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void optivis$disableClouds(MatrixStack matrices, Matrix4f matrix4f, Matrix4f matrix4f2, float tickDelta, double cameraX, double cameraY, double cameraZ, CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.disableClouds) {
            ci.cancel();
        }
    }
}
