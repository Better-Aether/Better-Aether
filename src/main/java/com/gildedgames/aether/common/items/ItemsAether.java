package com.gildedgames.aether.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.commons.lang3.tuple.Pair;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.AetherCreativeTabs;
import com.gildedgames.aether.common.AetherMaterials;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.entities.effects.EffectInstance;
import com.gildedgames.aether.common.entities.effects.EffectProcessor;
import com.gildedgames.aether.common.entities.effects.EntityEffects;
import com.gildedgames.aether.common.entities.effects.processors.DoubleDropEffect;
import com.gildedgames.aether.common.entities.effects.processors.FreezeBlocksEffect;
import com.gildedgames.aether.common.entities.effects.processors.ModifyDamageEffect;
import com.gildedgames.aether.common.entities.effects.processors.RegenerateHealthEffect;
import com.gildedgames.aether.common.entities.effects.rules.OutOfCombatRule;
import com.gildedgames.aether.common.items.ItemEffectsBase.ItemEffects;
import com.gildedgames.aether.common.items.ItemEffectsBase.ItemEffects.ItemEffectsProvider;
import com.gildedgames.aether.common.items.ItemPropertiesBase.ItemProperties;
import com.gildedgames.aether.common.items.armor.ItemGravititeArmor;
import com.gildedgames.aether.common.items.armor.ItemNeptuneArmor;
import com.gildedgames.aether.common.items.armor.ItemObsidianArmor;
import com.gildedgames.aether.common.items.armor.ItemPhoenixArmor;
import com.gildedgames.aether.common.items.armor.ItemSentryBoots;
import com.gildedgames.aether.common.items.armor.ItemValkyrieArmor;
import com.gildedgames.aether.common.items.armor.ItemZaniteArmor;
import com.gildedgames.aether.common.items.consumables.ItemAmbrosiumShard;
import com.gildedgames.aether.common.items.consumables.ItemContinuumOrb;
import com.gildedgames.aether.common.items.consumables.ItemGummySwet;
import com.gildedgames.aether.common.items.consumables.ItemRainbowStrawberry;
import com.gildedgames.aether.common.items.consumables.ItemSkyrootConsumableBucket;
import com.gildedgames.aether.common.items.consumables.ItemStomperPop;
import com.gildedgames.aether.common.items.consumables.ItemSwetJelly;
import com.gildedgames.aether.common.items.misc.ItemAetherPortalFrame;
import com.gildedgames.aether.common.items.misc.ItemSkyrootSign;
import com.gildedgames.aether.common.items.tools.EnumToolType;
import com.gildedgames.aether.common.items.tools.ItemGravititeTool;
import com.gildedgames.aether.common.items.tools.ItemHolystoneTool;
import com.gildedgames.aether.common.items.tools.ItemSkyrootBucket;
import com.gildedgames.aether.common.items.tools.ItemSkyrootTool;
import com.gildedgames.aether.common.items.tools.ItemValkyrieTool;
import com.gildedgames.aether.common.items.tools.ItemZaniteTool;
import com.gildedgames.aether.common.items.weapons.ItemDart;
import com.gildedgames.aether.common.items.weapons.ItemDartShooter;
import com.gildedgames.aether.common.items.weapons.ItemPigSlayer;
import com.gildedgames.aether.common.items.weapons.ItemVampireBlade;
import com.gildedgames.aether.common.items.weapons.crossbow.ItemBolt;
import com.gildedgames.aether.common.items.weapons.crossbow.ItemCrossbow;
import com.gildedgames.aether.common.items.weapons.swords.ItemAetherSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemCandyCaneSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemElementalSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemGravititeSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemHolystoneSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemSkyrootSword;
import com.gildedgames.aether.common.items.weapons.swords.ItemZaniteSword;
import com.google.common.collect.Lists;

public class ItemsAether
{
	public static Item skyroot_stick;

	public static Item ambrosium_shard, continuum_orb, zanite_gemstone;

