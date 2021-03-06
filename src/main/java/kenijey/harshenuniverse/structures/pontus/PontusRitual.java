package kenijey.harshenuniverse.structures.pontus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.base.HarshenStructure;
import kenijey.harshenuniverse.dimensions.DimensionPontus;
import kenijey.harshenuniverse.internal.HarshenAPIHandler;
import kenijey.harshenuniverse.recipies.LightningRitualRecipes;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PontusRitual extends HarshenStructure
{
	public PontusRitual() {
		super("pontus/ambient", "pontus_ritual", 0.002f, false, DimensionPontus.DIMENSION_ID);
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) 
	{
		LightningRitualRecipes recipe = HarshenAPIHandler.allRitualRecipes.get(random.nextInt(HarshenAPIHandler.allRitualRecipes.size()));
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for(HarshenStack stack : recipe.getInputs())
			stacks.add(stack.getStackList().get(random.nextInt(stack.getStackList().size())));
		stacks.remove(random.nextInt(stacks.size()));
		BlockPos position = pos.subtract(originAddition);
		ArrayList<EnumFacing> shuffledFacing = new ArrayList<>();
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
			shuffledFacing.add(facing);
		Collections.shuffle(shuffledFacing);
		for(EnumFacing facing : shuffledFacing)
			if(world.getBlockState(position.offset(facing)).getBlock() == HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL
			&& facing.getHorizontalIndex() < stacks.size() && random.nextBoolean())
				((TileEntityHarshenDimensionalPedestal)world.getTileEntity(position.offset(facing))).setItem(stacks.get(facing.getHorizontalIndex()));	
		world.setBlockToAir(position);
	}
}