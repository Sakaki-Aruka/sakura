package com.github.sakakiaruka.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Cut implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player cutter = event.getPlayer();
        Block target = event.getBlock();
    }
}
