package com.smd.gctbits.feature.fastflyblockbreaking;

import com.smd.gctlib.api.module.IModularModule;
import com.smd.gctlib.api.module.ModuleConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastFlyBlockBreakingModule implements IModularModule {

    private double multiplier = 5.0D;

    public static String moduleComment() {
        return "Boosts block breaking speed while flying in creative mode.\n"
                + "创造模式飞行时提升方块挖掘速度。";
    }

    @Override
    public void setupModuleConfig(ModuleConfig config) {
        multiplier = config.double_("multiplier", 5.0D, 0.1D, 10.0D,
                "Block breaking speed multiplier while flying in creative mode.",
                "创造模式飞行时的方块挖掘速度倍率。");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!event.getEntityPlayer().world.isRemote
                && !event.getEntityPlayer().onGround
                && event.getEntityPlayer().capabilities.isFlying) {
            event.setNewSpeed(event.getOriginalSpeed() * (float) multiplier);
        }
    }
}
