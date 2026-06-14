package com.smd.gctbits.feature.fixlinebreaks;

import com.smd.gctlib.api.module.IModularModule;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class FixLineBreaksModule implements IModularModule {

    public static String moduleComment() {
        return "Splits tooltip lines containing literal \\n or newline characters.\n"
                + "拆分包含字面量 \\n 或换行符的物品提示文本。";
    }

    @Override
    public void initClient(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTooltip(ItemTooltipEvent event) {
        if (event.getEntityPlayer() == null || !event.getEntityPlayer().world.isRemote) {
            return;
        }

        List<String> fixedTooltip = new ArrayList<>();
        boolean changed = false;
        for (String line : event.getToolTip()) {
            changed |= splitLine(line, fixedTooltip);
        }

        if (changed) {
            event.getToolTip().clear();
            event.getToolTip().addAll(fixedTooltip);
        }
    }

    private static boolean splitLine(String line, List<String> output) {
        boolean changed = false;
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '\\' && i + 1 < line.length() && line.charAt(i + 1) == 'n') {
                output.add(current.toString());
                current.setLength(0);
                changed = true;
                i++;
            } else if (c == '\n') {
                output.add(current.toString());
                current.setLength(0);
                changed = true;
            } else {
                current.append(c);
            }
        }
        output.add(current.toString());
        return changed;
    }
}
