package com.gildedgames.aether.api.genes;

public interface IGenePool
{

	IGeneStorage getStorage();

	void transformFromParents(int seed, int fatherSeed, int motherSeed);

	void transformFromSeed(int seed);

}