	public static ItemSkyrootTool skyroot_axe, skyroot_pickaxe, skyroot_shovel;

	public static ItemHolystoneTool holystone_axe, holystone_pickaxe, holystone_shovel;

	public static ItemZaniteTool zanite_axe, zanite_pickaxe, zanite_shovel;

	public static ItemGravititeTool gravitite_axe, gravitite_pickaxe, gravitite_shovel;

	public static ItemValkyrieTool valkyrie_axe, valkyrie_pickaxe, valkyrie_shovel;

	public static ItemAetherSword skyroot_sword, holystone_sword, zanite_sword, gravitite_sword, valkyrie_lance;

	public static ItemZaniteArmor zanite_helmet, zanite_chestplate, zanite_leggings, zanite_boots;

	public static ItemGravititeArmor gravitite_helmet, gravitite_chestplate, gravitite_leggings, gravitite_boots;

	public static ItemObsidianArmor obsidian_helmet, obsidian_chestplate, obsidian_leggings, obsidian_boots;

	public static ItemNeptuneArmor neptune_helmet, neptune_chestplate, neptune_leggings, neptune_boots;

	public static ItemPhoenixArmor phoenix_helmet, phoenix_chestplate, phoenix_leggings, phoenix_boots;

	public static ItemValkyrieArmor valkyrie_helmet, valkyrie_chestplate, valkyrie_leggings, valkyrie_boots;

	public static ItemSentryBoots sentry_boots;

	public static Item golden_amber;

	public static ItemFood blueberry, orange, wyndberry, rainbow_strawberry, swet_jelly, gummy_swet;

	public static ItemFood candy_corn, cocoatrice, wrapped_chocolates, jelly_pumpkin, stomper_pop, blueberry_lollipop, orange_lollipop, icestone_poprocks;

	public static ItemFood ginger_bread_man, candy_cane;

	public static ItemSkyrootBucket skyroot_bucket, skyroot_water_bucket;

	public static ItemSkyrootConsumableBucket skyroot_milk_bucket, skyroot_poison_bucket;

	public static ItemAetherRecord valkyrie_music_disc, labyrinth_music_disc, moa_music_disc, aerwhale_music_disc, recording_892;

	public static ItemFood healing_stone;

	public static ItemDartShooter dart_shooter;

	public static ItemDart dart;

	public static ItemCrossbow crossbow;

	public static ItemBolt bolt;

	public static ItemElementalSword flaming_sword, holy_sword, lightning_sword;

	public static ItemSword pig_slayer, vampire_blade, candy_cane_sword;

	public static ItemDoor skyroot_door;

	public static Item iron_ring, gold_ring;

	public static Item zanite_ring, zanite_pendant;

	public static Item iron_pendant, gold_pendant;

	public static Item iron_bubble, regeneration_stone;

	public static Item ice_ring, ice_pendant;

	public static Item daggerfrost_locket;

	public static Item candy_ring, bone_ring, skyroot_ring;

	public static Item icestone;

	public static Item skyroot_sign;
	
	public static Item aether_portal_frame;

