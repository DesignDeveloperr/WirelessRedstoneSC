package ru.stonkscraft.wirelessredstone.utils;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BlockTileEntity<T extends TileEntity> extends BlockBase {

    public BlockTileEntity(Material material) {
        super(material);
    }

    public abstract Class<T> getTileEntityClass();

    public T getTileEntity(World world, int x, int y, int z) {
        return (T) world.getTileEntity(x, y, z);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Nullable
    @Override
    public abstract T createTileEntity(World world, int metadata);

}
