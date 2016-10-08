package com.gildedgames.aether.common.util;

import com.gildedgames.aether.common.crafting.loot.IItemSelector;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.ArrayList;
import java.util.Random;

public class RandomCraftedItemSelector implements IItemSelector
{

	private ArrayList<IRecipe> validRecipeCache;

	@Override
	public ItemStack getRandomItem(Random random)
	{
		if (this.validRecipeCache == null)
		{
			this.validRecipeCache = new ArrayList<>();

			for (IRecipe recipe : CraftingManager.getInstance().getRecipeList())
			{
				if (recipe instanceof ShapedRecipes || recipe instanceof ShapelessRecipes)
				{
					this.validRecipeCache.add(recipe);
				}
			}
		}

		IRecipe recipe = this.validRecipeCache.get(random.nextInt(this.validRecipeCache.size()));

		ItemStack stack = recipe.getRecipeOutput().copy();
		stack.stackSize = 1;

		return stack;
	}

}