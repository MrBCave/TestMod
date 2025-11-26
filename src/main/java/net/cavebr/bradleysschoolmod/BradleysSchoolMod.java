package net.cavebr.bradleysschoolmod;

import net.cavebr.bradleysschoolmod.block.ModBlocks;
import net.cavebr.bradleysschoolmod.item.ModCreativeModeTabs;
import net.cavebr.bradleysschoolmod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BradleysSchoolMod.MODID)
public class BradleysSchoolMod {

    public static final String MODID = "bradleysschoolmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BradleysSchoolMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        NeoForge.EVENT_BUS.addListener(BradleysSchoolMod::onPlayerDamage);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the mod's content to relevant creative tabs.
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_GREENSTONE);
            event.accept(ModItems.GREENSTONE);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.RAW_GREENSTONE_BLOCK);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.RAW_GREENSTONE_BLOCK);
            event.accept(ModBlocks.POLISHED_GREENSTONE_BLOCK);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.FRIED_EGG);
            event.accept(ModItems.KIWIBURGER);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CHARM_OF_RESILIENCE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    public static void onPlayerDamage(LivingIncomingDamageEvent event){

        if (event.isCanceled()) return;

        if (event.getEntity() instanceof Player player){

            //Check if the player is holding the charm in either hand
            ItemStack charm = player.getOffhandItem();
            EquipmentSlot slot = EquipmentSlot.OFFHAND;

            if (!charm.is(ModItems.CHARM_OF_RESILIENCE.get())) {
                charm = player.getMainHandItem();
                slot = EquipmentSlot.MAINHAND;
            }
            if (!charm.is(ModItems.CHARM_OF_RESILIENCE.get())){
                return;
            }

            float chance = player.getRandom().nextFloat();
            if (chance <= 0.2f && player.invulnerableTime <= 1) {

                event.setCanceled(true);
                player.invulnerableTime = Math.max(player.invulnerableTime, 30);

                player.level().playSound(
                        null,
                        player.blockPosition(),
                        SoundEvents.SHIELD_BLOCK,
                        SoundSource.PLAYERS,
                        1.0f, 1.0f
                );
                charm.hurtAndBreak(1, player, slot);

            }
        }
    }
}
