package com.ntnewhorizons.podzol;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    // Used by the config screen.
    public static Configuration configuration;

    public static boolean creativeNoclip = true;
    public static boolean noclipHideOverlays = true;
    public static boolean noclipPlaceInside = true;
    public static boolean noclipHideInsideFaces = true;
    public static int pickBlockRange = 256;
    public static boolean fastLang = true;

    public static void init(File configFile) {
        configuration = new Configuration(configFile);
        synchronizeConfiguration();
    }

    // Called on startup and after the config screen. Features read these fields
    // live.
    public static void synchronizeConfiguration() {
        creativeNoclip = configuration.getBoolean(
            "creativeNoclip",
            Configuration.CATEGORY_GENERAL,
            creativeNoclip,
            "Noclip while flying in creative.");
        noclipHideOverlays = configuration.getBoolean(
            "noclipHideOverlays",
            Configuration.CATEGORY_GENERAL,
            noclipHideOverlays,
            "Hide block, water and fire overlays while noclipping.");
        noclipPlaceInside = configuration.getBoolean(
            "noclipPlaceInside",
            Configuration.CATEGORY_GENERAL,
            noclipPlaceInside,
            "Allow placing blocks inside noclipping players.");
        noclipHideInsideFaces = configuration.getBoolean(
            "noclipHideInsideFaces",
            Configuration.CATEGORY_GENERAL,
            noclipHideInsideFaces,
            "Don't render the block around the camera inside-out while noclipping.");
        pickBlockRange = configuration.getInt(
            "pickBlockRange",
            Configuration.CATEGORY_GENERAL,
            pickBlockRange,
            0,
            1024,
            "Creative pick block range when nothing is in normal reach. 0 = vanilla.");
        fastLang = configuration.getBoolean(
            "fastLang",
            Configuration.CATEGORY_GENERAL,
            fastLang,
            "Only reload language files when switching language.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
