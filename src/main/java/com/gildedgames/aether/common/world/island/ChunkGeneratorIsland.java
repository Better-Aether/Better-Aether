package com.gildedgames.aether.common.world.island;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.world.island.logic.IslandData;
import com.gildedgames.aether.common.world.island.logic.IslandSector;
import com.gildedgames.aether.common.world.island.logic.IslandSectorAccess;
import com.gildedgames.aether.common.world.island.logic.WorldGeneratorIsland;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorIsland implements IChunkGenerator
{

	private final World worldObj;

	private final Random rand;

	private double[][] noiseFields;

	private double[] heightMap;

	private NoiseGeneratorOctaves[] octaveNoiseGenerators;

	private Biome[] biomes;

	private WorldGeneratorIsland islandGenerator;

	public ChunkGeneratorIsland(World world, long seed)
	{
		this.worldObj = world;
		this.rand = new Random(seed);

		this.noiseFields = new double[3][];
		this.noiseFields[0] = new double[256];
		this.noiseFields[1] = new double[256];
		this.noiseFields[2] = new double[256];

		this.heightMap = new double[256];

		this.islandGenerator = new WorldGeneratorIsland(this.rand);

		this.octaveNoiseGenerators = new NoiseGeneratorOctaves[7];

		this.octaveNoiseGenerators[0] = new NoiseGeneratorOctaves(this.rand, 64);
		this.octaveNoiseGenerators[1] = new NoiseGeneratorOctaves(this.rand, 4);
	}

	@Override
	public BlockPos getStrongholdGen(World world, String structureName, BlockPos pos)
	{
		return null;
	}

	public void setBlocksInChunk(ChunkPrimer primer, int chunkX, int chunkZ)
	{
		int posX = chunkX * 16;
		int posZ = chunkZ * 16;

		int minX = chunkX * 16;
		int minY = 0;
		int minZ = chunkZ * 16;

		int maxX = minX + 15;
		int maxY = this.worldObj.getActualHeight();
		int maxZ = minZ + 15;

		//StructureBoundingBox chunkBB = new StructureBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);

		int sectorX = IslandSectorAccess.inst().getSectorCoord(chunkX);
		int sectorY = IslandSectorAccess.inst().getSectorCoord(chunkZ);

		long sectorSeed = 0;

		if (!IslandSectorAccess.inst().wasSectorEverCreated(sectorX, sectorY))
		{
			sectorSeed = this.rand.nextLong();
		}

		IslandSector sector = IslandSectorAccess.inst().attemptToLoadSector(sectorX, sectorY, sectorSeed);

		for(int x = 0; x < 16; x++)
		{
			for(int z = 0; z < 16; z++)
			{
				IslandData island = sector.getIslandDataAtBlockPos(posX + x, posZ + z);

				if (island == null)
				{
					continue;
				}

				this.heightMap = this.islandGenerator.genHeightMapForChunk(island, sector, chunkX, chunkZ, this.heightMap);

				int islandWidth = (int)island.getBounds().getMinX();
				int islandHeight = (int)island.getBounds().getMinY();

				int stepX = posX - islandWidth + x;
				int stepZ = posZ - islandHeight + z;

				if (stepX < 0 || stepZ < 0 || stepX > island.getBounds().getWidth() - 1 || stepZ > island.getBounds().getHeight() - 1)
				{
					continue;
				}

				double height = this.heightMap[z  + (x * 16)];

				if (height > -0.3)
				{
					double heightMod = (Math.abs(height + 1) / 2);

					for (int y = 20; y < heightMod * 60; y++)
					{
						primer.setBlockState(x, y, z, BlocksAether.holystone.getDefaultState());
					}
				}
			}
		}
	}

	public void replaceBiomeBlocks(ChunkPrimer primer, int chunkX, int chunkY, Biome[] biomes)
	{
		double oneThirtySnd = 0.03125D;

		this.noiseFields[0] = this.octaveNoiseGenerators[0].generateNoiseOctaves(this.noiseFields[0], chunkX * 16, chunkY * 16, 0, 16, 16, 1, oneThirtySnd, oneThirtySnd, 1.0D);
		this.noiseFields[1] = this.octaveNoiseGenerators[0].generateNoiseOctaves(this.noiseFields[1], chunkX * 16, 109, chunkY * 16, 16, 1, 16, oneThirtySnd, 1.0D, oneThirtySnd);
		this.noiseFields[2] = this.octaveNoiseGenerators[1].generateNoiseOctaves(this.noiseFields[2], chunkX * 16, chunkY * 16, 0, 16, 16, 1, oneThirtySnd * 2D, oneThirtySnd * 2D, oneThirtySnd * 2D);

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				Biome biome = biomes[z + x * 16];

				int sthWithHeightMap = (int) (this.noiseFields[2][x + z * 16] / 3D + 3D + this.rand.nextDouble() / 4);

				int j1 = -1;

				IBlockState topAetherBlock = biome.topBlock;
				IBlockState fillAetherBlock = biome.fillerBlock;
				IBlockState stone = BlocksAether.holystone.getDefaultState();

				for (int y = 256; y >= 0; y--)
				{
					Block block = primer.getBlockState(x, y, z).getBlock();

					if (block == Blocks.AIR)
					{
						j1 = -1;
						continue;
					}

					if (block != stone.getBlock())
					{
						continue;
					}

					if (j1 == -1)
					{
						if (sthWithHeightMap <= 0)
						{
							topAetherBlock = Blocks.AIR.getDefaultState();
							fillAetherBlock = stone;
						}

						j1 = sthWithHeightMap;

						if (y >= 0)
						{
							primer.setBlockState(x, y, z, topAetherBlock);
						}
						else
						{
							primer.setBlockState(x, y, z, fillAetherBlock);
						}

						continue;
					}

					if (j1 <= 0)
					{
						continue;
					}

					primer.setBlockState(x, y, z, fillAetherBlock);

					j1--;
				}
			}
		}
	}

	@Override
	public Chunk provideChunk(int chunkX, int chunkZ)
	{
		this.rand.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);

		ChunkPrimer primer = new ChunkPrimer();

		this.biomes = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomes, chunkX * 16, chunkZ * 16, 16, 16);

		this.setBlocksInChunk(primer, chunkX, chunkZ);

		this.replaceBiomeBlocks(primer, chunkX, chunkZ, this.biomes);

		Chunk chunk = new Chunk(this.worldObj, primer, chunkX, chunkZ);

		chunk.generateSkylightMap();

		return chunk;
	}

	@Override
	public void populate(int chunkX, int chunkZ)
	{
		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, this.worldObj, this.rand, chunkX, chunkZ, false));
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}

	@Override
	public void recreateStructures(Chunk chunk, int chunkX, int chunkZ)
	{

	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		Biome biomegenbase = this.worldObj.getBiome(pos);

		if (biomegenbase == null)
		{
			return null;
		}
		else
		{
			return biomegenbase.getSpawnableList(creatureType);
		}
	}
}
