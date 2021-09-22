package com.meintsot.regeneratingblocks.manager;

import com.meintsot.regeneratingblocks.RegeneratingBlocks;
import com.meintsot.regeneratingblocks.files.BlocksFile;
import com.meintsot.regeneratingblocks.runnable.BlockRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class BlockManager {
    private RegeneratingBlocks plugin;
    private BlocksFile blocksFile;
    private HashMap<Location, Integer> regenLocations = new HashMap<Location, Integer>();
    private HashMap<Location, Material> regenMaterial;

    public BlockManager(RegeneratingBlocks plugin) {
        this.plugin = plugin;
        blocksFile = new BlocksFile(plugin);
        loadBlocks();
    }

    public void registerBlock(Block block) {
        regenLocations.put(block.getLocation(), 30);
        regenMaterial.put(block.getLocation(), block.getType());
        blocksFile.registerBlock(block);
    }

    private void loadBlocks() {
//        Loading from files
        regenMaterial = blocksFile.getBlocks();
        for (Location location:regenMaterial.keySet()) {
            Bukkit.broadcastMessage(ChatColor.GOLD + String.format("(%f, %f, %f, %s)",
                    location.getX(), location.getY(), location.getZ(), location.getWorld().toString()));
            regenLocations.put(location, 30);
        }
    }

    public boolean isRegenBlock(Block block) {
        return regenMaterial.containsKey(block.getLocation());
    }

    public void startRegenProcess(Block block) {
        block.setType(Material.BEDROCK);

        BlockRunnable blockRunnable = new BlockRunnable(block, regenMaterial.get(block.getLocation()), Material.BEDROCK);
        blockRunnable.runTaskTimer(plugin, 0, 20);
    }

    public void save() {
        blocksFile.save();
    }

    public void handleBlockDrop(Block block, Player player) {
        Material material = null;
        switch (block.getType()) {
            case DIAMOND_ORE:
                material = Material.DIAMOND;
                break;
            case IRON_ORE:
                material = Material.IRON_INGOT;
                break;
            case GOLD_ORE:
                material = Material.GOLD_INGOT;
                break;
            case LAPIS_ORE:
                material = Material.LAPIS_LAZULI;
                break;
            case REDSTONE_ORE:
                material = Material.REDSTONE;
                break;
            case COAL_ORE:
                material = Material.COAL;
                break;
            default:
                material = block.getType();
        }
        ItemStack item = new ItemStack(material, 1);
        player.getInventory().addItem(item);
    }
}
