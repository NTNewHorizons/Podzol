package com.ntnewhorizons.podzol.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.ntnewhorizons.podzol.Config;

/** Noclipping players don't block block placement, so blocks can be placed inside them. */
@Mixin(World.class)
public abstract class MixinWorld_NoclipPlacement {

    @WrapOperation(
        method = "checkNoEntityCollision(Lnet/minecraft/util/AxisAlignedBB;Lnet/minecraft/entity/Entity;)Z",
        at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;preventEntitySpawning:Z"))
    private boolean podzol$ignoreNoclippingPlayers(Entity entity, Operation<Boolean> original) {
        if (Config.noclipPlaceInside && entity instanceof EntityPlayer && entity.noClip) return false;
        return original.call(entity);
    }
}
