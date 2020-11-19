package ru.stonkscraft.wirelessredstone.items;

import net.minecraft.item.Item;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;

public class ItemConnectorScreen extends Item {

    public ItemConnectorScreen() {
        setUnlocalizedName("wrsc_connector_screen");
        setCreativeTab(WirelessRedstone.tabs);
        setMaxDamage(0);
        setTextureName("wirelessredstonesc:connector_screen");
    }

}
