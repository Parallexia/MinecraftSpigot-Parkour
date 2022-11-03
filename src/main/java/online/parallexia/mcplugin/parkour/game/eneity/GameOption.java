package online.parallexia.mcplugin.parkour.game.eneity;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SerializableAs("ParkourOption")
public class GameOption{
    public int step;
    public List<Material> blocks;

    @NotNull
    public Vector getSize(int step){
        return Game.calcSize(step);
    }
}

