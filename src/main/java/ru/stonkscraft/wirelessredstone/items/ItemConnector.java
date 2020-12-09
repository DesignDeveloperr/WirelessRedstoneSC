package ru.stonkscraft.wirelessredstone.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import ru.stonkscraft.wirelessredstone.WirelessRedstone;
import ru.stonkscraft.wirelessredstone.blocks.WRBlocks;

import java.util.List;

public class ItemConnector extends Item {

    public ItemConnector() {
        setUnlocalizedName("wrsc_connector");
        setCreativeTab(WirelessRedstone.tabs);
        setMaxDamage(0);
        setTextureName("wirelessredstonesc:connector");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        Block block = world.getBlock(x, y, z);
        if (player.isSneaking()) {
            if (block.getUnlocalizedName().equals(WRBlocks.transmitter.getUnlocalizedName())) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("x", x);
                nbt.setInteger("y", y);
                nbt.setInteger("z", z);
                nbt.setBoolean("tune", true);
                nbt.setInteger("world", world.provider.dimensionId);
                itemStack.setTagCompound(nbt);
                if (!world.isRemote)
                    player.addChatMessage(new ChatComponentTranslation("message.setconnector"));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        try {
            NBTTagCompound nbt = itemStack.getTagCompound();
            if (nbt.getBoolean("tune")) {
                list.add(I18n.format("message.connector.tune"));
                list.add("X: " + nbt.getInteger("x"));
                list.add("Y: " + nbt.getInteger("y"));
                list.add("Z: " + nbt.getInteger("z"));
                list.add("World: " + nbt.getInteger("world"));
            }
        } catch (NullPointerException ignored) {}
    }
}
