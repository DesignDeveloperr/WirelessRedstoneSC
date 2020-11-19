package ru.stonkscraft.wirelessredstone.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class WRBlocks {

    public static Block transmitter;
    public static Block transmitter_active;
    public static Block receiver;
    public static Block receiver_active;

    public static void register() {
        transmitter = GameRegistry.registerBlock(new BlockTransmitter(false, "wirelessredstonesc:transmitter_sides", "wirelessredstonesc:transmitter_top"), "transmitter");
        transmitter_active = GameRegistry.registerBlock(new BlockTransmitter(true, "wirelessredstonesc:transmitter_sides_active", "wirelessredstonesc:transmitter_top_active"), "transmitter_active");
        receiver = GameRegistry.registerBlock(new BlockReceiver(false, "wirelessredstonesc:receiver_sides", "wirelessredstonesc:receiver_top"), "receiver");
        receiver_active = GameRegistry.registerBlock(new BlockReceiver(true, "wirelessredstonesc:receiver_sides_active", "wirelessredstonesc:receiver_top_active"), "receiver_active");
    }

}
