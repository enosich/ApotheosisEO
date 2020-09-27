package shadows.apotheosis.potion;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LuckyFootItem extends Item {

	public LuckyFootItem() {
		super(new Item.Properties().maxStackSize(1));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}