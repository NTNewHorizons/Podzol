package com.ntnewhorizons.podzol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = Podzol.MODID,
    version = Tags.VERSION,
    name = "Podzol",
    acceptedMinecraftVersions = "[1.7.10]",
    guiFactory = "com.ntnewhorizons.podzol.PodzolGuiFactory")
public class Podzol {

    public static final String MODID = "podzol";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.ntnewhorizons.podzol.ClientProxy",
        serverSide = "com.ntnewhorizons.podzol.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
