package ru.stonkscraft.wirelessredstone.blocks;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityReceiver extends TileEntity {

    private int x;
    private int y;
    private int z;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        System.out.println("Какая-то тварь поставила X = " + x);
        this.x = x;
        this.markDirty();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.markDirty();
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
        this.markDirty();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("x", this.x);
        nbt.setInteger("y", this.y);
        nbt.setInteger("z", this.z);
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.x = nbt.getInteger("x");
        this.y = nbt.getInteger("y");
        this.z = nbt.getInteger("z");
        super.readFromNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.x, this.y, this.z, 3, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();
        this.readFromNBT(nbt);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        World world = getWorldObj();
        if (!world.isRemote) {
            world.scheduleBlockUpdate(this.xCoord, this.yCoord, this.zCoord, WRBlocks.receiver, 4);
            world.scheduleBlockUpdate(this.xCoord, this.yCoord, this.zCoord, WRBlocks.receiver_active, 4);
        }
    }
}
