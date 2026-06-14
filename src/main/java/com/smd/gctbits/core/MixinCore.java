package com.smd.gctbits.core;

import com.smd.gctbits.Tags;
import com.smd.gctlib.api.config.GCTConfig;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SortingIndex(9999)
public class MixinCore implements IFMLLoadingPlugin, IEarlyMixinLoader {

    private static final String WITHER_LOOT_MIXIN = "mixins.gctbits.feature.wither_loot.json";
    private static final String WITHER_LOOT_MODULE = "wither_loot";

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
        return Arrays.asList(WITHER_LOOT_MIXIN);
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return !WITHER_LOOT_MIXIN.equals(mixinConfig)
                || GCTConfig.moduleEnabled(Tags.MOD_ID, WITHER_LOOT_MODULE, true);
    }
}
