package kenijey.harshenuniverse.intergration.jei.hereticritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshenuniverse.HarshenBlocks;
import kenijey.harshenuniverse.api.CauldronLiquid;
import kenijey.harshenuniverse.api.HarshenStack;
import kenijey.harshenuniverse.base.BaseJeiWrapper;
import kenijey.harshenuniverse.enums.items.GlassContainerValue;
import kenijey.harshenuniverse.recipies.HereticRitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIHereticRitualWrapper extends BaseJeiWrapper	
{
	private final ItemStack output;
	private final CauldronLiquid catalyst;
	private final List<List<ItemStack>> allInputs;

	@SuppressWarnings("unchecked")
	public JEIHereticRitualWrapper(HereticRitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(recipe.getCauldronInput().getStackList());
		for(HarshenStack stack : recipe.getPedestalItems())
			builder.add(stack.getStackList());
		builder.add(ImmutableList.of(GlassContainerValue.getContainerFromType(recipe.getCatalyst()).getStack()));
		builder.add(ImmutableList.of(new ItemStack(HarshenBlocks.HERETIC_CAULDRON)));
		output = recipe.getOutput();
		allInputs = builder.build();
		catalyst = recipe.getCatalyst();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, allInputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}