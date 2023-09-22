package com.github.sakakiaruka.interfaces;

import org.bukkit.Material;

import java.util.List;

public interface TreeData {
    public abstract int getMaxHeight();
    public abstract int getMinHeight();
    public abstract double getProbability();
    public abstract List<Material> getTrunkWoodSpecies();
    public abstract List<Material> getLeafSpecies();
}
