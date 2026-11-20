package com.ntnewhorizons.podzol;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.init(event.getSuggestedConfigurationFile());

        FMLCommonHandler.instance()
            .bus()
            .register(new NoclipHandler());
    }
}
