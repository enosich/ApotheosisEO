package shadows.potion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import shadows.Apotheosis;
import shadows.Apotheosis.ApotheosisInit;
import shadows.Apotheosis.ApotheosisRecipeEvent;
import shadows.potion.potions.PotionSundering;

public class PotionModule {

	public static final Logger LOG = LogManager.getLogger("Apotheosis : Potion");
	public static final ResourceLocation POTION_TEX = new ResourceLocation(Apotheosis.MODID, "textures/potions.png");

	@ObjectHolder("apotheosis:true_infinity")
	public static final EnchantmentTrueInfinity TRUE_INFINITY = null;

	@ObjectHolder("apotheosis:resistance")
	public static final PotionType RESISTANCE = null;

	@ObjectHolder("apotheosis:long_resistance")
	public static final PotionType LONG_RESISTANCE = null;

	@ObjectHolder("apotheosis:strong_resistance")
	public static final PotionType STRONG_RESISTANCE = null;

	@ObjectHolder("apotheosis:absorption")
	public static final PotionType ABSORPTION = null;

	@ObjectHolder("apotheosis:long_absorption")
	public static final PotionType LONG_ABSORPTION = null;

	@ObjectHolder("apotheosis:strong_absorption")
	public static final PotionType STRONG_ABSORPTION = null;

	@ObjectHolder("apotheosis:haste")
	public static final PotionType HASTE = null;

	@ObjectHolder("apotheosis:long_haste")
	public static final PotionType LONG_HASTE = null;

	@ObjectHolder("apotheosis:strong_haste")
	public static final PotionType STRONG_HASTE = null;

	@ObjectHolder("apotheosis:fatigue")
	public static final PotionType FATIGUE = null;

	@ObjectHolder("apotheosis:long_fatigue")
	public static final PotionType LONG_FATIGUE = null;

	@ObjectHolder("apotheosis:strong_fatigue")
	public static final PotionType STRONG_FATIGUE = null;

	@ObjectHolder("witherskelefix:fragment")
	public static final Item SKULL_FRAGMENT = null;

	@ObjectHolder("apotheosis:wither")
	public static final PotionType WITHER = null;

	@ObjectHolder("apotheosis:long_wither")
	public static final PotionType LONG_WITHER = null;

	@ObjectHolder("apotheosis:strong_wither")
	public static final PotionType STRONG_WITHER = null;

	@ObjectHolder("apotheosis:sundering")
	public static final PotionSundering SUNDERING = null;

	@ObjectHolder("apotheosis:sundering")
	public static final PotionType T_SUNDERING = null;

	@ObjectHolder("apotheosis:long_sundering")
	public static final PotionType LONG_SUNDERING = null;

	@ObjectHolder("apotheosis:strong_sundering")
	public static final PotionType STRONG_SUNDERING = null;

	static Configuration config;

