package ru.stonkscraft.wirelessredstone.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class WRItems {

    public static Item connector;
    public static Item connectorScreen;
    public static Item connectorPlate;

    public static void register() {
        connector = new ItemConnector();
        GameRegistry.registerItem(connector, "connector");
        connectorScreen = new ItemConnectorScreen();
        GameRegistry.registerItem(connectorScreen, "connector_screen");
        connectorPlate = new ItemConnectorPlate();
        GameRegistry.registerItem(connectorPlate, "connector_plate");
    }

    public static Item registerItem(Item item, String name) {
        GameRegistry.registerItem(item, name);
        return item;
    }

}
