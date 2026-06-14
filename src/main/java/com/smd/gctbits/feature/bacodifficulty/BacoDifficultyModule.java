package com.smd.gctbits.feature.bacodifficulty;

import com.smd.gctlib.api.module.IModularModule;
import net.minecraft.command.CommandDifficulty;
import net.minecraft.command.CommandGameRule;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BacoDifficultyModule implements IModularModule {

    public static String moduleComment() {
        return "Restricts difficulty and gamerule commands to safe senders in singleplayer and dedicated server contexts.\n"
                + "防止玩家和控制台外的任何东西改变世界难度和规则。";
    }

    @Override
    public void initServer(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onCommandEvent(CommandEvent event) {
        ICommand command = event.getCommand();
        if (!(command instanceof CommandDifficulty || command instanceof CommandGameRule)) {
            return;
        }

        ICommandSender sender = event.getSender();
        MinecraftServer server = sender.getServer();
        if (server == null) {
            event.setCanceled(true);
            return;
        }

        boolean isDedicated = server.isDedicatedServer();
        boolean isPlayer = sender instanceof EntityPlayer;
        boolean isPlayerMP = sender instanceof EntityPlayerMP;
        boolean isConsole = sender == server;

        boolean allow = isConsole || (isDedicated && isPlayer) || (!isDedicated && isPlayerMP);
        event.setCanceled(!allow);
    }
}
