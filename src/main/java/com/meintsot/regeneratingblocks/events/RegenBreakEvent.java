package com.meintsot.regeneratingblocks.events;

import com.meintsot.regeneratingblocks.manager.BlockManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class RegenBreakEvent implements Listener {
    private BlockManager blockManager;

    public RegenBreakEvent(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onClick(BlockBreakEvent event) {
        if (blockManager.isRegenBlock(event.getBlock())) {
            Bukkit.broadcastMessage(ChatColor.GOLD + String.format("Broke %s!", event.getBlock().getType().toString()));
            blockManager.handleBlockDrop(event.getBlock(), event.getPlayer());
            blockManager.startRegenProcess(event.getBlock());
        }
    }
}
