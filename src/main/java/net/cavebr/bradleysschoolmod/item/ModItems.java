package net.cavebr.bradleysschoolmod.item;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BradleysSchoolMod.MODID);

    public static final DeferredItem<Item> RAW_GREENSTONE =
            ITEMS.register("raw_greenstone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GREENSTONE =
            ITEMS.register("greenstone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> NEGATIVE_ION =
            ITEMS.register("negative_ion", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .fast()
                            .nutrition(1)
                            .saturationModifier(2.0F)
                    .build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
