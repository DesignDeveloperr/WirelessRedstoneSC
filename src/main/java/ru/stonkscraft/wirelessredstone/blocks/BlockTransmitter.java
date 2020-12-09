package ru.stonkscraft.wirelessredstone.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;

import java.util.Random;

public class BlockTransmitter extends Block {

    private IIcon top, sides;
    private final boolean active;
    private final String texture_sides;
    private final String texture_top;

    public BlockTransmitter(boolean active) {
        super(new Material(MapColor.ironColor));
        setStepSound(Block.soundTypeMetal);
        setBlockName("wrsc_transmitter");
        setHardness(0.1F);
        this.active = active;
        if (!this.active) {
            setCreativeTab(WirelessRedstone.tabs);
            this.texture_sides = "wirelessredstonesc:transmitter_sides";
            this.texture_top = "wirelessredstonesc:transmitter_top";
        } else {
            this.texture_sides = "wirelessredstonesc:transmitter_sides_active";
            this.texture_top = "wirelessredstonesc:transmitter_top_active";
        }
    }

    public boolean isActive() {
        return this.active;
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

    private void updateBlock(World world, int x, int y, int z) {
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
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        updateBlock(world, x, y, z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        updateBlock(world, x, y, z);
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

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(WRBlocks.transmitter, 1);
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
