package kenijey.harshenuniverse.items;

import kenijey.harshenuniverse.interfaces.IBloodSupply;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BloodyApple extends ItemFood implements IBloodSupply
{
	public BloodyApple()
	{
		super(1, 1, true);
		setUnlocalizedName("bloody_apple");
		setRegistryName("bloody_apple");
	}

	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if (!worldIn.isRemote)
		{           
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 666, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2922, 0));
			player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 3133, 0));
		}
	}
		
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	@Override
	public int getAmountPerSecond() {
		return 1;
	}

	@Override
	public int ticksUntillUsed() {
		return 800;
	}
}