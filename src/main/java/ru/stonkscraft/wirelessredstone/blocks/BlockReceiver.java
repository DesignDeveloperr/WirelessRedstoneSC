package ru.stonkscraft.wirelessredstone.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;
import ru.stonkscraft.wirelessredstone.items.ItemConnector;
import ru.stonkscraft.wirelessredstone.utils.BlockTileEntity;

import java.util.Random;

public class BlockReceiver extends BlockTileEntity<TileEntityReceiver> {

    private IIcon top, sides;
    private final boolean active;
    private final String texture_sides;
    private final String texture_top;

    public BlockReceiver(boolean active, String texture_sides, String texture_top) {
        super(new Material(MapColor.ironColor));
        setStepSound(Block.soundTypeMetal);
        setBlockName("wrsc_receiver");
        setHardness(0.1F);
        this.active = active;
        this.texture_sides = texture_sides;
        this.texture_top = texture_top;
        if (!this.active) {
            setCreativeTab(WirelessRedstone.tabs);
            GameRegistry.registerTileEntity(this.getTileEntityClass(), this.getUnlocalizedName());
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        try {
            ItemStack itemStack = player.getCurrentEquippedItem();
            TileEntityReceiver tileEntity = this.getTileEntity(world, x, y, z);
            if (itemStack.getUnlocalizedName().equals(new ItemConnector().getUnlocalizedName())) {
                NBTTagCompound nbt = itemStack.getTagCompound();
                tileEntity.setCords(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z"), nbt.getInteger("world"));
                if (!world.isRemote) {
                    player.addChatMessage(new ChatComponentTranslation("message.getconnector"));
                }
                return true;
            }
        } catch (NullPointerException ignored) {}
        return false;
    }

    private void updateBlock(World world, int x, int y, int z, int tx, int ty, int tz, int worldId) {
        TileEntityReceiver tileEntityReceiver = this.getTileEntity(world, x, y, z);
        tileEntityReceiver.setCords(tx, ty, tz, worldId);
        for (int i = -1; i < 1; i++)
            for (int j = -1; j < 1; j++)
                for (int k = -1; k < 1; k++)
                    world.notifyBlocksOfNeighborChange(x + i, y + j, z + k, this);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random p_149674_5_) {
        try {
            if (!world.isRemote) {
                TileEntityReceiver tileEntity = this.getTileEntity(world, x, y, z);
                int tx = tileEntity.getX();
                int ty = tileEntity.getY();
                int tz = tileEntity.getZ();
                int worldId = tileEntity.getWorldId();
                World worldRemote = DimensionManager.getWorld(worldId);
                if (this.active && !worldRemote.isBlockIndirectlyGettingPowered(tx, ty, tz) && worldRemote.getBlock(tx, ty, tz).getUnlocalizedName().equals("tile.wrsc_transmitter")) {
                    world.setBlock(x, y, z, WRBlocks.receiver, 0, 2);
                    updateBlock(world, x, y, z, tx, ty, tz, worldId);
                } else if (!this.active && worldRemote.isBlockIndirectlyGettingPowered(tx, ty, tz) && worldRemote.getBlock(tx, ty, tz).getUnlocalizedName().equals("tile.wrsc_transmitter")) {
                    world.setBlock(x, y, z, WRBlocks.receiver_active, 0, 2);
                    updateBlock(world, x, y, z, tx, ty, tz, worldId);
                } else if (this.active && !worldRemote.getBlock(tx, ty, tz).getUnlocalizedName().equals("tile.wrsc_transmitter")) {
                    world.setBlock(x, y, z, WRBlocks.receiver, 0, 2);
                    updateBlock(world, x, y, z, tx, ty, tz, worldId);
                }
            }
        } catch (NullPointerException ignored) {}
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
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        if (this.active) return 15;
        else return 0;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        if (this.active) return 15;
        else return 0;
    }

    @Override
    public Class<TileEntityReceiver> getTileEntityClass() {
        return TileEntityReceiver.class;
    }

    @Override
    public TileEntityReceiver createTileEntity(World world, int metadata) {
        return new TileEntityReceiver();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_) {
        if (this.active) {
            BlockRedstoneOre block = new BlockRedstoneOre(true);
            block.func_150186_m(world, x, y, z);
            this.setLightLevel(0.5F);
        } else {
            this.setLightLevel(0F);
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(WRBlocks.receiver);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return new ItemStack(WRBlocks.receiver, 1);
    }
}
