package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.interfaces.IBloodSupply;
import net.minecraft.item.Item;

public class BloodEssence extends Item implements IBloodSupply
{
	public BloodEssence()
	{
		setUnlocalizedName("blood_essence");
		setRegistryName("blood_essence");
	}
	
	@Override
	public int getAmountPerSecond() {
		return 1;
	}

	@Override
	public int ticksUntillUsed() {
		return 2000;
	}
}