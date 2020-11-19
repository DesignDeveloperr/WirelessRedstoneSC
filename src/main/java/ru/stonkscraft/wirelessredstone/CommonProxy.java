package ru.stonkscraft.wirelessredstone;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;
import ru.stonkscraft.wirelessredstone.events.WRIvents;
import ru.stonkscraft.wirelessredstone.items.WRItems;
import ru.stonkscraft.wirelessredstone.utils.Crafts;

public class CommonProxy {

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        WRBlocks.register();
        WRItems.register();
        Crafts.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
