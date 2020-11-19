package ru.stonkscraft.wirelessredstone.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;

import java.util.Random;

public class BlockTransmitter extends Block {

    private IIcon top, sides;
    private boolean active;
    private String texture_sides;
    private String texture_top;

    public BlockTransmitter(boolean active, String texture_sides, String texture_top) {
        super(new Material(MapColor.ironColor));
        setStepSound(Block.soundTypeMetal);
        setBlockName("wrsc_transmitter");
        setHardness(0.1F);
        this.active = active;
        this.texture_sides = texture_sides;
        this.texture_top = texture_top;
        if (!this.active) setCreativeTab(WirelessRedstone.tabs);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.top = register.registerIcon(this.texture_top);
        this.sides = register.registerIcon(this.texture_sides);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) return top;
        return sides;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (!world.isRemote) {
            if (this.active && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.scheduleBlockUpdate(x, y, z, this, 4);
            }
            else if (!this.active && world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.setBlock(x, y, z, WRBlocks.transmitter_active, 0, 2);
            }
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote) {
            if (this.active && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.scheduleBlockUpdate(x, y, z, this, 4);
            }
            else if (!this.active && world.isBlockIndirectlyGettingPowered(x, y, z)) {
                world.setBlock(x, y, z, WRBlocks.transmitter_active, 0, 2);
            }
        }
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote && this.active && !world.isBlockIndirectlyGettingPowered(x, y, z))
            world.setBlock(x, y, z, WRBlocks.transmitter, 0, 2);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(WRBlocks.transmitter);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_) {
        if (this.active) {
            BlockRedstoneOre redstoneOre = new BlockRedstoneOre(true);
            redstoneOre.func_150186_m(world, x, y, z);
            this.setLightLevel(0.5F);
        } else {
            this.setLightLevel(0F);
        }
    }

}