	@SubscribeEvent
	public void init(ApotheosisInit e) {
		PotionHelper.addMix(PotionTypes.AWKWARD, Items.SHULKER_SHELL, RESISTANCE);
		PotionHelper.addMix(RESISTANCE, Items.REDSTONE, LONG_RESISTANCE);
		PotionHelper.addMix(RESISTANCE, Items.GLOWSTONE_DUST, STRONG_RESISTANCE);

		PotionHelper.addMix(RESISTANCE, Items.FERMENTED_SPIDER_EYE, T_SUNDERING);
		PotionHelper.addMix(LONG_RESISTANCE, Items.FERMENTED_SPIDER_EYE, LONG_SUNDERING);
		PotionHelper.addMix(STRONG_RESISTANCE, Items.FERMENTED_SPIDER_EYE, STRONG_SUNDERING);
		PotionHelper.addMix(T_SUNDERING, Items.REDSTONE, LONG_SUNDERING);
		PotionHelper.addMix(T_SUNDERING, Items.GLOWSTONE_DUST, STRONG_SUNDERING);

		PotionHelper.addMix(PotionTypes.AWKWARD, Items.GOLDEN_APPLE, ABSORPTION);
		PotionHelper.addMix(ABSORPTION, Items.REDSTONE, LONG_ABSORPTION);
		PotionHelper.addMix(ABSORPTION, Items.GLOWSTONE_DUST, STRONG_ABSORPTION);

		PotionHelper.addMix(PotionTypes.AWKWARD, Items.MUSHROOM_STEW, HASTE);
		PotionHelper.addMix(HASTE, Items.REDSTONE, LONG_HASTE);
		PotionHelper.addMix(HASTE, Items.GLOWSTONE_DUST, STRONG_HASTE);

		PotionHelper.addMix(HASTE, Items.FERMENTED_SPIDER_EYE, FATIGUE);
		PotionHelper.addMix(LONG_HASTE, Items.FERMENTED_SPIDER_EYE, LONG_FATIGUE);
		PotionHelper.addMix(STRONG_HASTE, Items.FERMENTED_SPIDER_EYE, STRONG_FATIGUE);
		PotionHelper.addMix(FATIGUE, Items.REDSTONE, LONG_FATIGUE);
		PotionHelper.addMix(FATIGUE, Items.GLOWSTONE_DUST, STRONG_FATIGUE);

		if (SKULL_FRAGMENT != null) PotionHelper.addMix(PotionTypes.AWKWARD, SKULL_FRAGMENT, WITHER);
		else PotionHelper.addMix(PotionTypes.AWKWARD, Ingredient.fromStacks(new ItemStack(Items.SKULL, 1, 1)), WITHER);
		PotionHelper.addMix(WITHER, Items.REDSTONE, LONG_WITHER);
		PotionHelper.addMix(WITHER, Items.GLOWSTONE_DUST, STRONG_WITHER);
	}

	@SubscribeEvent
	public void enchants(Register<Enchantment> e) {
		e.getRegistry().register(new EnchantmentTrueInfinity().setRegistryName(Apotheosis.MODID, "true_infinity"));
	}

	@SubscribeEvent
	public void recipes(ApotheosisRecipeEvent e) {
		Ingredient fireRes = Apotheosis.potionIngredient(PotionTypes.FIRE_RESISTANCE);
		Ingredient abs = Apotheosis.potionIngredient(STRONG_ABSORPTION);
		Ingredient res = Apotheosis.potionIngredient(RESISTANCE);
		Ingredient regen = Apotheosis.potionIngredient(PotionTypes.STRONG_REGENERATION);
		e.helper.addShaped(new ItemStack(Items.GOLDEN_APPLE, 1, 1), 3, 3, fireRes, regen, fireRes, abs, Items.GOLDEN_APPLE, abs, res, abs, res);
	}

