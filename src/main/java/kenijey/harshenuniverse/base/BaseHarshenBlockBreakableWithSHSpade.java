package kenijey.harshenuniverse.base;

import kenijey.harshenuniverse.items.SoulHarsherSpade;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseHarshenBlockBreakableWithSHSpade extends Block
{
	public BaseHarshenBlockBreakableWithSHSpade() {
		super(Material.GROUND);
		setHarvestLevel("shovel", 3);
        blockSoundType = blockSoundType.GROUND;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem() instanceof SoulHarsherSpade)
		{
			setHardness(1f);
			setResistance(1f);
		}
		else
		{
			setHardness(3000f);
			setResistance(3000f);
		}
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
	}
}