package org.minecraftmods.tombuilder.points;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.minecraftmods.tombuilder.builder.Builder;
import org.minecraftmods.tombuilder.exception.BuildingException;
import org.minecraftmods.tombuilder.utils.LocationUtils;

import java.util.ArrayList;
import java.util.Objects;

public class ConstructionPlan {
    private Location startLocation;
    private Block block;
    private Builder builder;

    private final LocationUtils locationUtils = new LocationUtils();

    public ConstructionPlan(Location startLocation, Block block, Builder builder){
        this.startLocation = startLocation;
        this.block = block;
        this.builder = builder;
    }

    public void setEndLocation(Location endLocation) throws BuildingException {
        ArrayList<Location> locations = locationUtils.getLocationArray(startLocation, endLocation);
        try {
            build(locations);
        }catch(BuildingException e){
            System.out.println("Error building: "+e.getErrorMessage());
        }
    }

    public void build(ArrayList<Location> locations) throws BuildingException{
        ArrayList<Location> validLocations = new ArrayList<Location>();
        for(Location location : locations){
            BlockData worldLocationData = Objects.requireNonNull(location.getWorld()).getBlockAt(location).getBlockData();
            if(worldLocationData.getMaterial().isAir() || worldLocationData.getMaterial().equals(Material.DIRT)) {
                validLocations.add(location);
            }
        }

        ItemStack requirement = new ItemStack(block.getBlockData().getMaterial(),validLocations.size());

        if(removeItems(builder.getPlayer().getInventory(), requirement, validLocations.size())) {
            for (Location location : validLocations) {
                Objects.requireNonNull(location.getWorld()).getBlockAt(location).setBlockData(block.getBlockData());
            }
            builder.getPlayer().sendMessage(ChatColor.GREEN + "Build Success");
        }
        else{
            builder.getPlayer().sendMessage(ChatColor.RED+Integer.toString(requirement.getAmount())+" "+requirement.getType().toString()+" required for construction");
        }
    }

     private boolean removeItems(Inventory inv, ItemStack item, Integer amount){
         if(inv.containsAtLeast(item, amount)){
            inv.removeItem(item);
            return true;
        }
        else{
            return false;
        }
    }
}