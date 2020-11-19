package ru.stonkscraft.wirelessredstone.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import ru.stonkscraft.wirelessredstone.blocks.TileEntityReceiver;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;

public class WRIvents {

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        World world = event.world;
        if (!world.isRemote) {

        }
    }

}
