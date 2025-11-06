package net.cavebr.bradleysschoolmod.item;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BradleysSchoolMod.MODID);

    public static final Supplier<CreativeModeTab> ITEMS_TAB =
            CREATIVE_MOD_TAB.register("bradleys_school_items",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.bradleysschoolmod.items"))
                            .icon(() -> new ItemStack(ModItems.GREENSTONE.get()))
                            .displayItems(((itemDisplayParameters, output) -> {
                                output.accept(ModItems.RAW_GREENSTONE);
                                output.accept(ModItems.GREENSTONE);
                            }))
                            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TAB.register(eventBus);
    }

}
