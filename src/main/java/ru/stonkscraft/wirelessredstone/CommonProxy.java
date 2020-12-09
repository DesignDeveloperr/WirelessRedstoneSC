package ru.stonkscraft.wirelessredstone;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;
import ru.stonkscraft.wirelessredstone.items.WRItems;

public class CommonProxy {

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        WRBlocks.register();
        WRItems.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
