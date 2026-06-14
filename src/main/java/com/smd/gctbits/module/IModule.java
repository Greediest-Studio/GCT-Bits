package com.smd.gctbits.module;

import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

public interface IModule {

    default void preInit(FMLPreInitializationEvent event) {
    }

    default void preInitClient(FMLPreInitializationEvent event) {
    }

    default void preInitServer(FMLPreInitializationEvent event) {
    }

    default void init(FMLInitializationEvent event) {
    }

    default void initClient(FMLInitializationEvent event) {
    }

    default void initServer(FMLInitializationEvent event) {
    }

    default void postInit(FMLPostInitializationEvent event) {
    }

    default void postInitClient(FMLPostInitializationEvent event) {
    }

    default void postInitServer(FMLPostInitializationEvent event) {
    }

    default void serverStarting(FMLServerStartingEvent event) {
    }

    default void serverStopped(FMLServerStoppedEvent event) {
    }

    default void loadComplete(FMLLoadCompleteEvent event) {
    }
}
