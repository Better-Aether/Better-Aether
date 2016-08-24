package com.gildedgames.aether.api.genes.util;

import com.gildedgames.aether.api.genes.GeneRegion;
import com.gildedgames.aether.api.genes.IGenePool;
import com.gildedgames.aether.api.genes.IGeneStorage;

import java.util.List;

public abstract class GenePool implements IGenePool
{

	private List<GeneRegion> geneRegions;

	private IGeneStorage storage;

	public GenePool(IGeneStorage storage)
	{
		this.storage = storage;
	}

	public abstract List<GeneRegion> createGeneRegions();

	public final List<GeneRegion> getGeneRegions()
	{
		if (this.geneRegions == null)
		{
			this.geneRegions = this.createGeneRegions();
		}

		return this.geneRegions;
	}

	@Override
	public IGeneStorage getStorage()
	{
		return this.storage;
	}

}
