package com.ntnewhorizons.podzol;

import net.minecraftforge.client.event.RenderBlockOverlayEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Hides block, water and fire overlays while noclipping. */
public class NoclipRenderHandler {

    @SubscribeEvent
    public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
        if (Config.noclipHideOverlays && event.player.noClip) event.setCanceled(true);
    }
}
