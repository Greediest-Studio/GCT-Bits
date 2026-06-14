package com.smd.gctbits.feature.bacodifficulty;

import net.minecraft.command.CommandDifficulty;
import net.minecraft.command.CommandGameRule;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BacoDifficultyHandler {

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
