package com.smd.gctbits;

import com.smd.gctbits.feature.bacodifficulty.BacoDifficultyModule;
import com.smd.gctbits.feature.fastflyblockbreaking.FastFlyBlockBreakingModule;
import com.smd.gctbits.feature.fixlinebreaks.FixLineBreaksModule;
import com.smd.gctbits.feature.fluidtexturestitch.FluidTextureStitchModule;
import com.smd.gctbits.feature.witherloot.WitherLootModule;
import com.smd.gctlib.api.module.ModularAPI;
import net.minecraftforge.fml.common.Mod;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:gctlib@[1.0,);required-after:mixinbooter@[10.2,);"
)
public class GCTBits {

    public GCTBits() {
        ModularAPI.register(this, r -> {
            r.register("wither_loot", WitherLootModule.class);
            r.register("fast_fly_block_breaking", FastFlyBlockBreakingModule.class);
            r.register("baco_difficulty", BacoDifficultyModule.class);
            r.register("fluid_texture_stitch", FluidTextureStitchModule.class);
            r.register("fix_line_breaks", FixLineBreaksModule.class);
        });
    }
}
