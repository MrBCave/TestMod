package net.cavebr.bradleysschoolmod.block;

import net.cavebr.bradleysschoolmod.BradleysSchoolMod;
import net.cavebr.bradleysschoolmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockSaltLamp extends Block{
    public BlockSaltLamp(Properties p) {
        super(p.randomTicks());
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel serverLevel, BlockPos pos, RandomSource rand){
        BradleysSchoolMod.LOGGER.debug("TICKING SALT LAMP");

        ItemStack stack = new ItemStack(ModItems.NEGATIVE_ION.get());
        ItemEntity item = new ItemEntity(
                serverLevel,
                pos.getX()+0.5,
                pos.getY()+1,
                pos.getZ()+ 0.5,
                stack
        );
        double velX = (rand.nextDouble() - 0.5) * 0.2;
        double velY = 0.3 + rand.nextDouble() * 0.3;
        double velZ = (rand.nextDouble() - 0.5) * 0.2;

        item.setDeltaMovement(velX, velY, velZ);

        serverLevel.addFreshEntity(item);
    }
}
