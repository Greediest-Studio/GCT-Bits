package com.smd.gctbits.module;

import com.smd.gctbits.config.BitsConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ModuleEntry {

    private static final String MODULE_SUFFIX = "Module";

    private final Class<? extends IModule> moduleClass;
    private final String configName;
    private final String moduleId;
    private final List<String> mixinConfigs;

    private ModuleEntry(Class<? extends IModule> moduleClass, String... mixinConfigs) {
        this.moduleClass = moduleClass;
        this.configName = deriveConfigName(moduleClass);
        this.moduleId = toSnakeCase(this.configName);
        this.mixinConfigs = Collections.unmodifiableList(Arrays.asList(mixinConfigs));
    }

    public static ModuleEntry of(Class<? extends IModule> moduleClass, String... mixinConfigs) {
        return new ModuleEntry(moduleClass, mixinConfigs);
    }

    public Class<? extends IModule> getModuleClass() {
        return this.moduleClass;
    }

    public String getConfigName() {
        return this.configName;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public List<String> getMixinConfigs() {
        return this.mixinConfigs;
    }

    public boolean isEnabled() {
        try {
            Field configField = BitsConfig.class.getField(this.configName);
            Object config = configField.get(null);
            Field enabledField = config.getClass().getField("enabled");
            return enabledField.getBoolean(config);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                    "Module [" + this.moduleId + "] requires BitsConfig." + this.configName + ".enabled", e);
        }
    }

    public IModule createModule() {
        try {
            return this.moduleClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new IllegalStateException("Failed to create module [" + this.moduleId + "]", e);
        }
    }

    private static String deriveConfigName(Class<? extends IModule> moduleClass) {
        String simpleName = moduleClass.getSimpleName();
        if (simpleName.endsWith(MODULE_SUFFIX)) {
            return simpleName.substring(0, simpleName.length() - MODULE_SUFFIX.length());
        }
        return simpleName;
    }

    private static String toSnakeCase(String name) {
        StringBuilder builder = new StringBuilder(name.length() + 8);
        for (int i = 0; i < name.length(); i++) {
            char current = name.charAt(i);
            if (Character.isUpperCase(current) && i > 0) {
                char previous = name.charAt(i - 1);
                if (Character.isLowerCase(previous) || Character.isDigit(previous)) {
                    builder.append('_');
                }
            }
            builder.append(Character.toLowerCase(current));
        }
        return builder.toString();
    }
}
