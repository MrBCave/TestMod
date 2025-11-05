package net.cavebr.bradleysschoolmod.item;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
