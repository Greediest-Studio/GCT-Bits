package com.smd.gctbits.feature.fastflyblockbreaking;

import com.smd.gctbits.config.BitsConfig;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastFlyBlockBreakingHandler {

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!event.getEntityPlayer().world.isRemote
                && !event.getEntityPlayer().onGround
                && event.getEntityPlayer().capabilities.isFlying) {
            float multiplier = (float) BitsConfig.FastFlyBlockBreaking.multiplier;
            event.setNewSpeed(event.getOriginalSpeed() * multiplier);
        }
    }
}
