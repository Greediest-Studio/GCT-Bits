package com.smd.gctbits.feature.bacodifficulty;

import com.smd.gctbits.module.IModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class BacoDifficultyModule implements IModule {

    @Override
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new BacoDifficultyHandler());
    }
}
