package com.meintsot.regeneratingblocks;

import com.meintsot.regeneratingblocks.commands.GetRegenWand;
import com.meintsot.regeneratingblocks.events.RegenBreakEvent;
import com.meintsot.regeneratingblocks.events.RegenClickEvent;
import com.meintsot.regeneratingblocks.manager.BlockManager;
import com.meintsot.regeneratingblocks.manager.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegeneratingBlocks extends JavaPlugin {
    private BlockManager blockManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        blockManager = new BlockManager(this);
        ItemManager.init();
        getCommand("regenwand").setExecutor(new GetRegenWand());
        getServer().getPluginManager().registerEvents(new RegenClickEvent(blockManager), this);
        getServer().getPluginManager().registerEvents(new RegenBreakEvent(blockManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        blockManager.save();
    }
}
