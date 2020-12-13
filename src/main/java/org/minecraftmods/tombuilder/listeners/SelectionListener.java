package org.minecraftmods.tombuilder.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.minecraftmods.tombuilder.builder.Builder;
import org.minecraftmods.tombuilder.builder.BuilderManager;
import org.minecraftmods.tombuilder.exception.BuildingException;
import org.minecraftmods.tombuilder.points.ConstructionPlan;
import org.minecraftmods.tombuilder.utils.LocationUtils;

public class SelectionListener implements Listener {
    private LocationUtils locationUtils = new LocationUtils();
    private BuilderManager builderManager = new BuilderManager();

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent event){
        Builder builder;
        if(builderManager.builders.containsKey(event.getPlayer())){
            builder = builderManager.builders.get(event.getPlayer());
        }else{
            builder = new Builder(event.getPlayer());
            builderManager.builders.put(event.getPlayer(), builder);
        }
        boolean holdingStick = builder.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK;

        Action action = event.getAction();
        ItemStack item = event.getItem();

        if(builder.getActivePlan() != null && holdingStick && (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))){
            builder.getPlayer().sendMessage(ChatColor.RED+"Construction cancelled.");
            builder.setActivePlan(null);
        }
        if(holdingStick && action.equals(Action.RIGHT_CLICK_BLOCK)){
            Block block = builder.getPlayer().getTargetBlock(null, 200);
            if(null != item && builder.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK && !builder.isBuilding()){
                builder.getPlayer().sendMessage(ChatColor.YELLOW+"Selected "+ block.getBlockData().getMaterial().toString() +" block.");
                builder.setActivePlan(new ConstructionPlan(block.getLocation(), block, builder));
            }
            else if(null != item && builder.getPlayer().getInventory().getItemInMainHand().getType() == Material.STICK && builder.isBuilding()){
                try {
                    builder.getActivePlan().setEndLocation(block.getLocation());
                    builder.setActivePlan(null);
                } catch (BuildingException e) {
                    builder.getPlayer().sendMessage(ChatColor.RED+e.getErrorMessage());
                }
            }
        }
    }
}
