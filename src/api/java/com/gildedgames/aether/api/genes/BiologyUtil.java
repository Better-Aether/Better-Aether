package com.gildedgames.aether.api.genes;

import net.minecraft.world.World;

import java.util.Random;

public class BiologyUtil
{

	private BiologyUtil()
	{

	}

	public static <T extends GeneRegion<G>, G extends Gene> G evaluateInheritedGene(Random r, T geneRegion1, T geneRegion2)
	{
		final float inheritenceChance1 = geneRegion1.gene().inheritance().generateChanceToInherit(r);
		final float inheritenceChance2 = geneRegion2.gene().inheritance().generateChanceToInherit(r);

		final boolean equalChance = inheritenceChance1 == inheritenceChance2;

		T chosenToInherit;

		if (equalChance)
		{
			chosenToInherit = r.nextBoolean() ? geneRegion1 : geneRegion2;
		}
		else
		{
			chosenToInherit = inheritenceChance1 > inheritenceChance2 ? geneRegion1 : geneRegion2;
		}

		return chosenToInherit.gene();
	}

	public static int getRandomSeed(World world)
	{
		int modifier = world.rand.nextBoolean() ? 1 : -1;

		return modifier * world.rand.nextInt(Integer.MAX_VALUE);
	}

}
