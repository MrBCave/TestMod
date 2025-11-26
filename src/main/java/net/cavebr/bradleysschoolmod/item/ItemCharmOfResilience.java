package net.cavebr.bradleysschoolmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemCharmOfResilience extends Item {

    public ItemCharmOfResilience() {
        super(new Item.Properties()
                .durability(100)
                .rarity(Rarity.UNCOMMON)
        );
    }
}
