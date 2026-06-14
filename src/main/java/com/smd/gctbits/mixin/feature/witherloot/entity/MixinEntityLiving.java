package com.smd.gctbits.mixin.feature.witherloot.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving {

    @Unique
    private static final ResourceLocation WITHER_LOOT =
            LootTableList.register(new ResourceLocation("minecraft", "entities/boss/wither"));

    @Inject(method = "getLootTable", at = @At("HEAD"), cancellable = true)
    private void gctbits$useWitherLootTable(CallbackInfoReturnable<ResourceLocation> cir) {
        if ((Object) this instanceof EntityWither) {
            cir.setReturnValue(WITHER_LOOT);
        }
    }
}
