package ru.stonkscraft.wirelessredstone.utils;

import codechicken.nei.api.API;
import net.minecraft.item.ItemStack;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;

public class Nei {

    public static void load() {
        API.hideItem(new ItemStack(WRBlocks.receiver_active));
        API.hideItem(new ItemStack(WRBlocks.transmitter_active));
    }
}
