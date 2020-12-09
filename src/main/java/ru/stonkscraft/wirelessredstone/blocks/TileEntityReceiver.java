package ru.stonkscraft.wirelessredstone.blocks;

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
    private int worldId;


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getWorldId() {
        return this.worldId;
    }

    public void setCords(int x, int y, int z, int worldId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldId = worldId;
        this.markDirty();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("xt", this.x);
        nbt.setInteger("yt", this.y);
        nbt.setInteger("zt", this.z);
        nbt.setInteger("worldt", this.worldId);
        super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.x = nbt.getInteger("xt");
        this.y = nbt.getInteger("yt");
        this.z = nbt.getInteger("zt");
        this.worldId = nbt.getInteger("worldt");
        super.readFromNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();
        this.readFromNBT(nbt);
    }

    @Override
    public void updateEntity() {
        if (!this.worldObj.isRemote) this.worldObj.scheduleBlockUpdate(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 0);
    }
}
