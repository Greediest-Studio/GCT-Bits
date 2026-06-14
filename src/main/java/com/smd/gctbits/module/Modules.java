package com.smd.gctbits.module;

import com.smd.gctbits.feature.fastflyblockbreaking.FastFlyBlockBreakingModule;
import com.smd.gctbits.feature.bacodifficulty.BacoDifficultyModule;
import com.smd.gctbits.feature.fluidtexturestitch.FluidTextureStitchModule;
import com.smd.gctbits.feature.witherloot.WitherLootModule;

import java.util.Arrays;
import java.util.List;

public final class Modules {

    private static final List<ModuleEntry> MODULES = Arrays.asList(
            ModuleEntry.of(WitherLootModule.class, "mixins.gctbits.feature.wither_loot.json"),
            ModuleEntry.of(FastFlyBlockBreakingModule.class),
            ModuleEntry.of(BacoDifficultyModule.class),
            ModuleEntry.of(FluidTextureStitchModule.class)
    );

    private Modules() {
    }

    public static void registerAll(ModuleManager manager) {
        for (ModuleEntry entry : entries()) {
            manager.registerModule(entry);
        }
    }

    public static List<ModuleEntry> entries() {
        return MODULES;
    }
}
