package com.smd.gctbits.feature.fluidtexturestitch;

import com.smd.gctlib.api.module.IModularModule;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FluidTextureStitchModule implements IModularModule {

    public static String moduleComment() {
        return "Registers still and flowing textures for every known fluid during texture stitch.\n"
                + "在纹理拼接阶段为所有已知流体注册静止和流动纹理。";
    }

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTextureStitch(TextureStitchEvent.Pre event) {
        TextureMap map = event.getMap();

        for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            map.registerSprite(fluid.getStill());
            map.registerSprite(fluid.getFlowing());
        }
    }
}
