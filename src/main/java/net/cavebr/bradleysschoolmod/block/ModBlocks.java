package net.cavebr.bradleysschoolmod.block;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.cavebr.bradleysschoolmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(BradleysSchoolMod.MODID);

    public static final DeferredBlock<Block> POLISHED_GREENSTONE_BLOCK =
            registerBlock("polished_greenstone_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> RAW_GREENSTONE_BLOCK =
            registerBlock("raw_greenstone_block", () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)
                    .sound(SoundType.TUFF)));

    public static final DeferredBlock<Block> SALT_LAMP =
            registerBlock("salt_lamp", () -> new BlockSaltLamp(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 0.3F)
                    .sound(SoundType.GLASS)
                    .lightLevel( (block) -> 7 )));

    //Method used to register a block.
    //Will register the block form as well as the held item form too.
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //Helper method called by the code above to register the item form of a block.
    //Probably shouldn't be called anywhere else?
    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
