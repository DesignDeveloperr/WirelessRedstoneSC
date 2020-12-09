package ru.stonkscraft.wirelessredstone.utils;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.stonkscraft.wirelessredstone.blocks.BlockReceiver;
import ru.stonkscraft.wirelessredstone.blocks.BlockTransmitter;
import ru.stonkscraft.wirelessredstone.blocks.TileEntityReceiver;

import java.util.List;

public class Waila implements IWailaDataProvider {

    public static final Waila instance = new Waila();

    public static void load(IWailaRegistrar registrar) {
        registrar.registerBodyProvider(instance, BlockTransmitter.class);
        registrar.registerBodyProvider(instance, BlockReceiver.class);
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        Block block = iWailaDataAccessor.getBlock();
        if (block instanceof BlockTransmitter) {
            BlockTransmitter blockTransmitter = (BlockTransmitter) block;
            if (blockTransmitter.isActive()) list.add(I18n.format("message.status") + ": " + I18n.format("message.status.on"));
            else list.add(I18n.format("message.status") + ": " + I18n.format("message.status.off"));
        }

        if (block instanceof BlockReceiver) {
            TileEntityReceiver tileEntity = (TileEntityReceiver) iWailaDataAccessor.getTileEntity();
            if (tileEntity.getX() == 0 && tileEntity.getZ() == 0 && tileEntity.getY() == 0) list.add(I18n.format("message.status.notconnected"));
            else {
                list.add(I18n.format("message.status.connected") + ": " + tileEntity.getX() + " " + tileEntity.getY() + " " + tileEntity.getZ());
                list.add(I18n.format("message.status.world") + ": DIM" + tileEntity.getWorldId());
            }

            BlockReceiver blockReceiver = (BlockReceiver) block;
            if (blockReceiver.isActive()) list.add(I18n.format("message.status") + ": " + I18n.format("message.status.on"));
            else list.add(I18n.format("message.status") + ": " + I18n.format("message.status.off"));
        }
        return list;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, int i, int i1, int i2) {
        return null;
    }
}
