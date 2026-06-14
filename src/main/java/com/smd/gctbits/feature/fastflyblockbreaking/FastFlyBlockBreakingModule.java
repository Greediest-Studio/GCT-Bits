package com.smd.gctbits.feature.fastflyblockbreaking;

import com.smd.gctbits.module.IModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class FastFlyBlockBreakingModule implements IModule {

    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new FastFlyBlockBreakingHandler());
    }
}
