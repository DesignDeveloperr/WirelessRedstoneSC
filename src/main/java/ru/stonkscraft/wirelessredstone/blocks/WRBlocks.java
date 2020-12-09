package ru.stonkscraft.wirelessredstone.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class WRBlocks {

    public static Block transmitter;
    public static Block transmitter_active;
    public static Block receiver;
    public static Block receiver_active;

    public static void register() {
        transmitter = GameRegistry.registerBlock(new BlockTransmitter(false), "transmitter");
        transmitter_active = GameRegistry.registerBlock(new BlockTransmitter(true), "transmitter_active");
        receiver = GameRegistry.registerBlock(new BlockReceiver(false), "receiver");
        receiver_active = GameRegistry.registerBlock(new BlockReceiver(true), "receiver_active");
    }

}
