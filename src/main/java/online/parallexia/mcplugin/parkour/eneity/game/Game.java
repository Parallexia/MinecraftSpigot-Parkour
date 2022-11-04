package online.parallexia.mcplugin.parkour.eneity.game;

import online.parallexia.mcplugin.parkour.Parkour;
import online.parallexia.mcplugin.parkour.event.game.StepOnBlockEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/*
* 游戏类
* 在服务器加载时创建，持久化存储，尽量避免重复创建占用服务器资源*/
public class Game implements IGame{

    //TODO:实现对输入的数值进行判断
    public Game(Location position,Vector lineVector,IGameOption option,Player player)
    {
        this.option = option;
        this.position = position;
        this.player = player;
        this.lineVector = lineVector;
        this.gameLogic = new GameLogic();
    }
    /*运行时添加的字段*/
    private final UUID uuid = UUID.randomUUID();
    //游戏区域的对角线，由游戏区域的跳数自动决定，矩形对角线的连线
    private final Vector lineVector;
    //跑酷区域标记点
    public final Location position;

    private final IGameOption option;

    //在游戏中的玩家
    private Player player;
    //游戏所使用的逻辑
    private final IParkourGameLogic gameLogic;
    private boolean isStarted = false;

    //计算游戏区域的大小，为正值
    public static Vector calcSize(int step, int maxStepSize){
        //确保两个跑酷区域的边界不会相互跳跃
        int hor = (step + 1) * maxStepSize;
        int vet = step + 2;
        return new Vector(hor,hor,vet);
    }

    @Override
    @NotNull
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public IGameOption getGameOption() {
        return option;
    }

    @Override
    public Location getLocation() {
        return position;
    }

    //获取游戏区域的大小
    @Override
    @NotNull
    public Vector getLineVector(){
        return lineVector.clone();
    }

    @Override
    public void start() throws NullPointerException{
        if (Objects.isNull(this.player))
            throw new NullPointerException("玩家不存在");
        this.isStarted = true;

    }

    @Override
    public void end() {
        this.isStarted = false;
    }

    @Override
    @NotNull
    public List<Player> getPlayer() {
        List<Player> list = new ArrayList<>();
        if (Objects.nonNull(player))
            list.add(player);
        return list;
    }

    @Override
    public boolean inGame() {
        return this.isStarted;
    }

    @Override
    public void setPlayer(@NotNull List<Player> players) {
        this.player = players.get(0);
    }

    @Override
    public IParkourGameLogic getGameLogic(){
        return gameLogic;
    }

    private static class GameLogic implements IParkourGameLogic{
        private final Random random = new Random();
        //玩家正在站立的方块
        private Block standBlock;
        //目标方块
        private Block targetBlock;

        /*判断玩家是否成功的踩到了方块上*/
        private boolean isSuccessfulOnTargetBlock(@NotNull IGame game,@NotNull Player player) {
            return false;
        }

        /*当游戏开始时执行的逻辑*/
        private void gameStart(@NotNull IGame game,@NotNull Player player){
            Block block = game.getLocation().getBlock();
        }

        /*生成新的方块并删去旧方块,由方块列表提供模式*/
        private void generateNewTarget(@NotNull IGame game,@NotNull Player player) {

            //生成的方块距离原点的个数，此处应该为正值
            int stepX = 0,stepY = 0,stepZ = 0;
            //下一个方块应该位于的位置
            int x,y,z;



            standBlock.setType(Material.AIR);
            standBlock = targetBlock;
            targetBlock = calcTargetLocation(stepX,stepY,stepZ,game).getBlock();

        }

        /*设置方块的属性*/
        private void setBlockData(@NotNull Block block, @NotNull List<Material> materials){
            Material material = materials.get(random.nextInt(0, materials.size()));
            block.setType(material);
        }

        /*计算一个可行的新的方块位置*/
        /*推荐在异步方法中运行该方法*/
        //TODO:提取方法，减少单个代码块的代码量
        private Location calcTargetLocation(int stepX,int stepY,int stepZ,IGame game) throws IllegalStateException{
            //得到游戏的向量方向
            int nx = game.getLineVector().getBlockX() < 0 ? -1:1;
            int ny = game.getLineVector().getBlockY() < 0 ? -1:1;
            int nz = game.getLineVector().getBlockZ() < 0 ? -1:1;

            boolean isOK = false;
            //玩家是否可以到达目标方块
            boolean isReachable = false;
            //目标步数是否符合
            boolean isStepAvail = false;

            int horDirection,verDirection;
            int maxStep = game.getGameOption().getMaxStep();
            int maxStepSize = game.getGameOption().getMaxStepSize();

            int tempStepX = stepX,tempStepY = stepY,tempStepZ = stepZ;
            int addHor,addVer;
            Location result = standBlock.getLocation().clone();
            while (!isOK){
                horDirection = random.nextInt(0,4);
                verDirection = random.nextInt(0,3);

                /*坐标相关逻辑*/
                //生成下一个方向
                switch (horDirection){
                    case 0:tempStepX++; break;
                    case 1:tempStepX--; break;
                    case 2:tempStepY++; break;
                    case 3:tempStepY--; break;
                    default:{Parkour.getInstance().getLogger().warning("游戏{" +game.getUUID() +"}" +"水平方向参数错误");
                        throw new IllegalStateException();}
                }

                switch (verDirection){
                    case 0:tempStepZ++; break;
                    case 1: break;
                    case 2:tempStepZ--; break;
                    default:{Parkour.getInstance().getLogger().warning("游戏{" +game.getUUID() +"}" +"垂直方向参数错误");
                        throw new IllegalStateException();}
                }

                /*判断当前确定的步数是否合规*/
                if (!checkStepAvail(tempStepX,maxStep)&&
                        checkStepAvail(tempStepY,maxStep)&&
                        checkStepAvail(tempStepZ,maxStep)) continue;

                //生成下个坐标
                Location original = game.getLocation();
                Location stand = standBlock.getLocation();

                addHor = random.nextInt(2,maxStepSize);
                addVer = random.nextInt(0,2);

                /*判断坐标是否在游戏区域中*/

                /*玩家能否到达的判断条件*/
                //TODO:改为更加精细的判断
                if (result.subtract(standBlock.getLocation().clone()).toVector().length() < 5)
                    isReachable = true;
                if (isReachable)
                    isOK = true;
            }
            return result;
        }

        private boolean checkStepAvail(int step,int maxStep){
            return step > 0 && step < maxStep;
        }

        @Override
        public void onPlayerStepOnBlockEventBeHandled(@NotNull StepOnBlockEvent event) {
            boolean isSuccessfulOnBlock = isSuccessfulOnTargetBlock(event.game,event.player);
            if (isSuccessfulOnBlock)
                generateNewTarget(event.game, event.player);
        }
    }
}

