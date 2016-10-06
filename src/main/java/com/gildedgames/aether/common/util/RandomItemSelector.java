package com.gildedgames.aether.common.util;

import com.gildedgames.aether.common.crafting.loot.IItemSelector;
import com.gildedgames.util.core.util.GGHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.fml.common.registry.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomItemSelector implements IItemSelector
{

	private ArrayList<ItemStack> validStackCache;

	private Constraint constraint;

	public RandomItemSelector(Constraint constraint)
	{
		this.constraint = constraint;
	}

	@Override
	public ItemStack getRandomItem(Random random)
	{
		if (this.validStackCache == null)
		{
			this.validStackCache = new ArrayList<>();

			for (final Item item  : GameData.getItemRegistry().typeSafeIterable())//TODO: Make sure this gets all items
			{
				if (item == null)
				{
					continue;
				}

				List<ItemStack> subItems = new ArrayList<>();

				item.getSubItems(item, item.getCreativeTab(), subItems);

				for (final ItemStack stack : subItems)
				{
					if (this.constraint.accept(stack))
					{
						this.validStackCache.add(stack);
					}
				}
			}
		}

		ItemStack stack = this.validStackCache.get(random.nextInt(this.validStackCache.size())).copy();

		stack.stackSize = 1;

		return stack;
	}

}