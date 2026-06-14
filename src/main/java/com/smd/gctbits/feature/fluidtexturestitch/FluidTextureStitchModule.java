package com.smd.gctbits.feature.fluidtexturestitch;

import com.smd.gctbits.module.IModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FluidTextureStitchModule implements IModule {

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new FluidTextureStitchHandler());
    }
}
