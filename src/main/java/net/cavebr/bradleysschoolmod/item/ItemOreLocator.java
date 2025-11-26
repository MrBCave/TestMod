package net.cavebr.bradleysschoolmod.item;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;

public class ItemOreLocator extends Item {

    public ItemOreLocator() {
        super(new Item.Properties()
                .durability(128)
                .stacksTo(1)
        );
    }

    private static HashMap<Block, Integer> valueMap = new HashMap<>(){{
        put(Blocks.DEEPSLATE_DIAMOND_ORE, 10);
        put(Blocks.DIAMOND_ORE, 10);
        put(Blocks.DEEPSLATE_EMERALD_ORE, 9);
        put(Blocks.EMERALD_ORE, 9);
        put(Blocks.DEEPSLATE_GOLD_ORE, 8);
        put(Blocks.GOLD_ORE, 8);
    }};



    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));

    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        if (livingEntity instanceof ServerPlayer player){

            int scanRadius = 8;
            int maxValue = 0;
            BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

            for (int x = player.getBlockX()-scanRadius; x <= player.getBlockX()+scanRadius; x++){
                for (int y = player.getBlockY()-scanRadius; y <= player.getBlockY()+scanRadius; y++){
                    for (int z = player.getBlockZ()-scanRadius; z <= player.getBlockZ()+scanRadius; z++){

                        pos = pos.set(x, y, z);
                        Block block = level.getBlockState(pos).getBlock();

                        maxValue = Math.max(valueMap.getOrDefault(block, 0), maxValue);

                    }
                }
            }

            float pitch = 0.5f + ((float)maxValue/10);
            if (maxValue == 0) player.playNotifySound(SoundEvents.NOTE_BLOCK_BASS.value(), SoundSource.PLAYERS, 1, 0.5f);
            else player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, pitch);

            player.getCooldowns().addCooldown(stack.getItem(), 60);
            stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
            return stack;
        }

        return stack;
    }


}
