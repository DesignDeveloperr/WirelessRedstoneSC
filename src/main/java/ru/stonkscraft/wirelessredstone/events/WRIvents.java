package ru.stonkscraft.wirelessredstone.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class WRIvents {

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        World world = event.world;
        if (!world.isRemote) {

        }
    }

}
