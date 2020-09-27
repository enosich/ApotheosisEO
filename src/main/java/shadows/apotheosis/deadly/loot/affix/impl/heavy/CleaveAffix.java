package shadows.apotheosis.deadly.loot.affix.impl.heavy;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TranslationTextComponent;
import shadows.apotheosis.Apotheosis;
import shadows.apotheosis.deadly.loot.EquipmentType;
import shadows.apotheosis.deadly.loot.affix.Affix;
import shadows.apotheosis.deadly.loot.affix.AffixHelper;
import shadows.apotheosis.deadly.loot.modifiers.AffixModifier;

/**
 * Cleave Affix.  Allows for full strength attacks to trigger a full-strength attack against nearby enemies.
 */
public class CleaveAffix extends Affix {

	private static boolean cleaving = false;

	public CleaveAffix(int weight) {
		super(weight);
	}

	@Override
	public boolean canApply(EquipmentType type) {
		return type == EquipmentType.AXE;
	}

	@Override
	public void onEntityDamaged(LivingEntity user, Entity target, float level) {
		if (Apotheosis.localAtkStrength >= 0.98 && !cleaving && user instanceof PlayerEntity) {
			cleaving = true;
			float chance = level % 1;
			int targets = (int) level;
			if (user.world.rand.nextFloat() < chance) {
				Predicate<Entity> pred = e -> !(e instanceof PlayerEntity) && e instanceof LivingEntity && ((LivingEntity) e).canAttack(EntityType.PLAYER);
				List<Entity> nearby = target.world.getEntitiesInAABBexcluding(target, new AxisAlignedBB(target.getPosition()).grow(6), pred);
				if (!user.world.isRemote) for (Entity e : nearby) {
					if (targets > 0) {
						user.ticksSinceLastSwing = 300;
						((PlayerEntity) user).attackTargetEntityWithCurrentItem(e);
						targets--;
					}
				}
			}
			cleaving = false;
		}
	}

	@Override
	public float apply(ItemStack stack, Random rand, @Nullable AffixModifier modifier) {
		int nearby = 2 + rand.nextInt(9);
		float chance = Math.min(0.9999F, 0.3F + rand.nextFloat());
		if (modifier != null) chance = modifier.editLevel(this, chance);
		AffixHelper.addLore(stack, new TranslationTextComponent("affix." + this.getRegistryName() + ".desc", String.format("%.2f", chance * 100), nearby));
		return nearby + chance;
	}

	@Override
	public float getMin() {
		return 0.3F;
	}

	@Override
	public float getMax() {
		return 0.9999F;
	}

}