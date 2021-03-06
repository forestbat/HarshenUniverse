package kenijey.harshenuniverse.handlers;

import java.util.List;

import kenijey.harshenuniverse.HarshenLootTables;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.entity.EntityHarshenSoul;
import kenijey.harshenuniverse.entity.EntityKazzendre;
import kenijey.harshenuniverse.entity.EntitySoulPart;
import kenijey.harshenuniverse.entity.EntitySoullessKnight;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenDrops
{
	@SubscribeEvent
	public void livingDrop(LivingDropsEvent event)
	{
		Vec3d vec = new Vec3d(event.getEntityLiving().getPosition());
		
		if(event.getEntity() instanceof EntityIronGolem)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.golemDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntityEnderman)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.endermanDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntityZombie)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.zombieDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntitySoulPart)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.soulPartDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntityHarshenSoul)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.harshenSoulDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntitySoullessKnight)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.soullessKnightDrops);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
		if(event.getEntity() instanceof EntityKazzendre)
		{
			List<ItemStack> stackList = HarshenUtils.getItemsFromLootTable(event.getEntity().world, HarshenLootTables.kazzendreDrop);
			event.getDrops().add(new EntityItem(event.getEntity().world, vec.x, vec.y, vec.z, stackList.get(0)));
		}
	}
}