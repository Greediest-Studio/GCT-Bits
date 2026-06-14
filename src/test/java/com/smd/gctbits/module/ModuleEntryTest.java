package com.smd.gctbits.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleEntryTest {

    @Test
    void derivesConfigNameAndModuleIdFromModuleClass() {
        ModuleEntry entry = Modules.entries().get(0);

        assertEquals("WitherLoot", entry.getConfigName());
        assertEquals("wither_loot", entry.getModuleId());
    }

    @Test
    void exposesMixinConfigsWithoutCreatingModules() {
        ModuleEntry entry = Modules.entries().get(0);

        assertEquals(
                "mixins.gctbits.feature.wither_loot.json",
                entry.getMixinConfigs().get(0)
        );
    }

}
