package ru.stonkscraft.wirelessredstone.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;
import ru.stonkscraft.wirelessredstone.items.WRItems;

public class Craft {

    public static void register() {
        GameRegistry.addRecipe(new ItemStack(WRItems.connectorScreen, 1), "XYX", "YXY", "XYX",
                ('X'), Items.redstone,
                ('Y'), Blocks.glass
        );

        GameRegistry.addRecipe(new ItemStack(WRItems.connectorPlate, 1), "XXX", "YYY", "ZZZ",
                ('X'), Items.glowstone_dust,
                ('Y'), Items.redstone,
                ('Z'), Items.iron_ingot
        );

        GameRegistry.addRecipe(new ItemStack(WRItems.connector, 1), "XYX", "XZX", "XWX",
                ('X'), Items.iron_ingot,
                ('Y'), Items.redstone,
                ('Z'), WRItems.connectorScreen,
                ('W'), WRItems.connectorPlate
        );

        GameRegistry.addRecipe(new ItemStack(WRBlocks.transmitter, 1), "XYX", "XZX", "XXX",
                ('X'), Items.iron_ingot,
                ('Y'), Items.diamond,
                ('Z'), WRItems.connectorPlate
        );

        GameRegistry.addRecipe(new ItemStack(WRBlocks.receiver, 1), "XYX", "XZX", "XXX",
                ('X'), Items.iron_ingot,
                ('Y'), Items.gold_ingot,
                ('Z'), WRItems.connectorPlate
        );
    }

}
