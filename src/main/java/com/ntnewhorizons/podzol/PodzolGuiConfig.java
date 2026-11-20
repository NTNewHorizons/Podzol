package com.ntnewhorizons.podzol;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

// Config screen. Shows every option in Config.configuration; non-general categories get sub-screens.
public class PodzolGuiConfig extends GuiConfig {

    public PodzolGuiConfig(GuiScreen parentScreen) {
        super(
            parentScreen,
            getConfigElements(),
            Podzol.MODID,
            false,
            false,
            GuiConfig.getAbridgedConfigPath(Config.configuration.toString()));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<>();
        Configuration configuration = Config.configuration;

        for (String name : configuration.getCategoryNames()) {
            ConfigCategory category = configuration.getCategory(name);
            if (category.isChild()) {
                continue;
            }
            if (Configuration.CATEGORY_GENERAL.equals(name)) {
                elements.addAll(new ConfigElement(category).getChildElements());
            } else {
                elements.add(new ConfigElement(category));
            }
        }
        return elements;
    }
}
