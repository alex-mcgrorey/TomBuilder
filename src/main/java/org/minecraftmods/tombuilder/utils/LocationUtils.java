package org.minecraftmods.tombuilder.utils;

import org.bukkit.Location;
import org.minecraftmods.tombuilder.exception.BuildingException;

import java.util.ArrayList;

public final class LocationUtils {

    public ArrayList<Location> getLocationArray(Location loc1, Location loc2) throws BuildingException {
        ArrayList<Location> result = new ArrayList<>();
        int xDiff = loc1.getBlockX() - loc2.getBlockX();
        int yDiff = loc1.getBlockY() - loc2.getBlockY();
        int zDiff = loc1.getBlockZ() - loc2.getBlockZ();

        int xSign = xDiff < 0 ? 1 : -1;
        int ySign = yDiff < 0 ? 1 : -1;
        int zSign = zDiff < 0 ? 1 : -1;

        for(int x = 0; x <= Math.abs(xDiff); x++){
            for(int y = 0; y <= Math.abs(yDiff); y++){
                for(int z = 0; z <= Math.abs(zDiff); z++){
                    Location location = new Location(loc1.getWorld(), loc1.getBlockX()+(x*xSign), loc1.getBlockY()+(y*ySign), loc1.getBlockZ()+(z*zSign));
                    result.add(location);
                }
            }
        }
        return result;
    }
}
