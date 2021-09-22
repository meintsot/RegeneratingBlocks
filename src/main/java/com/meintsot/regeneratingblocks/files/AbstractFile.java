package com.meintsot.regeneratingblocks.files;

import com.meintsot.regeneratingblocks.RegeneratingBlocks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile {
    private File file;
    protected RegeneratingBlocks plugin;
    protected FileConfiguration config;

    public AbstractFile(RegeneratingBlocks plugin, String filename) {
        this.file = new File(plugin.getDataFolder(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
