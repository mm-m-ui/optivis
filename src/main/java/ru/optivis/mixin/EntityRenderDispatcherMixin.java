package ru.optivis.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.optivis.config.OptiVisConfig;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void optivis$entityDistance(Entity entity, net.minecraft.client.render.Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        double maxDist = OptiVisConfig.INSTANCE.entityRenderDistance;
        if (maxDist <= 0.0) {
            cir.setReturnValue(false);
            return;
        }

        double dx = entity.getX() - x;
        double dy = entity.getY() - y;
        double dz = entity.getZ() - z;
        double distSq = dx * dx + dy * dy + dz * dz;
        double maxDistSq = maxDist * maxDist;

        if (distSq > maxDistSq) {
            cir.setReturnValue(false);
        }
    }
}
