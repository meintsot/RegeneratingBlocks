package com.meintsot.regeneratingblocks.runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockRunnable extends BukkitRunnable {
    private Block block;
    private Material blockMaterial;
    private Material breakMaterial;
    private int time = 30;

    public BlockRunnable(Block block, Material blockMaterial, Material breakMaterial) {
        this.block = block;
        this.blockMaterial = blockMaterial;
        this.breakMaterial = breakMaterial;
    }

    public void breakBlock() {
        block.setType(breakMaterial);
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public void run() {
        if (time == 30) {
            block.setType(breakMaterial);
        }
        if (time == 0) {
            block.setType(blockMaterial);
            cancel();
            return;
        }
        time--;
    }
}
