package com.github.sakakiaruka.sakura.sakura.listeners;
;
import org.bukkit.Location;
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

        Set<Location> locations = new HashSet<>();
        Set<Location> visited = new HashSet<>();
        List<Location> stack = new ArrayList<>();
        World world = target.getWorld();
        try {
            search(target, locations, visited, stack);
        } catch (StackOverflowError e) {
            cutter.sendMessage("Too many blocks.");
            return;
        }


        locations.forEach(location -> {
            Block block = world.getBlockAt(location);
            block.getDrops().forEach(item -> cutter.getWorld().dropItem(cutter.getLocation(), item));
            block.setType(Material.AIR);
        });
    }

    private boolean isLog(Block block) {
        return block.getType().name().matches("^([A-Z_]+)_(LOG|STEM|WOOD)$");
    }


    private void search(Block start, Set<Location> locations, Set<Location> visited, List<Location> stack) {
        stack.remove(start.getLocation());
        if (isLog(start)) {
            locations.add(start.getLocation());
            visited.add(start.getLocation());
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    Block block = start.getWorld().getBlockAt(start.getX()+dx, start.getY()+dy, start.getZ()+dz);
                    if (isLog(block) && !stack.contains(block.getLocation()) && !visited.contains(block.getLocation())) stack.add(0, block.getLocation());
                    if (!stack.isEmpty()) search(start.getWorld().getBlockAt(stack.get(0)), locations, visited, stack);
                }
            }
        }
    }
}
