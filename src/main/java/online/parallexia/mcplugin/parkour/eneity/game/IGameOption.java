package online.parallexia.mcplugin.parkour.eneity.game;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SerializableAs("ParkourOption")
public interface IGameOption{
    int getMaxStep();
    List<Material> getBlockPartten();
    int getMaxStepSize();
    @NotNull
    default Vector getSize(int step){
        return Game.calcSize(step, getMaxStepSize());
    }
}

