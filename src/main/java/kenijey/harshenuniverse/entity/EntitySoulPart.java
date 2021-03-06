package kenijey.harshenuniverse.entity;

import com.google.common.base.Predicate;

import kenijey.harshenuniverse.damagesource.DamageSourceSoulPart;
import kenijey.harshenuniverse.entity.AI.AIEntityFlyingTowardsPlayer;
import kenijey.harshenuniverse.entity.movehelper.MoveHelperSoulPart;
import kenijey.harshenuniverse.interfaces.IBurnInDay;
import kenijey.harshenuniverse.potions.HarshenPotions;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySoulPart extends EntityMob implements IBurnInDay
{
	private int cooldownTicks = 0;
	
	public EntitySoulPart(World worldIn) {
		super(worldIn);
        this.moveHelper = new MoveHelperSoulPart(this);
        this.experienceValue = 80;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(8, new AIEntityFlyingTowardsPlayer(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));

		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, 10, false, false, new Predicate<EntityLivingBase>() {

			@Override
			public boolean apply(EntityLivingBase input) {
				return !input.isPotionActive(HarshenPotions.potionSoulless);
			}
		}));
	}
	
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
	    this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
	    this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	    this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(700D);
	}  
	
	public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
        this.setNoGravity(true);
        if(this.getAttackTarget() != null && this.getPosition().distanceSq(this.getAttackTarget().getPosition()) < 25)
        	this.getAttackTarget().attackEntityFrom(new DamageSourceSoulPart(this), 2.5f);
    }
	
	@Override
    protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VEX_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return SoundEvents.ENTITY_VEX_DEATH;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return SoundEvents.ENTITY_VEX_HURT;
    }    
}