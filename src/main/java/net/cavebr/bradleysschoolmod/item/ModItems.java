package net.cavebr.bradleysschoolmod.item;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BradleysSchoolMod.MODID);

    public static final DeferredItem<Item> RAW_GREENSTONE =
            ITEMS.register("raw_greenstone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> GREENSTONE =
            ITEMS.register("greenstone", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FRIED_EGG =
            ITEMS.register("fried_egg", () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(5)
                            .saturationModifier(1.2f)
                            .build())
            ));

    public static final DeferredItem<Item> KIWIBURGER =
            ITEMS.register("kiwiburger", () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
                    .food(new FoodProperties.Builder()
                            .nutrition(12)
                            .saturationModifier(2.0f)
                            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 1.0f)
                            .build())
            ));

    public static final DeferredItem<Item> CHARM_OF_RESILIENCE =
            ITEMS.register("charm_of_resilience", () -> new ItemCharmOfResilience());

    public static final DeferredItem<Item> ORE_LOCATOR =
            ITEMS.register("ore_locator", () -> new ItemOreLocator());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
