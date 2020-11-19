package ru.stonkscraft.wirelessredstone.items;

import net.minecraft.item.Item;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;

public class ItemConnectorPlate extends Item {

    public ItemConnectorPlate() {
        setUnlocalizedName("wrsc_connector_plate");
        setCreativeTab(WirelessRedstone.tabs);
        setMaxDamage(0);
        setTextureName("wirelessredstonesc:connector_plate");
    }

}
