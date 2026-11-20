package com.ntnewhorizons.podzol.mixins;

import net.minecraft.client.Minecraft;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.ntnewhorizons.podzol.Config;

/** fastLang: switching language reloads only language files, not all resources. */
@Mixin(targets = "net.minecraft.client.gui.GuiLanguage$List")
public abstract class MixinGuiLanguageList_FastLang {

    @Redirect(
        method = "elementClicked",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;refreshResources()V"))
    private void podzol$reloadLanguageOnly(Minecraft mc) {
        if (Config.fastLang) {
            mc.getLanguageManager()
                .onResourceManagerReload(mc.getResourceManager());
        } else {
            mc.refreshResources();
        }
    }
}
