package com.smd.gctbits.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import com.smd.gctbits.Tags;
import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MOD_ID, name = Tags.MOD_NAME)
public class BitsConfig {

    @Config.Name("Debug Logging")
    @Config.Comment({
            "Enables additional debug logging for module and mixin decisions.",
            "启用模块和 Mixin 判定的额外调试日志。"
    })
    public static boolean debugLogging = false;

    @Config.Name("Log Module Registration")
    @Config.Comment({
            "Logs module execution and event handler registration.",
            "记录模块执行和事件监听器注册。"
    })
    public static boolean logModuleRegistration = true;

    @Config.Name("Wither Loot")
    public static final WitherLoot WitherLoot = new WitherLoot();

    @Config.Name("FastFlyBlockBreaking")
    public static final FastFlyBlockBreaking FastFlyBlockBreaking = new FastFlyBlockBreaking();

    @Config.Name("BacoDifficulty")
    public static final BacoDifficulty BacoDifficulty =
            new BacoDifficulty();

    @Config.Name("Fluid Texture Stitch")
    public static final FluidTextureStitch FluidTextureStitch = new FluidTextureStitch();

    public static class WitherLoot {

        @Config.RequiresMcRestart
        @Config.Name("Enabled")
        @Config.Comment({
                "Enables the custom Wither loot table mixin feature.",
                "启用凋灵的掉落表功能。"
        })
        public boolean enabled = true;
    }

    public static class FastFlyBlockBreaking {

        @Config.RequiresMcRestart
        @Config.Name("Enabled")
        @Config.Comment({
                "Boosts block breaking speed while flying in creative mode.",
                "在创造模式飞行时提升方块挖掘速度。"
        })
        public boolean enabled = true;

        @Config.RequiresMcRestart
        @Config.Name("Multiplier")
        @Config.Comment({
                "Block breaking speed multiplier while flying in creative mode.",
                "创造模式飞行时的方块挖掘速度倍率。"
        })
        @Config.RangeDouble(min = 0.1D, max = 10.0D)
        public double multiplier = 5.0D;
    }

    public static class BacoDifficulty {

        @Config.RequiresMcRestart
        @Config.Name("Enabled")
        @Config.Comment({
                "Restricts difficulty and gamerule commands to safe senders in singleplayer and dedicated server contexts.",
                "防止玩家和控制台外的任何东西改变世界难度和规则"
        })
        public boolean enabled = true;
    }

    public static class FluidTextureStitch {

        @Config.RequiresMcRestart
        @Config.Name("Enabled")
        @Config.Comment({
                "Registers still and flowing textures for every known fluid during texture stitch.",
                "在纹理拼接阶段为所有已知流体注册静止和流动纹理。"
        })
        public boolean enabled = true;
    }

    public static boolean isDebugLoggingEnabled() {
        return debugLogging;
    }

    public static boolean shouldLogModuleRegistration() {
        return logModuleRegistration;
    }

    static {
        ConfigAnytime.register(BitsConfig.class);
    }
}
