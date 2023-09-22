package com.github.sakakiaruka.objects;

import com.github.sakakiaruka.interfaces.TreeData;
import org.bukkit.Material;

import java.util.List;

public class CustomTree implements TreeData {
    private int maxHeight;
    private int minHeight;
    private double probability;
    private List<Material> trunkWoodSpecies;
    private List<Material> leafSpecies;

    public CustomTree(int max, int min, double probability, List<Material> trunk, List<Material> leaf) {
        this.maxHeight = max;
        this.minHeight = min;
        this.probability = probability;
        this.trunkWoodSpecies = trunk;
        this.leafSpecies = leaf;
    }

    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override
    public int getMinHeight() {
        return this.minHeight;
    }

    @Override
    public double getProbability() {
        return this.probability;
    }

    @Override
    public List<Material> getTrunkWoodSpecies() {
        return this.trunkWoodSpecies;
    }

    @Override
    public List<Material> getLeafSpecies() {
        return this.leafSpecies;
    }
}
