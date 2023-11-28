package com.github.sakakiaruka.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cut implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player cutter = event.getPlayer();
        Block target = event.getBlock();

        if (!isLog(target)) return;

        Set<Block> blocks = new HashSet<>();
        Set<Block> visited = new HashSet<>();
        List<Block> stack = new ArrayList<>();
        blocks.add(target);
        int x = target.getX();
        int y = target.getY();
        int z = target.getZ();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Block block = target.getWorld().getBlockAt(x+dx, y, z+dz);
                if (isLog(block)) stack.add(block);
            }
        }

        int limit = 500;
        int buffer = 0;
        for (int i = 0; i < limit; i++) {
            Block block = target.getWorld().getBlockAt(x, y+i, z);
            search(block, blocks, visited, stack);
            if (Math.abs(blocks.size() - buffer) == 0) break;
            buffer = blocks.size();
        }

        blocks.forEach(block -> block.getDrops().forEach(item -> cutter.getWorld().dropItem(cutter.getLocation(), item)));
    }

    private boolean isLog(Block block) {
        Material material = block.getType();
        String name = material.name();
        if (name.matches("^([A-Z_]+)_LOG$")) return true;
        if (name.matches("^([A-Z_]+)_STEM$")) return true;
        return false;
    }

    private void search(Block start, Set<Block> blocks, Set<Block> visited, List<Block> stack) {
        int x = start.getX();
        int y = start.getY() + 1;
        int z = start.getZ();
        World world = start.getWorld();

        stack.remove(start);
        if (isLog(start)) {
            blocks.add(start);
            visited.add(start);
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                Block block = world.getBlockAt(x+dx, y, z+dz);
                if (isLog(block) && !stack.contains(block) && !visited.contains(block)) stack.add(0, block);
                if (!stack.isEmpty()) search(stack.get(0), blocks, visited, stack);
            }
        }
    }
}
