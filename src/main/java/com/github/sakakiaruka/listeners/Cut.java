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
        search(target, blocks, visited, stack);

        blocks.forEach(block -> {
            block.getDrops().forEach(item -> cutter.getWorld().dropItem(cutter.getLocation(), item));
            block.setType(Material.AIR);
        });
    }

    private boolean isLog(Block block) {
        return block.getType().name().matches("^([A-Z_]+)_(LOG|STEM)$");
    }

    private boolean isLeaf(Block block) {
        return block.getType().name().matches("^([A-Z_]+)_(LEAVES|WART_BLOCK)$");
    }

    private void search(Block start, Set<Block> blocks, Set<Block> visited, List<Block> stack) {
        int x = start.getX();
        int y = start.getY();
        int z = start.getZ();
        World world = start.getWorld();

        stack.remove(start);
        if (isLog(start) || isLeaf(start)) {
            blocks.add(start);
            visited.add(start);
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Block block = world.getBlockAt(x+dx, y+dy, z+dz);
                    if ((isLog(block) || isLeaf(block)) && !stack.contains(block) && !visited.contains(block)) stack.add(0, block);
                    if (!stack.isEmpty()) search(stack.get(0), blocks, visited, stack);
                }
            }
        }
    }
}