	@SubscribeEvent
	public void types(Register<PotionType> e) {
		Potion sundering = ForgeRegistries.POTIONS.getValue(new ResourceLocation(Apotheosis.MODID, "sundering"));
		//Formatter::off
		e.getRegistry().registerAll(
				new PotionType("resistance", new PotionEffect(MobEffects.RESISTANCE, 3600)).setRegistryName(Apotheosis.MODID, "resistance"),
				new PotionType("resistance", new PotionEffect(MobEffects.RESISTANCE, 9600)).setRegistryName(Apotheosis.MODID, "long_resistance"),
				new PotionType("resistance", new PotionEffect(MobEffects.RESISTANCE, 1800, 1)).setRegistryName(Apotheosis.MODID, "strong_resistance"),
				new PotionType("absorption", new PotionEffect(MobEffects.ABSORPTION, 1200, 1)).setRegistryName(Apotheosis.MODID, "absorption"),
				new PotionType("absorption", new PotionEffect(MobEffects.ABSORPTION, 3600, 1)).setRegistryName(Apotheosis.MODID, "long_absorption"),
				new PotionType("absorption", new PotionEffect(MobEffects.ABSORPTION, 600, 3)).setRegistryName(Apotheosis.MODID, "strong_absorption"),
				new PotionType("haste", new PotionEffect(MobEffects.HASTE, 3600)).setRegistryName(Apotheosis.MODID, "haste"),
				new PotionType("haste", new PotionEffect(MobEffects.HASTE, 9600)).setRegistryName(Apotheosis.MODID, "long_haste"),
				new PotionType("haste", new PotionEffect(MobEffects.HASTE, 1800, 1)).setRegistryName(Apotheosis.MODID, "strong_haste"),
				new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 3600)).setRegistryName(Apotheosis.MODID, "fatigue"),
				new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 9600)).setRegistryName(Apotheosis.MODID, "long_fatigue"),
				new PotionType("fatigue", new PotionEffect(MobEffects.MINING_FATIGUE, 1800, 1)).setRegistryName(Apotheosis.MODID, "strong_fatigue"),
				new PotionType("wither", new PotionEffect(MobEffects.WITHER, 3600)).setRegistryName(Apotheosis.MODID, "wither"),
				new PotionType("wither", new PotionEffect(MobEffects.WITHER, 9600)).setRegistryName(Apotheosis.MODID, "long_wither"),
				new PotionType("wither", new PotionEffect(MobEffects.WITHER, 1800, 1)).setRegistryName(Apotheosis.MODID, "strong_wither"),
				new PotionType("sundering", new PotionEffect(sundering, 3600)).setRegistryName(Apotheosis.MODID, "sundering"),
				new PotionType("sundering", new PotionEffect(sundering, 9600)).setRegistryName(Apotheosis.MODID, "long_sundering"),
				new PotionType("sundering", new PotionEffect(sundering, 1800, 1)).setRegistryName(Apotheosis.MODID, "strong_sundering"));
		//Formatter::on
	}

	@SubscribeEvent
	public void potions(Register<Potion> e) {
		e.getRegistry().register(new PotionSundering().setRegistryName(Apotheosis.MODID, "sundering"));
	}

	/**
	 * Redirect for {@link ItemArrow#isInfinite(ItemStack, ItemStack, net.minecraft.entity.player.EntityPlayer)}
	 * @param a Arrow ItemStack
	 * @param b Bow ItemStack
	 * @param c EntityPlayer using the bow
	 * @return If this arrow should not be consumed.
	 */
	public static boolean isInfinite(Object a, Object b, Object c) {
		ItemStack stack = (ItemStack) a;
		ItemStack bow = (ItemStack) b;
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		if (enchant <= 0 ? false : stack.getItem().getClass() == ItemArrow.class) return true;
		return (TRUE_INFINITY != null && EnchantmentHelper.getEnchantmentLevel(TRUE_INFINITY, bow) > 0 && stack.getItem() instanceof ItemArrow);
	}

	public static boolean doesShowParticles(Object e) {
		PotionEffect ef = (PotionEffect) e;
		if (ef.getPotion() == MobEffects.INVISIBILITY) return false;
		return ef.showParticles;
	}

	public static float applyPotionDamageCalculations(Object ent, Object src, float damage) {
		EntityLivingBase entity = (EntityLivingBase) ent;
		DamageSource source = (DamageSource) src;
		if (source.isDamageAbsolute()) {
			return damage;
		} else {
			float mult = 1;
			if (entity.isPotionActive(MobEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
				int level = (entity.getActivePotionEffect(MobEffects.RESISTANCE).getAmplifier() + 1);
				mult -= (0.2 * level);
			}
			if (SUNDERING != null && entity.isPotionActive(SUNDERING) && source != DamageSource.OUT_OF_WORLD) {
				int level = (entity.getActivePotionEffect(SUNDERING).getAmplifier() + 1);
				mult += (0.2 * level);
			}

			damage *= mult;

			if (damage <= 0.0F) {
				return 0.0F;
			} else {
				int k = EnchantmentHelper.getEnchantmentModifierDamage(entity.getArmorInventoryList(), source);

				if (k > 0) {
					damage = CombatRules.getDamageAfterMagicAbsorb(damage, (float) k);
				}

				return damage;
			}
		}
	}

}