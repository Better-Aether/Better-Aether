package com.gildedgames.aether.common.util.helpers;

import com.gildedgames.aether.api.registry.simple_crafting.ISimpleRecipe;
import com.gildedgames.aether.common.items.tools.ItemSkyrootTool;
import com.gildedgames.aether.common.recipes.simple.OreDictionaryRequirement;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeUtil
{

	private RecipeUtil()
	{

	}

	public static boolean areEqual(Object obj1, Object obj2)
	{
		if (obj2 == null || obj1 == null)
		{
			return false;
		}

		if (obj1 instanceof ItemStack && obj2 instanceof ItemStack)
		{
			ItemStack stack1 = (ItemStack)obj1;
			ItemStack stack2 = (ItemStack)obj2;

			if (stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || stack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
			{
				return stack2.getItem() == stack1.getItem();
			}

			return ItemStack.areItemsEqualIgnoreDurability(stack1, stack2);
		}

		return obj1.equals(obj2) || obj2.equals(obj1);
	}

	public static boolean areEqual(Object obj, ItemStack stack)
	{
		if (stack == null || obj == null)
		{
			return false;
		}

		if (obj instanceof ItemStack)
		{
			ItemStack otherStack = (ItemStack)obj;

			if (otherStack.getItemDamage() == OreDictionary.WILDCARD_VALUE)
			{
				return otherStack.getItem() == stack.getItem();
			}

			return ItemStack.areItemsEqualIgnoreDurability((ItemStack) obj, stack);
		}
		else if (obj instanceof OreDictionaryRequirement)
		{
			OreDictionaryRequirement oreReq = (OreDictionaryRequirement)obj;

			return oreReq.equals(stack);
		}
		else if (obj instanceof String)
		{
			int[] stackIds = OreDictionary.getOreIDs(stack);
			int ore = OreDictionary.getOreID((String) obj);

			for (int id : stackIds)
			{
				if (ore == id)
				{
					return true;
				}
			}
		}

		return false;
	}

	public static boolean hasEnoughOfMaterial(EntityPlayer player, Object req)
	{
		if (req == null)
		{
			return false;
		}

		int amount = 0;

		if (req instanceof ItemStack)
		{
			amount = ((ItemStack) req).stackSize;
		}
		else if (req instanceof OreDictionaryRequirement)
		{
			amount = ((OreDictionaryRequirement) req).getCount();
		}

		for (int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			ItemStack inventoryStack = player.inventory.mainInventory[i];

			if (RecipeUtil.areEqual(req, inventoryStack))
			{
				amount = Math.max(0, amount - inventoryStack.stackSize);

				if (amount <= 0)
				{
					return true;
				}
			}
		}

		return false;
	}

	public static boolean canCraft(EntityPlayer player, ISimpleRecipe recipe)
	{
		if (recipe == null)
		{
			return false;
		}

		outside: for (int reqIndex = 0; reqIndex < recipe.getRequired().length; reqIndex++)
		{
			Object req = recipe.getRequired()[reqIndex];

			int amount = 0;

			if (req instanceof ItemStack)
			{
				amount = ((ItemStack) req).stackSize;
			}
			else if (req instanceof OreDictionaryRequirement)
			{
				amount = ((OreDictionaryRequirement) req).getCount();
			}

			for (int i = 0; i < player.inventory.mainInventory.length; i++)
			{
				ItemStack inventoryStack = player.inventory.mainInventory[i];

				if (RecipeUtil.areEqual(req, inventoryStack))
				{
					amount = Math.max(0, amount - inventoryStack.stackSize);

					if (amount <= 0)
					{
						continue outside;
					}
				}
			}

			if (amount > 0)
			{
				return false;
			}
		}

		return true;
	}

	public static int getTotalTimesCanCraft(EntityPlayer player, ISimpleRecipe recipe)
	{
		if (recipe == null)
		{
			return 0;
		}

		int totalCount = Integer.MAX_VALUE;

		outside: for (int reqIndex = 0; reqIndex < recipe.getRequired().length; reqIndex++)
		{
			Object req = recipe.getRequired()[reqIndex];

			int amount = 0;

			if (req instanceof ItemStack)
			{
				amount = ((ItemStack) req).stackSize;
			}
			else if (req instanceof OreDictionaryRequirement)
			{
				amount = ((OreDictionaryRequirement) req).getCount();
			}

			int innerTotalCount = 0;

			for (int i = 0; i < player.inventory.mainInventory.length; i++)
			{
				ItemStack inventoryStack = player.inventory.mainInventory[i];

				if (RecipeUtil.areEqual(req, inventoryStack))
				{
					innerTotalCount += inventoryStack.stackSize;
				}
			}

			totalCount = Math.min(innerTotalCount / amount, totalCount);
		}

		return totalCount;
	}

}
