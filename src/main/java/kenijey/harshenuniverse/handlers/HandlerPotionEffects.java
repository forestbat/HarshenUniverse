package kenijey.harshenuniverse.handlers;

import java.util.ArrayList;
import java.util.UUID;

import kenijey.harshenuniverse.HarshenUniverse;
import kenijey.harshenuniverse.damagesource.DamageSourceBleeding;
import kenijey.harshenuniverse.damagesource.DamageSourceHarshed;
import kenijey.harshenuniverse.entity.EntitySoulPart;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerPotionEffects {
	
	private ArrayList<EntityLivingBase> arrayLivingWithHarshedEffect = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> arrayLivingWithBleedingEffect = new ArrayList<EntityLivingBase>();
	private ArrayList<HandlerHarshedEffect> arrayHarshedEffectManager = new ArrayList<HandlerHarshedEffect>();
	private ArrayList<HandlerBleedingEffect> arrayBleedingEffectManager = new ArrayList<HandlerBleedingEffect>();
	private ArrayList<EntityLivingBase> arrayLivingNoSoul = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> arrayNeedingToBeCured = new ArrayList<EntityLivingBase>();

	
	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		try
		{
			if(event.getEntityLiving() instanceof EntitySoulPart && ((EntityLiving)event.getEntityLiving()).getAttackTarget().isPotionActive(HarshenPotions.potionSoulless))
				((EntityLiving)event.getEntityLiving()).setAttackTarget(null);
		}
		catch (NullPointerException e) {
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionSoulless))
		{
			if(!arrayLivingNoSoul.contains(event.getEntityLiving()))
			{
				arrayLivingNoSoul.add(event.getEntityLiving());
				IAttributeInstance attribute = event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"), "healthTo1", -(event.getEntityLiving().getMaxHealth() - 2D), 0).setSaved(true);
				if(!attribute.hasModifier(modifier))	
					attribute.applyModifier(modifier);
			}
			if(event.getEntity().world.isRemote && !Minecraft.getMinecraft().entityRenderer.isShaderActive() && event.getEntityLiving().equals(HarshenUniverse.proxy.getPlayer()))
				Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("minecraft", "shaders/post/desaturate.json"));
		}
		else if(arrayLivingNoSoul.contains(event.getEntityLiving()))
		{
			if(event.getEntity().world.isRemote && event.getEntityLiving().equals(HarshenUniverse.proxy.getPlayer()))
			{
				arrayLivingNoSoul.remove(event.getEntityLiving());
				Minecraft.getMinecraft().entityRenderer.stopUseShader();
			}
			event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"));
			event.getEntityLiving().setHealth(event.getEntityLiving().getHealth());
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionHarshed) && !event.getEntityLiving().world.isRemote)
		{
			if(!arrayLivingWithHarshedEffect.contains(event.getEntityLiving()))
			{
				arrayLivingWithHarshedEffect.add(event.getEntityLiving());
				for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
				{
					if(effect.getPotion().equals(HarshenPotions.potionHarshed))
						arrayHarshedEffectManager.add(new HandlerHarshedEffect(event.getEntityLiving(), effect.getAmplifier()));
				}
			}
			arrayHarshedEffectManager.get(arrayLivingWithHarshedEffect.indexOf(event.getEntityLiving())).add();
		}
		else if(arrayLivingWithHarshedEffect.contains(event.getEntityLiving()) && !event.getEntityLiving().world.isRemote)
		{
			arrayHarshedEffectManager.remove(arrayLivingWithHarshedEffect.indexOf(event.getEntityLiving()));
			arrayLivingWithHarshedEffect.remove(event.getEntityLiving());
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionBleeding) && !event.getEntityLiving().world.isRemote)
		{
			if(!arrayLivingWithBleedingEffect.contains(event.getEntityLiving()))
			{
				arrayLivingWithBleedingEffect.add(event.getEntityLiving());
				for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
				{
					if(effect.getPotion().equals(HarshenPotions.potionBleeding))
						arrayBleedingEffectManager.add(new HandlerBleedingEffect(event.getEntityLiving(), effect.getAmplifier()));
				}
			}
			arrayBleedingEffectManager.get(arrayLivingWithBleedingEffect.indexOf(event.getEntityLiving())).add();
		}
		else if(arrayLivingWithBleedingEffect.contains(event.getEntityLiving()) && !event.getEntityLiving().world.isRemote)
		{
			arrayBleedingEffectManager.remove(arrayLivingWithBleedingEffect.indexOf(event.getEntityLiving()));
			arrayLivingWithBleedingEffect.remove(event.getEntityLiving());
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionCure) && !arrayNeedingToBeCured.contains(event.getEntityLiving()))
		{
			arrayNeedingToBeCured.add(event.getEntityLiving());
			ArrayList<Potion> potionsToRemove = new ArrayList<>();
			for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
				if(effect.getPotion().isBadEffect())
					potionsToRemove.add(effect.getPotion());
			for(Potion potion : potionsToRemove)
				event.getEntityLiving().removePotionEffect(potion);
		}
		else if(!event.getEntityLiving().isPotionActive(HarshenPotions.potionCure) && arrayNeedingToBeCured.contains(event.getEntityLiving()))
			arrayNeedingToBeCured.remove(event.getEntityLiving());
	}
}

class HandlerHarshedEffect
{
	private int timer = 1;
	private final int level;
	private final EntityLivingBase entity;
	private BlockPos position;
	public HandlerHarshedEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
		this.position = this.entity.getPosition();
		this.level = level;
	}

	public void add()
	{
		if(timer++ >= 20)
		{
			timer = 0;
			this.entity.attackEntityFrom(new DamageSourceHarshed(), (float) Math.floor(this.level * 2.5f) + 1);
		}
	}
}
class HandlerBleedingEffect
{
	private int timer = 1;
	private final int level;
	private final EntityLivingBase entity;
	private BlockPos position;
	public HandlerBleedingEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
		this.position = this.entity.getPosition();
		this.level = level;
	}

	public void add()
	{
		if(timer++ >= 30)
		{
			timer = 0;
			this.entity.attackEntityFrom(new DamageSourceBleeding(), (float)this.level + 0.8f);
		}
	}
}