package online.parallexia.mcplugin.parkour.eneity.game;

import online.parallexia.mcplugin.parkour.factory.game.GameFactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/*跑酷区域
* <p>一个跑酷区域可创建多个游戏实例，游戏实例的个数由游戏实例的大小决定</p>*/
public class ParkourRegion {
    //使用工厂类进行实例构建
    public ParkourRegion(@NotNull Location Location1,@NotNull Location Location2,String name){
        //检测选取区域是否合法
        //检测选取区域是否重叠

        this.location1 = Location1;
        this.location2 = Location2;
        this.name = name;
    }
    private Location location1;
    private Location location2;
    private final UUID uuid = UUID.randomUUID();
    private String name;
    //该跑酷区域所支持的最大实例数
    private int maxInstance;
    //在插件运行时，获取该区域的游戏实例列表
    private final transient List<IGame> instanceList = new ArrayList<>();
    //该实例是否被持久化
    public boolean isStored;

    //计算跑酷区域大小
    @NotNull
    private Vector calcRegionSize(){
        return calcRegionSize(this.location1,this.location2);
    }

    public static Vector calcRegionSize(@NotNull Location location1,@NotNull Location location2){
        int x1,y1,z1;
        int x2,y2,z2;
        x1 = location1.getBlockX();
        y1 = location1.getBlockY();
        z1 = location1.getBlockZ();

        x2 = location2.getBlockX();
        y2 = location2.getBlockY();
        z2 = location2.getBlockZ();

        int x,y,z;
        x = Math.abs(x2-x1);
        y = Math.abs(y2-y1);
        z = Math.abs(z2-z1);
        return new Vector(x,y,z);
    }
    //计算该跑酷区域支持的实例数量
    public static int calcMaxInstance(@NotNull Vector initGameSize,@NotNull ParkourRegion region){
        Vector size = calcRegionSize(region.location1,region.location2 );
        int x,y,z;
        x = size.getBlockX();
        y = size.getBlockY();
        z = size.getBlockZ();
        int count = Math.min(x / (initGameSize.getBlockX()),y / (initGameSize.getBlockY()));
        count = Math.min(count,z / (initGameSize.getBlockZ()));

        region.maxInstance = count;
        return count;
    }
    //计算跑酷实例起始点的位置
    private Dictionary<Location,Vector> calcInstLocationAndLineVector(@NotNull Vector initGameSize){
        Dictionary<Location,Vector> locations = new Hashtable<>();
        Vector size = calcRegionSize();
        int xvalue = 0,yvalue = 0,zvalue = 0;
        //计算对角线向量
        Vector line = location2.subtract(location1).toVector();
        int nx,ny,nz;
        nx = line.getBlockX() < 0 ? -1:1;
        ny = line.getBlockY() < 0 ? -1:1;
        nz = line.getBlockZ() < 0 ? -1:1;

        for (int i =0;Math.abs(zvalue) < size.getBlockZ();i++){
            for (int j=0;Math.abs(yvalue) < size.getBlockY();j++) {
                for (int k = 0;Math.abs(xvalue) < size.getBlockX() ; k++) {
                    xvalue = k * nx * initGameSize.getBlockX();
                    yvalue = j * ny * initGameSize.getBlockY();
                    zvalue = i * nz * initGameSize.getBlockZ();
                    locations.put (new Location(location1.getWorld(),
                            location1.getBlockX() + xvalue,
                            location1.getBlockY() + yvalue,
                            location1.getBlockZ() + zvalue),

                            new Vector(initGameSize.getBlockX() * nx,
                                    initGameSize.getBlockX() * ny,
                                    initGameSize.getBlockX() * nz
                                    )
                    );
                }
            }
        }
        return locations;
    }

    public void initInstance(@NotNull IGameOption option){
        Vector initGameSize = option.getSize(option.getMaxStep());
        Dictionary<Location,Vector> dictionary = calcInstLocationAndLineVector(initGameSize);
        Enumeration<Location> locations = dictionary.keys();

        Location location;
        Vector vector;
        while(locations.hasMoreElements()){
            location = locations.nextElement();
            vector = dictionary.get(location);
            this.instanceList.add(GameFactory.newGame(location,vector,option,null));
        }
    }

    public void newGame(Player player) throws ArrayStoreException{
        IGame game = null;
        while (instanceList.iterator().hasNext()) {
            IGame next = instanceList.iterator().next();
            if (!next.inGame()){
                game = next;
                break;
            }
        }
        if (Objects.isNull(game))
            throw new ArrayStoreException("游戏区域已满");
        List<Player> playerList = new ArrayList<>();
        playerList.add(player);
        game.setPlayer(playerList);
    }
    public UUID getUuid(){
        return uuid;
    }
    //name字段方法对
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    //获取该区域最多支持的游戏数目
    public int getMaxGameInstance(){
        return maxInstance;
    }

    //区域大小方法对
    public void setLocation1(@NotNull Location location){
        this.location1 = location;
    }
    public void setLocation2(@NotNull Location location){
        this.location2 = location;
    }
    @NotNull
    public Location getLocation1(){
        return this.location1;
    }
    @NotNull
    public Location getLocation2(){
        return this.location2;
    }

    //存储相关
    public boolean getStored(){
        return isStored;
    }
    public void setStored(Boolean stored){
        isStored = stored;
    }
}