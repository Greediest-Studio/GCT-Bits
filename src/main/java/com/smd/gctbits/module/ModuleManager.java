package com.smd.gctbits.module;

import com.smd.gctbits.GCTBits;
import com.smd.gctbits.config.BitsConfig;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {

    private final List<LoadedModule> modules = new ArrayList<>();
    private ModuleSide currentSide;

    public ModuleManager(ModuleSide currentSide) {
        this.currentSide = currentSide;
    }

    public void setCurrentSide(ModuleSide currentSide) {
        this.currentSide = currentSide;
    }

    public void registerModule(ModuleEntry entry) {
        if (!entry.isEnabled()) {
            if (BitsConfig.isDebugLoggingEnabled()) {
                GCTBits.LOGGER.info("Skipping module [{}]: disabled by config", entry.getModuleId());
            }
            return;
        }
        this.modules.add(new LoadedModule(entry, entry.createModule()));
    }

    public List<LoadedModule> getModules() {
        return Collections.unmodifiableList(this.modules);
    }

    public void preInit(FMLPreInitializationEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "preInit");
            module.instance.preInit(event);
            if (this.currentSide == ModuleSide.CLIENT) {
                this.logRun(module, "preInitClient");
                module.instance.preInitClient(event);
            } else {
                this.logRun(module, "preInitServer");
                module.instance.preInitServer(event);
            }
        }
    }

    public void init(FMLInitializationEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "init");
            module.instance.init(event);
            if (this.currentSide == ModuleSide.CLIENT) {
                this.logRun(module, "initClient");
                module.instance.initClient(event);
            } else {
                this.logRun(module, "initServer");
                module.instance.initServer(event);
            }
        }
    }

    public void postInit(FMLPostInitializationEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "postInit");
            module.instance.postInit(event);
            if (this.currentSide == ModuleSide.CLIENT) {
                this.logRun(module, "postInitClient");
                module.instance.postInitClient(event);
            } else {
                this.logRun(module, "postInitServer");
                module.instance.postInitServer(event);
            }
        }
    }

    public void serverStarting(FMLServerStartingEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "serverStarting");
            module.instance.serverStarting(event);
        }
    }

    public void serverStopped(FMLServerStoppedEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "serverStopped");
            module.instance.serverStopped(event);
        }
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        for (LoadedModule module : this.modules) {
            this.logRun(module, "loadComplete");
            module.instance.loadComplete(event);
        }
    }

    private void logRun(LoadedModule module, String phase) {
        if (BitsConfig.shouldLogModuleRegistration()) {
            GCTBits.LOGGER.info("Executing module [{}] during {}", module.entry.getModuleId(), phase);
        }
    }

    public static final class LoadedModule {

        private final ModuleEntry entry;
        private final IModule instance;

        private LoadedModule(ModuleEntry entry, IModule instance) {
            this.entry = entry;
            this.instance = instance;
        }

        public ModuleEntry getEntry() {
            return this.entry;
        }

        public IModule getInstance() {
            return this.instance;
        }
    }
}
