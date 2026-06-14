package com.smd.gctbits.proxy;

import com.smd.gctbits.module.ModuleManager;
import com.smd.gctbits.module.ModuleSide;
import com.smd.gctbits.module.Modules;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

public class CommonProxy {

    protected final ModuleManager moduleManager = new ModuleManager(ModuleSide.SERVER);

    public CommonProxy() {
        Modules.registerAll(this.moduleManager);
    }

    public void preInit(FMLPreInitializationEvent event) {
        this.moduleManager.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        this.moduleManager.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        this.moduleManager.postInit(event);
    }

    public void serverStarting(FMLServerStartingEvent event) {
        this.moduleManager.serverStarting(event);
    }

    public void serverStopped(FMLServerStoppedEvent event) {
        this.moduleManager.serverStopped(event);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        this.moduleManager.loadComplete(event);
    }
}
