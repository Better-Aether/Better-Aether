package com.gildedgames.aether.common.world.dungeon.util;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.world.dungeon.DungeonGenerator;
import com.gildedgames.aether.common.world.dungeon.DungeonInstance;
import com.gildedgames.aether.common.world.dungeon.DungeonRoom;
import com.gildedgames.aether.common.world.dungeon.DungeonRoomProvider;
import com.gildedgames.util.modules.world.common.BlockPosDimension;
import com.google.common.collect.Lists;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public class FlatLayerDungeonGenerator implements DungeonGenerator
{

	private static final int CHANCE_FOR_CYCLE = 10;

	private static final int SEPARATION_ITERATIONS = 40;

	private static final int WALL = 0;

	private static final int BIG_ROOM = 1;

	private static final int SMALL_ROOM = 2;

	private static final int PATH = 3;

	private static final int GOAL_ROOM = 4;

	private static final int START_ROOM = 5;

	private static final int END_ROOM = 6;

	private static final int ENTRANCE = 7;

	private static final int TRUE_PATH = 8;

	private boolean layoutReady;

	private LinkedList<DungeonLayer> layers = Lists.newLinkedList();

	public FlatLayerDungeonGenerator(int layerCount)
	{
		for (int i = 0; i < layerCount; i++)
		{
			DungeonLayer layer = new DungeonLayer(40);

			layers.add(layer);
		}
	}

	@Override
	public BlockPosDimension getEntrancePos()
	{
		return null;
	}

	@Override
	public boolean isLayoutReady()
	{
		return this.layoutReady;
	}

	@Override
	public void generateLayout(MinecraftServer server, Random rand, DungeonInstance instance, DungeonRoomProvider provider)
	{
		DungeonLayer prevLayer = null;

		for (DungeonLayer layer : this.layers)
		{
			layer.defineMinY(prevLayer != null ? prevLayer.maxY() : 150);

			List<DungeonRoom> rooms = provider.createRooms(server, rand);

			DungeonRoom entrance = provider.createEntranceRoom(server, rand);
			DungeonRoom stairway = provider.createLayerConnectionRoom(server, rand);

			for (DungeonRoom room : rooms)
			{
				room.setPos(rand.nextInt(layer.getDiameter()), rand.nextInt(layer.getDiameter()));
			}

			entrance.setPos(layer.getDiameter(), layer.getDiameter());
			stairway.setPos(0, 0);

			rooms.add(entrance);
			rooms.add(stairway);

			rooms = this.separateRooms(rooms);

			int[][] tiles = this.createCorridors(instance, rooms, layer, prevLayer, rand, entrance, stairway);

			rooms.add(entrance);
			rooms.add(stairway);

			layer.setRooms(rooms);

			int maxHeight = 0;

			for (DungeonRoom room : layer.getRooms())
			{
				if (room.getHeight() > maxHeight)
				{
					maxHeight = room.getHeight();
				}
			}

			layer.defineTiles(tiles);

			layer.defineHeight(maxHeight + 6);

			int minHeight = maxHeight;

			for (DungeonRoom room : layer.getRooms())
			{
				if (room.getHeight() < maxHeight)
				{
					minHeight = room.getHeight();
				}
			}

			layer.setSmallestRoomHeight(minHeight);

			prevLayer = layer;
		}

		this.layoutReady = true;
	}

	@Override
	public void generateChunk(World world, Random rand, DungeonInstance inst, ChunkPrimer primer, int chunkX, int chunkZ)
	{
		if (!this.isLayoutReady())
		{
			return;
		}

		int posX = chunkX * 16;
		int posZ = chunkZ * 16;

		for (DungeonLayer layer : this.layers)
		{
			int startX = posX - layer.minX();
			int startZ = posZ - layer.minZ();

			if (layer.getWidth() < startX || layer.getLength() < startZ || startX < 0 || startZ < 0)
			{
				continue;
			}

			for (int x = 0; x < 16; x++)
			{
				for (int z = 0; z < 16; z++)
				{
					if (layer.getWidth() > startX + x && layer.getLength() > startZ + z && startX + x >= 0 && startZ + z >= 0)
					{
						int tile = layer.tiles()[startX + x][startZ + z];

						switch (tile)
						{
							case SMALL_ROOM:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, BlocksAether.labyrinth_wall.getDefaultState(), primer);
								break;
							}
							case END_ROOM:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, Blocks.STRUCTURE_VOID.getDefaultState(), primer);
								break;
							}
							case BIG_ROOM:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, BlocksAether.labyrinth_wall.getDefaultState(), primer);
								break;
							}
							case PATH:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, x % 2 == z % 2 ? BlocksAether.labyrinth_base.getDefaultState() : BlocksAether.labyrinth_headstone.getDefaultState(), BlocksAether.labyrinth_wall.getDefaultState(), primer);
								break;
							}
							case TRUE_PATH:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, BlocksAether.labyrinth_wall.getDefaultState(), BlocksAether.labyrinth_wall.getDefaultState(), primer);
								break;
							}
							case WALL:
							{
								for (int i = layer.minY(); i <= layer.maxY(); i++)
								{
									primer.setBlockState(x, i, z, BlocksAether.labyrinth_wall.getDefaultState());
								}

								break;
							}
							case ENTRANCE:
							{
								this.generateTile(layer, x, layer.smallestRoomHeight(), z, Blocks.STRUCTURE_VOID.getDefaultState(), BlocksAether.labyrinth_wall.getDefaultState(), primer);

								break;
							}
							default:
								break;
						}
					}
				}
			}
		}
	}

	@Override
	public void populateChunk(World world, Random rand, DungeonInstance inst, int chunkX, int chunkZ)
	{
		int posX = chunkX * 16;
		int posZ = chunkZ * 16;

		for (DungeonLayer layer : this.layers)
		{
			for (DungeonRoom room : layer.getRooms())
			{
				if (room.template != null)
				{
					PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(new ChunkPos(chunkX, chunkZ)).setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
					room.template.addBlocksToWorldChunk(world, new BlockPos(room.getMinX(), layer.minY(), room.getMinZ()), placementsettings);

					BlockPos roomMin = new BlockPos(posX, layer.minY() + 1, posZ);

					Iterable<BlockPos.MutableBlockPos> blocks = BlockPos.getAllInBoxMutable(roomMin, roomMin.add(16, room.template.getSize().getY(), 16));

					for (BlockPos.MutableBlockPos pos : blocks)
					{
						IBlockState state = world.getBlockState(pos);

						if (state.getLightValue(world, pos) > 0)
						{
							world.setBlockState(pos, state);
							world.checkLight(pos);

							Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

							world.markAndNotifyBlock(pos, chunk, Blocks.AIR.getDefaultState(), state, 3);
						}
					}
				}
			}
		}

		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				if (posX + x == inst.getInsideEntrance().getX() + 1 && posZ + z == inst.getInsideEntrance().getZ() + 1)
				{
					world.setBlockState(new BlockPos(posX + x, inst.getInsideEntrance().getY(), posZ + z), BlocksAether.labyrinth_totem.getDefaultState());
				}

				/*BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

				yloop:
				for (int y = 0; y < 256; y++)
				{
					pos.set(posX + x, y, posZ + z);

					IBlockState state = world.getBlockState(pos);

					if (state.getBlock().getLightValue() > 0)
					{
						world.markBlockForUpdate(pos);
						world.markBlockRangeForRenderUpdate(new BlockPos(posX + x, 0, posZ + z), new BlockPos(posX + x, 256, posZ + z));
						break yloop;
					}
				}*/
			}
		}
	}

	private void generateTile(DungeonLayer layer, int x, int height, int z, IBlockState block, ChunkPrimer primer)
	{
		this.generateTile(layer, x, height, z, block, block, primer);
	}

	private void generateTile(DungeonLayer layer, int x, int height, int z, IBlockState block, IBlockState block2, ChunkPrimer primer)
	{
		//for (int i = layer.minY(); i <= layer.minY(); i++)
		//{
			primer.setBlockState(x, layer.minY(), z, block);
		//}

		for (int i = layer.minY() + 1; i <= layer.minY() + height; i++)
		{
			primer.setBlockState(x, i, z, Blocks.STRUCTURE_VOID.getDefaultState());
		}

		for (int i = layer.minY() + 1 + height; i <= layer.minY() + layer.getHeight(); i++)
		{
			primer.setBlockState(x, i, z, block2);
		}
	}

	private List<DungeonRoom> separateRooms(List<DungeonRoom> rooms)
	{
		for (int iter = 0; iter < SEPARATION_ITERATIONS; iter++)//This part uses separation behavior to separate intersecting rooms.
		{
			Iterator<DungeonRoom> iterator1 = rooms.iterator();

			while (iterator1.hasNext())
			{
				DungeonRoom room1 = (DungeonRoom) iterator1.next();

				if (room1.asleep)
				{
					continue;
				}

				double vx = 0;
				double vz = 0;
				int overlaps = 0;
				double room1x = room1.getCenterX();
				double room1z = room1.getCenterZ();
				double largestOverlap = 0;

				Iterator<DungeonRoom> iterator2 = rooms.iterator();

				while (iterator2.hasNext())
				{
					DungeonRoom room2 = (DungeonRoom) iterator2.next();

					if (room1 == room2)
					{
						continue;
					}

					Rectangle intersection = room1.fullRectangle().intersection(room2.rectangle);

					if (intersection.isEmpty())
					{
						continue;
					}

					vx += room2.getCenterX() - room1x;
					vz += room2.getCenterZ() - room1z;

					double overlap = intersection.getWidth() > intersection.getHeight() ? intersection.getWidth() : intersection.getHeight();

					largestOverlap = largestOverlap > overlap ? largestOverlap : overlap;

					overlaps++;
					room2.asleep = false;
				}

				if (overlaps == 0)
				{
					room1.asleep = true;

					continue;
				}

				if (vx == 0 && vz == 0)
				{
					continue;
				}

				vx *= -1;
				vz *= -1;

				double length = Math.sqrt(vx * vx + vz * vz);//Normalizes the vector

				vx /= length;
				vz /= length;

				vx *= largestOverlap;
				vz *= largestOverlap;

				room1.setPositionOffset((int) vx, (int) vz);
			}
		}

		Iterator<DungeonRoom> iterator1 = rooms.iterator();

		while (iterator1.hasNext())
		{
			DungeonRoom room1 = (DungeonRoom) iterator1.next();

			Iterator<DungeonRoom> iterator2 = rooms.iterator();

			while (iterator2.hasNext())
			{
				DungeonRoom room2 = (DungeonRoom) iterator2.next();

				if (room1 == room2 || room2.toRemove)
				{
					continue;
				}

				if (room1.fullRectangle().intersects(room2.rectangle))
				{
					room1.toRemove = true;

					break;
				}
			}
		}

		int p = 0;

		while (p < rooms.size())
		{
			if (rooms.get(p).toRemove)
			{
				rooms.remove(p);
			}
			else
			{
				p++;
			}
		}

		return rooms;
	}

	private int[][] createCorridors(DungeonInstance instance, List<DungeonRoom> rooms, DungeonLayer newLayer, DungeonLayer previousLayer, Random rand, DungeonRoom entrance, DungeonRoom stairway)
	{
		rooms.remove(entrance);
		rooms.remove(stairway);

		List<DungeonRoom> largeRooms = this.findLargeRooms(rooms, rooms.size() / 2);

		List<DungeonRoom> smallRooms = (List<DungeonRoom>) ((ArrayList<DungeonRoom>) rooms).clone();

		smallRooms.removeAll(largeRooms);

		largeRooms.add(entrance);
		largeRooms.add(stairway);

		int[][] tiles = this.getTiles(largeRooms, smallRooms, newLayer, previousLayer);

		List<Connection> connections = this.createConnections(largeRooms, rand);

		for (Connection c : connections)
		{
			DungeonRoom room1 = null;
			DungeonRoom room2 = null;

			for (DungeonRoom r : largeRooms)
			{
				if (c.getPointA()[0] == (int) r.getCenterX() && c.getPointA()[1] == (int) r.getCenterZ())
				{
					room1 = r;
				}

				if (c.getPointB()[0] == (int) r.getCenterX() && c.getPointB()[1] == (int) r.getCenterZ())
				{
					room2 = r;
				}
			}

			for (int x = room1.getMinX(); x < room1.getMaxX(); x++)
			{
				for (int z = room1.getMinZ(); z < room1.getMaxZ(); z++)
				{
					tiles[x][z] = START_ROOM;
				}
			}

			for (int x = room2.getMinX(); x < room2.getMaxX(); x++)
			{
				for (int z = room2.getMinZ(); z < room2.getMaxZ(); z++)
				{
					tiles[x][z] = GOAL_ROOM;
				}
			}

			List<int[]> path = this.connect((int) room1.getCenterX(), (int) room1.getCenterZ(), (int) room2.getCenterX(), (int) room2.getCenterZ(), tiles, rand);

			this.walkPath(path, smallRooms, tiles);

			for (int x = room1.getMinX(); x < room1.getMaxX(); x++)
			{
				for (int z = room1.getMinZ(); z < room1.getMaxZ(); z++)
				{
					tiles[x][z] = BIG_ROOM;
				}
			}

			for (int x = room2.getMinX(); x < room2.getMaxX(); x++)
			{
				for (int z = room2.getMinZ(); z < room2.getMaxZ(); z++)
				{
					tiles[x][z] = BIG_ROOM;
				}
			}
		}

		this.findStartAndEnd(instance, tiles, largeRooms, smallRooms, newLayer, previousLayer, rand, entrance, stairway);

		return tiles;
	}

	private int[][] getTiles(List<DungeonRoom> largeRooms, List<DungeonRoom> smallRooms, DungeonLayer newLayer, DungeonLayer previousLayer)
	{
		int[] minVector = new int[2];
		int[] maxVector = new int[2];

		for (DungeonRoom r : largeRooms)//Find the rectangle that fits the rooms
		{
			minVector[0] = Math.min(minVector[0], r.getMinX());
			minVector[1] = Math.min(minVector[1], r.getMinZ());
			maxVector[0] = Math.max(maxVector[0], r.getMaxX());
			maxVector[1] = Math.max(maxVector[1], r.getMaxZ());
		}

		for (DungeonRoom r : smallRooms)
		{
			minVector[0] = Math.min(minVector[0], r.getMinX());
			minVector[1] = Math.min(minVector[1], r.getMinZ());
			maxVector[0] = Math.max(maxVector[0], r.getMaxX());
			maxVector[1] = Math.max(maxVector[1], r.getMaxZ());
		}

		if (previousLayer != null)//Ensure the entrance from the other layer is in the tiles
		{
			maxVector[0] = Math.max(maxVector[0], previousLayer.endX());
			maxVector[1] = Math.max(maxVector[1], previousLayer.endZ());
		}

		newLayer.defineWidth(maxVector[0] - minVector[0]);
		newLayer.defineLength(maxVector[1] - minVector[1]);

		int[][] tiles = new int[newLayer.getWidth()][newLayer.getLength()];

		for (int x = 0; x < newLayer.getWidth(); x++)
		{
			for (int z = 0; z < newLayer.getLength(); z++)
			{
				tiles[x][z] = WALL;
			}
		}

		for (DungeonRoom r : largeRooms)
		{
			r.setPositionOffset(-minVector[0], -minVector[1]);

			for (int x = r.getMinX(); x < r.getMaxX(); x++)
			{
				for (int z = r.getMinZ(); z < r.getMaxZ(); z++)
				{
					tiles[x][z] = BIG_ROOM;
				}
			}
		}

		for (DungeonRoom r : smallRooms)
		{
			r.setPositionOffset(-minVector[0], -minVector[1]);

			for (int x = r.getMinX(); x < r.getMaxX(); x++)
			{
				for (int z = r.getMinZ(); z < r.getMaxZ(); z++)
				{
					tiles[x][z] = SMALL_ROOM;
				}
			}
		}

		return tiles;
	}

	//Finds the 15 largest rooms that are generated.
	private List<DungeonRoom> findLargeRooms(List<DungeonRoom> rooms, int desiredLargeRoomCount)
	{
		List<DungeonRoom> largeRooms = new ArrayList<DungeonRoom>();

		Collections.sort(rooms, new RoomAreaComparer());

		for (int i = 0; i < desiredLargeRoomCount; i++)
		{
			largeRooms.add(rooms.get(i));
		}

		return largeRooms;
	}

	//Uses Delaunay Triangulation and Minimal Spanning Trees to make connections between rooms
	private List<Connection> createConnections(List<DungeonRoom> rooms, Random random)
	{
		List<int[]> points = new ArrayList<int[]>();
		Iterator<DungeonRoom> iter = rooms.iterator();

		while (iter.hasNext())//Store the positions in this list of points
		{
			points.add(((DungeonRoom) iter.next()).getPositionArray());
		}

		//Find Delaunay triangulation from the centers of the large rooms
		TriangulationCell[] cells = (new ConvexHull()).getDelaunayTriangulation(points);//ConvexHull.create(verticesz);

		List<Connection> connections = new ArrayList<Connection>();

		for (TriangulationCell cell : cells)
		{
			List<int[]> vertices = cell.vertices;
			new Connection(vertices.get(0), vertices.get(1), connections);
			new Connection(vertices.get(0), vertices.get(2), connections);
			new Connection(vertices.get(1), vertices.get(2), connections);
		}

		//Create minimal spanning tree from the delaunay triangulation
		Collections.sort(connections, new ConnectionComparer());//Sort by the length of the connection
		List<List<int[]>> branches = new ArrayList<List<int[]>>();//Contains branches of the minimal spanning tree while generating it
		List<Connection> chosenConnections = new ArrayList<Connection>();

		for (Connection c : connections)
		{
			if (chosenConnections.size() < 2)//The first two connections wouldn't make a circle
			{
				this.addConnection(c, branches, chosenConnections);
				continue;
			}

			if (this.checkForCycle(c, branches))//If adding this connection to the branch would create a circle
			{
				continue;
			}

			this.addConnection(c, branches, chosenConnections);
		}

		for (Connection c : connections)
		{
			if (!chosenConnections.contains(c))
			{
				if (random.nextInt(CHANCE_FOR_CYCLE) == 0)
				{
					chosenConnections.add(c);
				}
			}
		}

		return chosenConnections;
	}

	private void addConnection(Connection connection, List<List<int[]>> branches, List<Connection> chosenConnections)
	{
		chosenConnections.add(connection);

		int b = -1;
		int c = -1;

		for (int i = 0; i < branches.size(); i++)
		{
			List<int[]> branch = branches.get(i);

			if (branch.contains(connection.getPointA()))
			{
				b = i;
			}

			if (branch.contains(connection.getPointB()))
			{
				c = i;
			}
		}

		if (b >= 0 && c >= 0)//If both points are already found on a branch, connect the branches together
		{
			branches.get(b).addAll(branches.get(c));
			branches.remove(c);
		}
		else if (b >= 0)//If it was found on one branch, add that point to the branch
		{
			branches.get(b).add(connection.getPointB());
		}
		else if (c >= 0)
		{
			branches.get(c).add(connection.getPointA());
		}
		else //Create a new branch containing the points of the connection
		{
			List<int[]> newBranch = new ArrayList<int[]>();

			newBranch.add(connection.getPointA());
			newBranch.add(connection.getPointB());

			branches.add(newBranch);
		}
	}

	//Returns true if adding the connection would form a circle
	private boolean checkForCycle(Connection connection, List<List<int[]>> branches)
	{
		List<int[]> branch1 = null;
		List<int[]> branch2 = null;

		for (List<int[]> branch : branches)
		{
			if (branch.contains(connection.getPointA()))
			{
				branch1 = branch;
			}

			if (branch.contains(connection.getPointB()))
			{
				branch2 = branch;
			}
		}

		if (branch1 != null && branch2 != null)
		{
			if (branch1 == branch2)//If there already is a branch that can reach both points adding this connection would form a circle
			{
				return true;
			}
		}

		return false;
	}

	//A corridor cannot intersect another large room.
	private List<int[]> connect(int p1X, int p1Z, int p2X, int p2Z, int[][] tiles, Random random)
	{
		List<int[]> path = new ArrayList<int[]>();
		int xVel = (int) Math.signum(p2X - p1X);
		int zVel = (int) Math.signum(p2Z - p1Z);
		int currentX = p1X;
		int currentZ = p1Z;
		path.add(new int[] { p1X, p1Z });
		boolean goHori = random.nextBoolean();
		boolean foundGoal = false;

		while (!foundGoal)
		{
			int iVel = goHori ? xVel : zVel;

			if (iVel == 0)
			{
				goHori = !goHori;
				iVel = goHori ? xVel : zVel;
			}

			int iMin = goHori ? currentX : currentZ;

			int iMax = goHori ? p2X : p2Z;

			currentLoop:
			for (int i = iMin; i != iMax; i += iVel)
			{
				int x, z;

				if (goHori)
				{
					currentX = i;
					x = i;
					z = currentZ;
				}
				else
				{
					currentZ = i;
					x = currentX;
					z = i;
				}

				switch (this.checkPosition(x, z, tiles))
				{
					/*case (1)://TODO: properly handle collision with third non related room
					{
						foundGoal = true;
						break currentLoop;
					}*/
					case (2):
					{
						path.add(new int[] { x, z });
						foundGoal = true;
						break currentLoop;
					}
					default:
					{
						path.add(new int[] { currentX, currentZ });
					}
				}

			}

			goHori = !goHori;
		}
		return path;
	}

	//Returns 0 if the position is valid, 1 if the position is not valid and 2 if the destination is reached
	private int checkPosition(int x, int z, int[][] tiles)
	{
		if (tiles[x][z] == BIG_ROOM)
		{
			return 1;
		}

		if (tiles[x][z] == GOAL_ROOM)
		{
			return 2;
		}

		return 0;
	}

	private void walkPath(List<int[]> path, List<DungeonRoom> smallRooms, int[][] tiles)
	{
		for (int[] pos : path)
		{
			/*if (tiles[pos[0]][pos[1]] == EnumAbyssTileType.SmallRoom)
			{
				for (DungeonAbyssGenRoom room : smallRooms)
				{
					if (room.contains(pos))
					{
						for (int x = room.getMinX(); x <= room.getMaxX(); x++)
							for (int z = room.getMinZ(); z <= room.getMaxZ(); z++)
								tiles[x][z] = EnumAbyssTileType.ChosenSmallRoom;
					}
				}
			}*///Use this for the chosen room system
			if (tiles[pos[0]][pos[1]] == WALL || tiles[pos[0]][pos[1]] == PATH)
			{
				tiles[pos[0]][pos[1]] = TRUE_PATH;
				if (tiles[pos[0] + 1][pos[1]] == WALL)
					tiles[pos[0] + 1][pos[1]] = PATH;
				if (tiles[pos[0] - 1][pos[1]] == WALL)
					tiles[pos[0] - 1][pos[1]] = PATH;
				if (tiles[pos[0] + 1][pos[1] + 1] == WALL)
					tiles[pos[0] + 1][pos[1] + 1] = PATH;
				if (tiles[pos[0] + 1][pos[1] - 1] == WALL)
					tiles[pos[0] + 1][pos[1] - 1] = PATH;
				if (tiles[pos[0] - 1][pos[1] + 1] == WALL)
					tiles[pos[0] - 1][pos[1] + 1] = PATH;
				if (tiles[pos[0] - 1][pos[1] - 1] == WALL)
					tiles[pos[0] - 1][pos[1] - 1] = PATH;
				if (tiles[pos[0]][pos[1] + 1] == WALL)
					tiles[pos[0]][pos[1] + 1] = PATH;
				if (tiles[pos[0]][pos[1] - 1] == WALL)
					tiles[pos[0]][pos[1] - 1] = PATH;
			}
		}
	}

	private void findStartAndEnd(DungeonInstance instance, int[][] tiles, List<DungeonRoom> largeRooms, List<DungeonRoom> smallRooms, DungeonLayer newLayer, DungeonLayer previousLayer,  Random rand, DungeonRoom entrance, DungeonRoom stairway)
	{
		DungeonRoom end = stairway;

		if (previousLayer == null)//If this is the first generated layer
		{
			/*int largestDistance = 0;
			DungeonRoom start = null;

			for (int i = 0; i < largeRooms.size() - 1; i++)
			{
				DungeonRoom room1 = largeRooms.get(i);
				DungeonRoom room2 = null;

				int posX1 = (int) room1.getCenterX();
				int posZ1 = (int) room1.getCenterZ();

				for (int j = i + 1; j < largeRooms.size(); j++)
				{
					room2 = largeRooms.get(j);

					int disX = (int) room2.getCenterX() - posX1;
					int disZ = (int) room2.getCenterZ() - posZ1;
					int distance = disX * disX + disZ * disZ;

					if (distance > largestDistance)
					{
						start = room1;
						end = room2;
						largestDistance = distance;
					}
				}
			}*/

			instance.setInsideEntrance(new BlockPosDimension((int) entrance.getCenterX(), newLayer.minY() + 1, (int) entrance.getCenterZ(), instance.getDimIdInside()));
		}
		else
		{
			int startX = previousLayer.endX();
			int startZ = previousLayer.endZ();

			DungeonRoom room = null;

			int smallestDistance = Integer.MAX_VALUE;

			Iterator<DungeonRoom> iter = largeRooms.iterator();

			while (iter.hasNext())//Find the room closest to where you entered
			{
				DungeonRoom curIter = iter.next();

				int disX = (int) curIter.getCenterX() - startX;
				int disY = (int) curIter.getCenterZ() - startZ;
				int distance = disX * disX + disY * disY;

				if (distance < smallestDistance)
				{
					smallestDistance = distance;
					room = curIter;
				}
			}

			if (tiles[startX][startZ] == WALL || tiles[startX][startZ] == SMALL_ROOM)
			{//If the entrance is at a position that isn't guarenteed to be connected to the full dungeon
				for (int x = room.getMinX(); x < room.getMaxX(); x++)
				{
					//Connect it to the closest large room
					for (int z = room.getMinZ(); z < room.getMaxZ(); z++)
					{
						tiles[x][z] = GOAL_ROOM;
					}
				}

				List<int[]> path = this.connect(startX, startZ, (int) room.getCenterX(), (int) room.getCenterZ(), tiles, rand);

				this.walkPath(path, smallRooms, tiles);

				for (int x = room.getMinX(); x < room.getMaxX(); x++)
				{
					for (int z = room.getMinZ(); z < room.getMaxZ(); z++)
					{
						tiles[x][z] = BIG_ROOM;
					}
				}
			}

			int largestDistance = 0;//Find the large room furthest away from the room closest to the entrance

			for (int i = 0; i < largeRooms.size() - 1; i++)
			{
				DungeonRoom room2 = largeRooms.get(i);

				int disX = (int) room2.getCenterX() - (int) room.getCenterX();
				int disZ = (int) room2.getCenterZ() - (int) room.getCenterZ();
				int distance = disX * disX + disZ * disZ;

				if (distance > largestDistance)
				{
					end = room2;
					largestDistance = distance;
				}
			}

			tiles[startX][startZ] = ENTRANCE;
		}

		for (int i = end.getMinX(); i < end.getMaxX(); i++)
		{
			for (int j = end.getMinZ(); j < end.getMaxZ(); j++)
			{
				tiles[i][j] = END_ROOM;
			}
		}

		newLayer.defineEnd((int) end.getCenterX(), (int) end.getCenterZ());
	}

}