	public static void preInit()
	{
		skyroot_stick = registerItem("skyroot_stick", new Item(), AetherCreativeTabs.tabMaterials);
		ambrosium_shard = registerItem("ambrosium_shard", new ItemAmbrosiumShard(), AetherCreativeTabs.tabMaterials);
		continuum_orb = registerItem("continuum_orb", new ItemContinuumOrb(), AetherCreativeTabs.tabMaterials);
		zanite_gemstone = registerItem("zanite_gemstone", new Item(), AetherCreativeTabs.tabMaterials);

		skyroot_axe = registerItem("skyroot_axe", new ItemSkyrootTool(EnumToolType.AXE));
		skyroot_pickaxe = registerItem("skyroot_pickaxe", new ItemSkyrootTool(EnumToolType.PICKAXE));
		skyroot_shovel = registerItem("skyroot_shovel", new ItemSkyrootTool(EnumToolType.SHOVEL));
		skyroot_sword = registerItem("skyroot_sword", new ItemSkyrootSword());

		holystone_axe = registerItem("holystone_axe", new ItemHolystoneTool(EnumToolType.AXE));
		holystone_pickaxe = registerItem("holystone_pickaxe", new ItemHolystoneTool(EnumToolType.PICKAXE));
		holystone_shovel = registerItem("holystone_shovel", new ItemHolystoneTool(EnumToolType.SHOVEL));
		holystone_sword = registerItem("holystone_sword", new ItemHolystoneSword());

		zanite_axe = registerItem("zanite_axe", new ItemZaniteTool(EnumToolType.AXE));
		zanite_pickaxe = registerItem("zanite_pickaxe", new ItemZaniteTool(EnumToolType.PICKAXE));
		zanite_shovel = registerItem("zanite_shovel", new ItemZaniteTool(EnumToolType.SHOVEL));
		zanite_sword = registerItem("zanite_sword", new ItemZaniteSword());

		gravitite_axe = registerItem("gravitite_axe", new ItemGravititeTool(EnumToolType.AXE));
		gravitite_pickaxe = registerItem("gravitite_pickaxe", new ItemGravititeTool(EnumToolType.PICKAXE));
		gravitite_shovel = registerItem("gravitite_shovel", new ItemGravititeTool(EnumToolType.SHOVEL));
		gravitite_sword = registerItem("gravitite_sword", new ItemGravititeSword());

		valkyrie_axe = registerItem("valkyrie_axe", new ItemValkyrieTool(EnumToolType.AXE));
		valkyrie_pickaxe = registerItem("valkyrie_pickaxe", new ItemValkyrieTool(EnumToolType.PICKAXE));
		valkyrie_shovel = registerItem("valkyrie_shovel", new ItemValkyrieTool(EnumToolType.SHOVEL));
		valkyrie_lance = registerItem("valkyrie_lance", new ItemAetherSword(AetherMaterials.LEGENDARY_TOOL, ItemAbilityType.PASSIVE));

		zanite_helmet = registerItem("zanite_helmet", new ItemZaniteArmor(0));
		zanite_chestplate = registerItem("zanite_chestplate", new ItemZaniteArmor(1));
		zanite_leggings = registerItem("zanite_leggings", new ItemZaniteArmor(2));
		zanite_boots = registerItem("zanite_boots", new ItemZaniteArmor(3));

		gravitite_helmet = registerItem("gravitite_helmet", new ItemGravititeArmor(0));
		gravitite_chestplate = registerItem("gravitite_chestplate", new ItemGravititeArmor(1));
		gravitite_leggings = registerItem("gravitite_leggings", new ItemGravititeArmor(2));
		gravitite_boots = registerItem("gravitite_boots", new ItemGravititeArmor(3));

		obsidian_helmet = registerItem("obsidian_helmet", new ItemObsidianArmor(AetherMaterials.OBSIDIAN_ARMOR, 0));
		obsidian_chestplate = registerItem("obsidian_chestplate", new ItemObsidianArmor(AetherMaterials.OBSIDIAN_ARMOR, 1));
		obsidian_leggings = registerItem("obsidian_leggings", new ItemObsidianArmor(AetherMaterials.OBSIDIAN_ARMOR, 2));
		obsidian_boots = registerItem("obsidian_boots", new ItemObsidianArmor(AetherMaterials.OBSIDIAN_ARMOR, 3));

		neptune_helmet = registerItem("neptune_helmet", new ItemNeptuneArmor(0));
		neptune_chestplate = registerItem("neptune_chestplate", new ItemNeptuneArmor(1));
		neptune_leggings = registerItem("neptune_leggings", new ItemNeptuneArmor(2));
		neptune_boots = registerItem("neptune_boots", new ItemNeptuneArmor(3));

		phoenix_helmet = registerItem("phoenix_helmet", new ItemPhoenixArmor(0));
		phoenix_chestplate = registerItem("phoenix_chestplate", new ItemPhoenixArmor(1));
		phoenix_leggings = registerItem("phoenix_leggings", new ItemPhoenixArmor(2));
		phoenix_boots = registerItem("phoenix_boots", new ItemPhoenixArmor(3));

		valkyrie_helmet = registerItem("valkyrie_helmet", new ItemValkyrieArmor(AetherMaterials.VALKYRIE_ARMOR, 0));
		valkyrie_chestplate = registerItem("valkyrie_chestplate", new ItemValkyrieArmor(AetherMaterials.VALKYRIE_ARMOR, 1));
		valkyrie_leggings = registerItem("valkyrie_leggings", new ItemValkyrieArmor(AetherMaterials.VALKYRIE_ARMOR, 2));
		valkyrie_boots = registerItem("valkyrie_boots", new ItemValkyrieArmor(AetherMaterials.VALKYRIE_ARMOR, 3));

		sentry_boots = registerItem("sentry_boots", new ItemSentryBoots(AetherMaterials.LEGENDARY_ARMOR, 3));

		golden_amber = registerItem("golden_amber", new Item(), AetherCreativeTabs.tabMaterials);

		blueberry = registerItem("blueberry", new ItemFood(2, false), AetherCreativeTabs.tabConsumables);

		orange = registerItem("orange", new ItemFood(4, false), AetherCreativeTabs.tabConsumables);

		wyndberry = registerItem("wyndberry", new ItemFood(4, false), AetherCreativeTabs.tabConsumables);

		rainbow_strawberry = registerItem("rainbow_strawberry", new ItemRainbowStrawberry(), AetherCreativeTabs.tabConsumables);

		swet_jelly = registerItem("swet_jelly", new ItemSwetJelly(), AetherCreativeTabs.tabConsumables);

		gummy_swet = registerItem("gummy_swet", new ItemGummySwet(), AetherCreativeTabs.tabConsumables);

		candy_corn = registerItem("candy_corn", new ItemFood(8, false), AetherCreativeTabs.tabConsumables);

		cocoatrice = registerItem("cocoatrice", new ItemFood(12, false), AetherCreativeTabs.tabConsumables);

		wrapped_chocolates = registerItem("wrapped_chocolates", new ItemFood(12, false), AetherCreativeTabs.tabConsumables);

		jelly_pumpkin = registerItem("jelly_pumpkin", new ItemFood(12, false), AetherCreativeTabs.tabConsumables);

		stomper_pop = registerItem("stomper_pop", new ItemStomperPop(), AetherCreativeTabs.tabConsumables);

		blueberry_lollipop = registerItem("blueberry_lollipop", new ItemFood(10, false), AetherCreativeTabs.tabConsumables);

		orange_lollipop = registerItem("orange_lollipop", new ItemFood(8, false), AetherCreativeTabs.tabConsumables);

		icestone_poprocks = registerItem("icestone_poprocks", new ItemFood(5, false), AetherCreativeTabs.tabConsumables);

		ginger_bread_man = registerItem("ginger_bread_man", new ItemFood(2, false), AetherCreativeTabs.tabConsumables);

		candy_cane = registerItem("candy_cane", new ItemFood(2, false), AetherCreativeTabs.tabConsumables);

		skyroot_bucket = registerItem("skyroot_bucket", new ItemSkyrootBucket(Blocks.air), AetherCreativeTabs.tabTools);
		skyroot_water_bucket = registerItem("skyroot_water_bucket", new ItemSkyrootBucket(Blocks.flowing_water), AetherCreativeTabs.tabTools);
		skyroot_milk_bucket = registerItem("skyroot_milk_bucket", new ItemSkyrootConsumableBucket(), AetherCreativeTabs.tabConsumables);
		skyroot_poison_bucket = registerItem("skyroot_poison_bucket", new ItemSkyrootConsumableBucket(), AetherCreativeTabs.tabConsumables);

		valkyrie_music_disc = registerItem("valkyrie_music_disc", new ItemAetherRecord("valkyrie"), AetherCreativeTabs.tabTools);
		labyrinth_music_disc = registerItem("labyrinth_music_disc", new ItemAetherRecord("labyrinth"), AetherCreativeTabs.tabTools);
		moa_music_disc = registerItem("moa_music_disc", new ItemAetherRecord("moa"), AetherCreativeTabs.tabTools);
		aerwhale_music_disc = registerItem("aerwhale_music_disc", new ItemAetherRecord("aerwhale"), AetherCreativeTabs.tabTools);
		recording_892 = registerItem("recording_892", new ItemAetherRecord("recording_892"), AetherCreativeTabs.tabTools);

		healing_stone = registerItem("healing_stone", new ItemFood(0, 1.2f, false).setAlwaysEdible().setPotionEffect(Potion.regeneration.getId(), 30, 0, 1.0f), AetherCreativeTabs.tabConsumables);

		dart_shooter = registerItem("dart_shooter", new ItemDartShooter(), AetherCreativeTabs.tabWeapons);
		dart = registerItem("dart", new ItemDart(), AetherCreativeTabs.tabWeapons);

		crossbow = registerItem("crossbow", new ItemCrossbow(), AetherCreativeTabs.tabWeapons);
		bolt = (ItemBolt) registerItem("bolt", new ItemBolt().setMaxStackSize(999), AetherCreativeTabs.tabWeapons);

		flaming_sword = registerItem("flaming_sword", new ItemElementalSword(ItemElementalSword.SwordElement.FIRE));
		holy_sword = registerItem("holy_sword", new ItemElementalSword(ItemElementalSword.SwordElement.HOLY));
		lightning_sword = registerItem("lightning_sword", new ItemElementalSword(ItemElementalSword.SwordElement.LIGHTNING));

		pig_slayer = registerItem("pig_slayer", new ItemPigSlayer(), AetherCreativeTabs.tabWeapons);
		vampire_blade = registerItem("vampire_blade", new ItemVampireBlade(), AetherCreativeTabs.tabWeapons);
		candy_cane_sword = registerItem("candy_cane_sword", new ItemCandyCaneSword(), AetherCreativeTabs.tabWeapons);

		skyroot_door = registerItem("skyroot_door_item", new ItemDoor(BlocksAether.skyroot_door), AetherCreativeTabs.tabBlocks);

		iron_ring = registerItem("iron_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		gold_ring = registerItem("gold_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		iron_pendant = registerItem("iron_pendant", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		gold_pendant = registerItem("gold_pendant", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		zanite_ring = registerItem("zanite_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		zanite_pendant = registerItem("zanite_pendant", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		iron_bubble = registerItem("iron_bubble", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		regeneration_stone = registerItem("regeneration_stone", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		ice_ring = registerItem("ice_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);
		ice_pendant = registerItem("ice_pendant", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		daggerfrost_locket = registerItem("daggerfrost_locket", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		candy_ring = registerItem("candy_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		bone_ring = registerItem("bone_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		skyroot_ring = registerItem("skyroot_ring", new Item().setMaxStackSize(1), AetherCreativeTabs.tabAccessories);

		icestone = registerItem("icestone", new Item().setMaxStackSize(1), AetherCreativeTabs.tabMaterials);

		skyroot_sign = registerItem("skyroot_sign", new ItemSkyrootSign(), AetherCreativeTabs.tabBlocks);
		
		aether_portal_frame = registerItem("aether_portal_frame", new ItemAetherPortalFrame(), AetherCreativeTabs.tabBlocks);

		ItemProperties.register(iron_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(gold_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(iron_pendant, ItemRarity.COMMON, ItemEquipmentType.NECKWEAR);
		ItemProperties.register(gold_pendant, ItemRarity.COMMON, ItemEquipmentType.NECKWEAR);
		ItemProperties.register(zanite_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(zanite_pendant, ItemRarity.COMMON, ItemEquipmentType.NECKWEAR);
		ItemProperties.register(iron_bubble, ItemRarity.COMMON, ItemEquipmentType.RELIC);
		ItemProperties.register(regeneration_stone, ItemRarity.COMMON, ItemEquipmentType.RELIC);
		ItemProperties.register(ice_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(ice_pendant, ItemRarity.COMMON, ItemEquipmentType.NECKWEAR);
		ItemProperties.register(daggerfrost_locket, ItemRarity.COMMON, ItemEquipmentType.NECKWEAR);
		ItemProperties.register(candy_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(bone_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(skyroot_ring, ItemRarity.COMMON, ItemEquipmentType.RING);
		ItemProperties.register(bolt, ItemRarity.NONE, ItemEquipmentType.AMMUNITION);
		ItemProperties.register(Items.arrow, ItemRarity.NONE, ItemEquipmentType.AMMUNITION);

		class Effects implements ItemEffectsProvider
		{

			private List<Pair<EffectProcessor, EffectInstance>> pairs = Lists.newArrayList();

			public Effects()
			{

			}

			public <I extends EffectInstance> Effects add(EffectProcessor<I> processor, I instance)
			{
				Pair<EffectProcessor, EffectInstance> effectPair = Pair.of((EffectProcessor) processor, (EffectInstance) instance);

				this.pairs.add(effectPair);

				return this;
			}

			public Effects add(EffectProcessor<EffectInstance> processor)
			{
				return this.add(processor, new EffectInstance());
			}

			@Override
			public List<Pair<EffectProcessor, EffectInstance>> provide()
			{
				List<Pair<EffectProcessor, EffectInstance>> clone = Lists.newArrayList();

				for (Pair<EffectProcessor, EffectInstance> pair : this.pairs)
				{
					clone.add(Pair.of(pair.getLeft(), pair.getRight().cloneInstance()));
				}

				return clone;
			}

		}

		ItemEffects.register(iron_ring);
		ItemEffects.register(gold_ring);
		ItemEffects.register(iron_pendant);
		ItemEffects.register(gold_pendant);
		ItemEffects.register(zanite_ring);
		ItemEffects.register(zanite_pendant);
		ItemEffects.register(iron_bubble, new Effects().add(EntityEffects.BREATHE_UNDERWATER));
		ItemEffects.register(regeneration_stone, new Effects().add(EntityEffects.REGENERATE_HEALTH, new RegenerateHealthEffect.Instance(4, new OutOfCombatRule(160))));
		ItemEffects.register(ice_ring, new Effects().add(EntityEffects.FREEZE_BLOCKS, new FreezeBlocksEffect.Instance(3)));
		ItemEffects.register(ice_pendant, new Effects().add(EntityEffects.FREEZE_BLOCKS, new FreezeBlocksEffect.Instance(3)));
		ItemEffects.register(daggerfrost_locket, new Effects().add(EntityEffects.DAGGERFROST, new EffectInstance()));
		ItemEffects.register(candy_ring, new Effects().add(EntityEffects.PAUSE_HUNGER));
		ItemEffects.register(bone_ring, new Effects().add(EntityEffects.MODIFY_DAMAGE, new ModifyDamageEffect.Instance(2.0F)));
		ItemEffects.register(skyroot_ring, new Effects().add(EntityEffects.DOUBLE_DROPS, new DoubleDropEffect.Instance(1.5F)));

		registerItemProperties();
	}

	private static void registerItemProperties()
	{
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.WATER,
				new ItemStack(ItemsAether.skyroot_water_bucket), new ItemStack(ItemsAether.skyroot_bucket));
	}

	private static <T extends Item> T registerItem(String name, T item, CreativeTabs tab)
	{
		item.setCreativeTab(tab);

		return registerItem(name, item);
	}

	private static <T extends Item> T registerItem(String name, T item)
	{
		item.setUnlocalizedName(AetherCore.MOD_ID + "." + name);
		item.setRegistryName(name);

		GameRegistry.registerItem(item);

		return item;
	}
}
