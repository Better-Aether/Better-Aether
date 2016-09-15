package com.gildedgames.aether.common;

public class ReflectionAether
{

	public static class ReflectionEntry
	{
		private String[] mappings;

		private ReflectionEntry(String... mappings)
		{
			this.mappings = mappings;
		}

		public String[] getMappings()
		{
			return this.mappings;
		}
	}

	public static final ReflectionEntry IN_MORE_WORLD_OPTIONS_DISPLAY = new ReflectionEntry("field_146344_y", "inMoreWorldOptionsDisplay");

	public static final ReflectionEntry EQUIPPED_PROGRESS_MAIN_HAND = new ReflectionEntry("field_187469_f", "equippedProgressMainHand");

	public static final ReflectionEntry NET_CLIENT_HANDLER = new ReflectionEntry("field_78774_b", "netClientHandler");

	public static final ReflectionEntry INVULNERABLE_DIMENSION_CHANGE = new ReflectionEntry("field_184851_cj", "invulnerableDimensionChange");

	public static final ReflectionEntry BLOCK_HARDNESS = new ReflectionEntry("field_149782_v", "blockHardness");

	public static final ReflectionEntry IS_JUMPING = new ReflectionEntry("field_70703_bu", "isJumping");

	public static final ReflectionEntry FOOD_LEVEL = new ReflectionEntry("field_75127_a", "foodLevel");

	public static final ReflectionEntry FOOD_SATURATION_LEVEL = new ReflectionEntry("field_75125_b", "foodSaturationLevel");

	public static final ReflectionEntry FOOD_EXHAUSTION_LEVEL = new ReflectionEntry("field_75126_c", "foodExhaustionLevel");

	public static final ReflectionEntry TILE_STRUCTURE = new ReflectionEntry("field_189846_f", "tileStructure");

	public static final ReflectionEntry NAME_INPUT = new ReflectionEntry("field_189853_u");

	public static final ReflectionEntry RELATIVE_X_INPUT = new ReflectionEntry("field_189854_v");

	public static final ReflectionEntry RELATIVE_Y_INPUT = new ReflectionEntry("field_189855_w");

	public static final ReflectionEntry RELATIVE_Z_INPUT = new ReflectionEntry("field_189856_x");

	public static final ReflectionEntry SIZE_X_INPUT = new ReflectionEntry("field_189857_y");

	public static final ReflectionEntry SIZE_Y_INPUT = new ReflectionEntry("field_189858_z");

	public static final ReflectionEntry SIZE_Z_INPUT = new ReflectionEntry("field_189825_A");

	public static final ReflectionEntry METADATA_INPUT = new ReflectionEntry("field_189828_D");

	public static final ReflectionEntry INTEGRITY_INPUT = new ReflectionEntry("field_189826_B");

	public static final ReflectionEntry SEED_INPUT = new ReflectionEntry("field_189827_C");

}
