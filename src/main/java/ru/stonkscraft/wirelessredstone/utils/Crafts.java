package ru.stonkscraft.wirelessredstone.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;

public class Crafts {

    public static void register() {
        GameRegistry.addRecipe(new ItemStack(WRBlocks.receiver, 1), new Object[] {
                "RIR", "IGI", "RIR",
                ('R'), Blocks.redstone_wire,
                ('I'), Items.iron_ingot,
                ('G'), Items.gold_ingot
        });
    }

}
