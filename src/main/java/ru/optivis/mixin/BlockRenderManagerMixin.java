package ru.optivis.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FernBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.optivis.config.OptiVisConfig;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {

    @Inject(method = "renderBlock", at = @At("HEAD"), cancellable = true)
    private void optivis$disableVegetation(BlockState state, BlockPos pos, BlockRenderView world, MatrixStack matrices, VertexConsumer vertexConsumer, boolean cull, net.minecraft.util.math.random.Random random, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();

        if (OptiVisConfig.INSTANCE.disableGrass) {
            if (block == Blocks.SHORT_GRASS || block == Blocks.TALL_GRASS || block == Blocks.FERN || block == Blocks.LARGE_FERN || block == Blocks.SEAGRASS || block == Blocks.TALL_SEAGRASS) {
                cir.setReturnValue(false);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableFlowers) {
            if (block instanceof FlowerBlock || block == Blocks.DANDELION || block == Blocks.POPPY || block == Blocks.BLUE_ORCHID
                    || block == Blocks.ALLIUM || block == Blocks.AZURE_BLUET || block == Blocks.RED_TULIP || block == Blocks.ORANGE_TULIP
                    || block == Blocks.WHITE_TULIP || block == Blocks.PINK_TULIP || block == Blocks.OXEYE_DAISY || block == Blocks.CORNFLOWER
                    || block == Blocks.LILY_OF_THE_VALLEY || block == Blocks.WITHER_ROSE || block == Blocks.TORCHFLOWER
                    || block == Blocks.PINK_PETALS || block == Blocks.SUNFLOWER || block == Blocks.LILAC || block == Blocks.ROSE_BUSH
                    || block == Blocks.PEONY || block == Blocks.PITCHER_PLANT) {
                cir.setReturnValue(false);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableVines) {
            if (block instanceof VineBlock || block == Blocks.VINE || block == Blocks.CAVE_VINES || block == Blocks.CAVE_VINES_PLANT
                    || block == Blocks.TWISTING_VINES || block == Blocks.TWISTING_VINES_PLANT || block == Blocks.WEEPING_VINES
                    || block == Blocks.WEEPING_VINES_PLANT || block == Blocks.GLOW_LICHEN) {
                cir.setReturnValue(false);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableFerns) {
            if (block instanceof FernBlock || block == Blocks.FERN || block == Blocks.LARGE_FERN) {
                cir.setReturnValue(false);
                return;
            }
        }

        if (OptiVisConfig.INSTANCE.disableDeadBush) {
            if (block == Blocks.DEAD_BUSH) {
                cir.setReturnValue(false);
            }
        }
    }
}
