package com.meintsot.regeneratingblocks.events;

import com.meintsot.regeneratingblocks.manager.BlockManager;
import com.meintsot.regeneratingblocks.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RegenClickEvent implements Listener {
    private BlockManager blockManager;

    public RegenClickEvent(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!event.getClickedBlock().getType().equals(Material.AIR) && event.getItem().getItemMeta()
                    .equals(ItemManager.wand.getItemMeta())) {
                Bukkit.broadcastMessage(ChatColor.GOLD + String.format("Selected block %d %d %d",
                        event.getClickedBlock().getX(), event.getClickedBlock().getY(), event.getClickedBlock().getZ()));
                blockManager.registerBlock(event.getClickedBlock());
            }
        }
    }
}
