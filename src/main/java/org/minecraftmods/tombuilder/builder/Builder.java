package org.minecraftmods.tombuilder.builder;

import org.bukkit.entity.Player;
import org.minecraftmods.tombuilder.points.ConstructionPlan;

public class Builder {
    private Player player;

    private ConstructionPlan activePlan;

    public Builder(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setActivePlan(ConstructionPlan plan){
        activePlan = plan;
    }

    public ConstructionPlan getActivePlan(){
        return activePlan;
    }

    public boolean isBuilding(){
        return null != activePlan;
    }

}