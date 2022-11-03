package online.parallexia.mcplugin.parkour.game.factory;

import online.parallexia.mcplugin.parkour.game.eneity.ParkourRegion;
import org.bukkit.Location;

public class ParkourRegionFactory {
    public ParkourRegion newParkourRegion(Location location1, Location location2, String name){
        return new ParkourRegion(location1,location2,name);
    }
}
