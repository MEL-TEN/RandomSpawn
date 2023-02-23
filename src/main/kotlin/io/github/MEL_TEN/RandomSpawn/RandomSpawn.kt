package io.github.mel_ten.randomspawn

import io.github.mel_ten.randomspawn.config.ConfigManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RandomSpawn : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: RandomSpawn

        lateinit var Config: ConfigManager
    }

    val is_rsp_state: HashMap<Player, Boolean> = hashMapOf()

    // public static volatile HashMap<Player, Location> teleport_request;
    // private static GameProcess gameProcess;
    // private static SignalGenerator signalGenerator;
    override fun onEnable() {
        instance = this
        Config = ConfigManager()

        // Plugin startup logic
        Config.ChatAlert("Loading..")
        server.pluginManager.registerEvents(this, this)

        // config load
        Config.load()

        // teleport_request = new HashMap<Player, Location>();

        // gameProcess = GameProcess.inst();
        // signalGenerator = signalGenerator.inst();
        // signalGenerator.setPlugin(this);
        // signalGenerator.Initialize();

        // gameProcess.CycleStart();
        Config.ChatAlert("Loading Compleate")
    }

    override fun onDisable() {
        // Plugin shutdown logic

        // config save
        Config.save()

        Config.ChatAlert("Unloading..")
        Config.ChatAlert("Unloading Compleate")
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.removePotionEffect(PotionEffectType.SLOW_FALLING)
        is_rsp_state.putIfAbsent(event.player, false)
    }

    @EventHandler
    fun exit(event: PlayerQuitEvent) {
        if (is_rsp_state[event.player] == true) {
            is_rsp_state.remove(event.player)
            event.player.removePotionEffect(PotionEffectType.SLOW_FALLING)
        }
    }

    @EventHandler
    fun evvvv(event: PlayerMoveEvent)
    {
        val player = event.player
        if (is_rsp_state[player] == true) {
            val playerPosition = event.to
            val playerX = playerPosition.blockX
            val playerZ = playerPosition.blockZ
            val playerY = playerPosition.blockY
            val world = Bukkit.getWorld("world")
            if (world != null) // Prevent NPE
            {
                val block = world.getHighestBlockAt(playerX, playerZ)
                if (block.location.blockY + 3 > playerY) {
                    is_rsp_state[player] = false
                    player.removePotionEffect(PotionEffectType.SLOW_FALLING)
                }
            } else {
                throw NullPointerException("")
            }
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage(ChatColor.RED.toString() + "플레이어 이름을 입력해주세요.")
            return true
        }
        val player = Bukkit.getPlayer(args[0])
        if (!sender.isOp) {
            sender.sendMessage(ChatColor.RED.toString() + "권한이 없습니다.")
            return true
        }
        if (player == null) {
            sender.sendMessage(ChatColor.RED.toString() + sender.name + "플레이어를 찾을 수 없습니다.")
            return true
        }
        if (is_rsp_state[player] == true) {
            sender.sendMessage(ChatColor.RED.toString() + sender.name + "이미 명령어를 사용했습니다.")
            return true
        }

        val calculatedX: Int
        val calculatedZ: Int
        val world = Bukkit.getWorld("wild")
        var ratio: Double = Math.random() * 2 - 1
        calculatedX = (ratio * Config.LimitX.toDouble()).toInt()
        ratio = Math.random() * 2 - 1
        calculatedZ = (ratio * Config.LimitZ.toDouble()).toInt()
        val location: Location = Location(world, calculatedX.toDouble(), Config.FixedY.toDouble(), calculatedZ.toDouble())
        val successTeleport: Boolean = player.teleport(location)
        if (successTeleport) {
            //player.sendMessage("${ChatColor.GREEN}다른 위치로 이동중....");
            PotionEffect(PotionEffectType.SLOW_FALLING, 100000, 0).apply(player)
            is_rsp_state[player] = true
        } else {
            player.sendMessage(ChatColor.RED.toString() + "다른 위치로 이동 실패")
        }

        /* Thread thread = new Thread(() -> {
			Block block;
			Chunk chunk;
			int calculatedX;
			int calculatedZ;
			World world = Bukkit.getWorld("world");
			Location location;
			double ratio;

			ratio = (Math.random()*2)-1;
			calculatedX = (int)(ratio * (double)ServerConfig.LimitX);
			ratio = (Math.random()*2)-1;
			calculatedZ = (int)(ratio * (double)ServerConfig.LimitZ);

			chunk = world.getChunkAt(calculatedX, calculatedZ);
			if(!chunk.isLoaded()) world.loadChunk(chunk);
			block = world.getHighestBlockAt(calculatedX, calculatedZ);

			block.getBlockData().getAsString();

			location = new Location(world, calculatedX, block.getLocation().getBlockY() + ServerConfig.AddedY, calculatedZ);

			teleport_request.put(player, location);
		});
		thread.start();*/

        // p.getStatistic(Statistic.PLAYER_KILLS);
        // p.setStatistic(Statistic.PLAYER_KILLS, 0);

        // p.getStatistic(Statistic.DEATHS);
        // p.setStatistic(Statistic.DEATHS, 0);
        return true
    }
}