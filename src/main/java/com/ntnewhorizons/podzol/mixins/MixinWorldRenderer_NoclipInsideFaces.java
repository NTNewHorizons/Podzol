package com.ntnewhorizons.podzol.mixins;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.ntnewhorizons.podzol.Config;

/**
 * Vanilla bakes the block around the camera inside-out into chunk meshes, which leaves "holes" behind while
 * noclipping. Skip it while the camera is noclipping.
 */
@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer_NoclipInsideFaces {

    @WrapOperation(
        method = "updateRenderer",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockByRenderType(Lnet/minecraft/block/Block;III)Z",
            ordinal = 1))
    private boolean podzol$skipInsideFacesWhileNoclipping(RenderBlocks renderBlocks, Block block, int x, int y, int z,
        Operation<Boolean> original) {
        final EntityLivingBase camera = Minecraft.getMinecraft().renderViewEntity;
        if (Config.noclipHideInsideFaces && camera != null && camera.noClip) return false;
        return original.call(renderBlocks, block, x, y, z);
    }
}
