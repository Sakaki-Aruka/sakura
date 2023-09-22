package com.github.sakakiaruka.listeners;

import org.bukkit.TreeType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;

public class TreeGrowListener implements Listener {
    @EventHandler
    public void onStructureGrow(StructureGrowEvent event) {
        TreeType type = event.getSpecies();

        if (type.equals(TreeType.BROWN_MUSHROOM) || type.equals(TreeType.REDWOOD)) return;

        return;
    }
}
