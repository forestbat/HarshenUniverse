package kenijey.harshenuniverse.structures.overworld;

import java.util.Random;

import kenijey.harshenuniverse.HarshenLootTables;
import kenijey.harshenuniverse.base.HarshenStructure;
import kenijey.harshenuniverse.worldgenerators.ChestGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Castle extends HarshenStructure
{
	public Castle() {
		super("overworld", "castle", 1f, false, 0, true);
	}
	
	@Override
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ) {
		return false;
	}
	
	@Override
	public boolean canSpawnOnWater() {
		return true;
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) {
		BlockPos[] possOfFillableChests = {new BlockPos(9, 5, 36), new BlockPos(15, 5, 40), new BlockPos(19, 5, 31)};
		for(BlockPos chestPos : possOfFillableChests)
			new ChestGenerator(BlockPos.ORIGIN, 1f, HarshenLootTables.castle, false).generate(world, random, pos.add(chestPos));
	}
	
	private BlockPos getRelativeSize(World world, BlockPos pos)
	{
		return size.add(0, -5, 0);
	}
}