package com.meintsot.regeneratingblocks.commands;

import com.meintsot.regeneratingblocks.manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetRegenWand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.broadcastMessage(ChatColor.RED + "Warning! Only players may use this command!");
            return true;
        }
        Player player = (Player) sender;
        player.getInventory().addItem(ItemManager.wand);
        return true;
    }
}
