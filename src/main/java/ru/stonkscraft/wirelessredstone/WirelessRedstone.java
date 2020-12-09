package ru.stonkscraft.wirelessredstone;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.stonkscraft.wirelessredstone.items.WRItems;
import ru.stonkscraft.wirelessredstone.utils.Craft;

@Mod(
    modid = WirelessRedstone.MOD_ID,
    name = WirelessRedstone.MOD_NAME,
    version = WirelessRedstone.MOD_VERSION
)
public class WirelessRedstone {

    public static final String MOD_ID = "wirelessredstonesc";
    public static final String MOD_NAME = "Wireless Redstone SC";
    public static final String MOD_VERSION = "1.0.0";

    @SidedProxy(clientSide = "ru.stonkscraft.wirelessredstone.ClientProxy", serverSide = "ru.stonkscraft.wirelessredstone.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabs = new CreativeTabs("wrsc") {
        @Override
        public Item getTabIconItem() {
            return WRItems.connector;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        Craft.register();
    }


}
