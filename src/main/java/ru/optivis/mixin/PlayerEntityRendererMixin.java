package ru.optivis.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.optivis.config.OptiVisConfig;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "updateRenderState", at = @At("TAIL"), require = 0)
    private void optivis$disableFirstPersonModel(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (OptiVisConfig.INSTANCE.disableFirstPersonModel
                && client.options.getPerspective().isFirstPerson()
                && client.player != null) {
            client.player.setInvisible(true);
        }
    }
}
