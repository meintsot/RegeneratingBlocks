package com.meintsot.regeneratingblocks.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;

    public static void init() {
        createWand();
    }

    private static void createWand() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_PURPLE + "This is wand.");
        lore.add(ChatColor.GREEN + "Right click on any block to be regenerated.");
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "WAND");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }
}
