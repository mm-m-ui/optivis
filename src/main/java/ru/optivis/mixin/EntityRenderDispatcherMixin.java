package ru.optivis.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.optivis.config.OptiVisConfig;

@Mixin(EntityRenderManager.class)
public class EntityRenderDispatcherMixin {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void optivis$entityDistance(Entity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        double maxDist = OptiVisConfig.INSTANCE.entityRenderDistance;
        if (maxDist <= 0.0) {
            cir.setReturnValue(false);
            return;
        }

        if (maxDist * maxDist < entity.squaredDistanceTo(x, y, z)) {
            cir.setReturnValue(false);
        }
    }
}
