package com.ntnewhorizons.podzol.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ntnewhorizons.podzol.Config;

/** pickBlockRange: longer creative pick block when nothing is in normal reach. */
@Mixin(Minecraft.class)
public abstract class MixinMinecraft_PickBlock {

    @Shadow
    public EntityClientPlayerMP thePlayer;

    @Shadow
    public EntityLivingBase renderViewEntity;

    @Shadow
    public MovingObjectPosition objectMouseOver;

    @Inject(method = "func_147112_ai", at = @At("HEAD"))
    private void podzol$extendCreativePickRange(CallbackInfo ci) {
        if (Config.pickBlockRange <= 0 || thePlayer == null || renderViewEntity == null) return;
        if (!thePlayer.capabilities.isCreativeMode || objectMouseOver == null
            || objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.MISS) return;

        final MovingObjectPosition hit = podzol$rayTraceBlocks(renderViewEntity, Config.pickBlockRange);
        if (hit != null) objectMouseOver = hit;
    }

    /** Vanilla ray traces stop after 200 block steps, so trace in 64-block segments. */
    @Unique
    private static MovingObjectPosition podzol$rayTraceBlocks(EntityLivingBase entity, double range) {
        final double segment = 64.0D;
        final Vec3 eye = entity.getPosition(1.0F);
        final Vec3 look = entity.getLook(1.0F);

        for (double from = 0.0D; from < range; from += segment) {
            final double to = Math.min(from + segment, range);
            // func_147447_a mutates its start vector.
            final Vec3 start = eye.addVector(look.xCoord * from, look.yCoord * from, look.zCoord * from);
            final Vec3 end = eye.addVector(look.xCoord * to, look.yCoord * to, look.zCoord * to);
            final MovingObjectPosition hit = entity.worldObj.func_147447_a(start, end, false, false, false);
            if (hit != null) return hit;
        }
        return null;
    }
}
