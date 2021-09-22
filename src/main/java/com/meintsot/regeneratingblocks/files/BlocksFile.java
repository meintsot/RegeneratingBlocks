package com.meintsot.regeneratingblocks.files;

import com.meintsot.regeneratingblocks.RegeneratingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlocksFile extends AbstractFile {

    public BlocksFile(RegeneratingBlocks plugin) {
        super(plugin, "blocks.yml");
        List<Integer> pos = config.getIntegerList("Blocks.x");
        if (pos.size() == 0) {
            pos = new ArrayList<Integer>();
            config.set("Blocks.x", pos);
            config.set("Blocks.y", pos);
            config.set("Blocks.z", pos);
            List<String> worlds = new ArrayList<String>();
            config.set("Blocks.worlds", worlds);
            List<String> materials = new ArrayList<String>();
            config.set("Blocks.materials", materials);
            save();
        }
    }

    public void registerBlock(Block block) {
        List<Integer> posX = config.getIntegerList("Blocks.x");
        List<Integer> posY = config.getIntegerList("Blocks.y");
        List<Integer> posZ = config.getIntegerList("Blocks.z");
        posX.add(block.getX());
        posY.add(block.getY());
        posZ.add(block.getZ());
        config.set("Blocks.x", posX);
        config.set("Blocks.y", posY);
        config.set("Blocks.z", posZ);
        String worldName = block.getWorld().getName();
        List<String> worlds = config.getStringList("Blocks.worlds");
        worlds.add(worldName);
        config.set("Blocks.worlds", worlds);
        block.getType();
        List<String> materials = config.getStringList("Blocks.materials");
        materials.add(block.getType().toString());
        config.set("Blocks.materials", materials);
        save();
    }

    public HashMap<Location, Material> getBlocks() {
        HashMap<Location, Material> hashMap = new HashMap<Location, Material>();
        List<Integer> posX = config.getIntegerList("Blocks.x");
        List<Integer> posY = config.getIntegerList("Blocks.y");
        List<Integer> posZ = config.getIntegerList("Blocks.z");
        List<String> worlds = config.getStringList("Blocks.worlds");
        List<String> materials = config.getStringList("Blocks.materials");

        for (int i=0; i < posX.size(); i++) {
            World world = Bukkit.getWorld(worlds.get(i));
            Location location = new Location(world ,posX.get(i), posY.get(i), posZ.get(i));
            Material material = Material.getMaterial(materials.get(i));
            hashMap.put(location, material);
        }
        return hashMap;
    }
}
