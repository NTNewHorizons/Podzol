package com.ntnewhorizons.podzol;

import java.util.Collections;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.collect.MapMaker;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

/** creativeNoclip: creative players pass through blocks while flying. Runs on both sides. */
public class NoclipHandler {

    // Players we enabled noclip for, so we only ever disable our own.
    private final Set<EntityPlayer> noclipping = Collections.newSetFromMap(
        new MapMaker().weakKeys()
            .makeMap());

    public static boolean shouldNoclip(EntityPlayer player) {
        return Config.creativeNoclip && player.capabilities.isCreativeMode && player.capabilities.isFlying;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        final EntityPlayer player = event.player;

        if (shouldNoclip(player)) {
            player.noClip = true;
            noclipping.add(player);
        } else if (noclipping.remove(player)) {
            player.noClip = false;
        }
    }
}
