package com.gildedgames.aether.common.world.dungeon;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Random;

import net.minecraftforge.fml.common.FMLCommonHandler;

import com.gildedgames.aether.common.world.dungeon.util.FlatLayerDungeonGenerator;
import com.gildedgames.aether.common.world.dungeon.util.Schematic;
import com.google.common.collect.Lists;

public class DungeonDefinitions
{

	public static final DungeonDefinition SLIDERS_LABYRINTH = new DungeonDefinition()
	{

		@Override
		public DungeonGenerator createGenerator()
		{
			return new FlatLayerDungeonGenerator(1);
		}

		@Override
		public DungeonRoomProvider createRoomProvider()
		{
			return new DungeonRoomProvider()
			{

				@Override
				public List<DungeonRoom> createRooms(Random rand)
				{
					List<DungeonRoom> rooms = Lists.newArrayList();

					File schemFile = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getDataDirectory(), "/dungeonSchematics/");
					
					schemFile.mkdirs();

					File[] files = schemFile.listFiles(new FilenameFilter()
					{
					    @Override
					    public boolean accept(File dir, String name)
					    {
					        return name.endsWith(".schematic");
					    }
					});
					
					for (File file : files)
					{
						rooms.add(new DungeonRoom(new Schematic(file.getName())));
					}
					
					return rooms;
				}
				
			};
		}
		
	};
	
	public static final DungeonDefinition VALKYRIE_TEMPLE = new DungeonDefinition()
	{

		@Override
		public DungeonGenerator createGenerator()
		{
			return null;
		}

		@Override
		public DungeonRoomProvider createRoomProvider()
		{
			return null;
		}
		
	};
	
	public static final DungeonDefinition MOLTEN_CORE = new DungeonDefinition()
	{

		@Override
		public DungeonGenerator createGenerator()
		{
			return null;
		}

		@Override
		public DungeonRoomProvider createRoomProvider()
		{
			return null;
		}
		
	};
	
}
