package ru.optivis.mixin;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.optivis.config.OptiVisConfig;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {

    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;", at = @At("HEAD"), cancellable = true)
    private void optivis$cancelParticle(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfoReturnable<Particle> cir) {
        if (OptiVisConfig.INSTANCE.disableAllParticles) {
            cir.setReturnValue(null);
            return;
        }

        ParticleType<?> type = parameters.getType();

        if (OptiVisConfig.INSTANCE.disableExplosionParticles) {
            if (type == ParticleTypes.EXPLOSION || type == ParticleTypes.EXPLOSION_EMITTER || type == ParticleTypes.FLASH) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableSmokeParticles) {
            if (type == ParticleTypes.SMOKE || type == ParticleTypes.LARGE_SMOKE || type == ParticleTypes.CAMPFIRE_COSY_SMOKE || type == ParticleTypes.CAMPFIRE_SIGNAL_SMOKE || type == ParticleTypes.WHITE_SMOKE) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disablePortalParticles) {
            if (type == ParticleTypes.PORTAL || type == ParticleTypes.REVERSE_PORTAL || type == ParticleTypes.NAUTILUS) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disablePotionParticles) {
            if (type == ParticleTypes.EFFECT || type == ParticleTypes.INSTANT_EFFECT || type == ParticleTypes.ENTITY_EFFECT || type == ParticleTypes.WITCH) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableCriticalParticles) {
            if (type == ParticleTypes.CRIT || type == ParticleTypes.ENCHANTED_HIT || type == ParticleTypes.DAMAGE_INDICATOR) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableWaterParticles) {
            if (type == ParticleTypes.BUBBLE || type == ParticleTypes.BUBBLE_POP || type == ParticleTypes.BUBBLE_COLUMN_UP || type == ParticleTypes.CURRENT_DOWN || type == ParticleTypes.SPLASH || type == ParticleTypes.FISHING || type == ParticleTypes.RAIN || type == ParticleTypes.DRIPPING_WATER || type == ParticleTypes.FALLING_WATER) {
                cir.setReturnValue(null);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableFireParticles) {
            if (type == ParticleTypes.FLAME || type == ParticleTypes.SOUL_FIRE_FLAME || type == ParticleTypes.LAVA || type == ParticleTypes.DRIPPING_LAVA || type == ParticleTypes.FALLING_LAVA || type == ParticleTypes.LANDING_LAVA || type == ParticleTypes.SMOKE) {
                cir.setReturnValue(null);
            }
        }
    }
}
