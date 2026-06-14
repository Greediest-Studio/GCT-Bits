package com.smd.gctbits.core;

import com.smd.gctbits.config.BitsConfig;
import com.smd.gctbits.module.ModuleEntry;
import com.smd.gctbits.module.Modules;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SortingIndex(9999)
public class MixinCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    private static final Logger LOGGER = LogManager.getLogger("GCT Bits MixinCore");
    private static final Map<String, ModuleEntry> MIXIN_CONFIG_MODULES = createMixinConfigModules();

    private static Map<String, ModuleEntry> createMixinConfigModules() {
        Map<String, ModuleEntry> mappings = new LinkedHashMap<>();
        for (ModuleEntry entry : Modules.entries()) {
            for (String mixinConfig : entry.getMixinConfigs()) {
                mappings.put(mixinConfig, entry);
            }
        }
        return Collections.unmodifiableMap(mappings);
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(MIXIN_CONFIG_MODULES.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        ModuleEntry entry = MIXIN_CONFIG_MODULES.get(mixinConfig);
        if (entry == null) {
            return true;
        }

        boolean enabled = entry.isEnabled();
        if (BitsConfig.isDebugLoggingEnabled()) {
            LOGGER.info("Mixin config [{}] for module [{}] queue result={}", mixinConfig, entry.getModuleId(), enabled);
        }
        return enabled;
    }
}
