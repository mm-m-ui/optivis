package ru.optivis.mixin;

import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.optivis.config.OptiVisConfig;

@Mixin(SpriteAtlasTexture.class)
public class SpriteAtlasTextureMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void optivis$freezeAnimations(CallbackInfo ci) {
        if (OptiVisConfig.INSTANCE.freezeTextureAnimations) {
            ci.cancel();
        }
    }
}
