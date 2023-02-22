package Main;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.*;
import java.util.HashMap;

public final class Main extends JavaPlugin implements Listener {

	public static volatile HashMap<Player, Boolean> is_rsp_state;
	//public static volatile HashMap<Player, Location> teleport_request;
	//private static GameProcess gameProcess;
	//private static SignalGenerator signalGenerator;

	@Override
	public void onEnable() {
		// Plugin startup logic
		ServerConfig.ChatAlert( "Loading..");

		getServer().getPluginManager().registerEvents(this, this);
		is_rsp_state = new HashMap<Player, Boolean>();
		//teleport_request = new HashMap<Player, Location>();

		//gameProcess = GameProcess.inst();
		//signalGenerator = signalGenerator.inst();
		//signalGenerator.setPlugin(this);
		//signalGenerator.Initialize();

		//gameProcess.CycleStart();

		ServerConfig.ChatAlert("Loading Compleate");
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		ServerConfig.ChatAlert( "Unloading..");



		ServerConfig.ChatAlert( "Unloading Compleate");
	}

	@EventHandler
	public void join(PlayerJoinEvent event)
	{
		event.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		if(is_rsp_state.get(event.getPlayer()) == null) is_rsp_state.put(event.getPlayer(), false);
	}

	@EventHandler
	public void exit(PlayerQuitEvent event)
	{
		if(is_rsp_state.get(event.getPlayer()))
		{
			is_rsp_state.remove(event.getPlayer());
			event.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
		}
	}

	@EventHandler
	public void evvvv(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if(is_rsp_state.get(player))
		{
			Location player_position = event.getTo();
			int playerX = player_position.getBlockX();
			int playerZ = player_position.getBlockZ();
			int playerY = player_position.getBlockY();

			World world = Bukkit.getWorld("world");
			Block block = world.getHighestBlockAt(playerX, playerZ);
			if (block.getLocation().getBlockY() + 3 > playerY)
			{
				is_rsp_state.replace(player, false);
				player.removePotionEffect(PotionEffectType.SLOW_FALLING);
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {

		if(args.length == 0)
		{
			sender.sendMessage(ChatColor.RED + "플레이어 이름을 입력해주세요.");
			return true;
		}

		Player player = Bukkit.getPlayer(args[0]);
		if(!sender.isOp())
		{
			sender.sendMessage(ChatColor.RED + "권한이 없습니다.");
			return true;
		}

		if(player == null)
		{
			sender.sendMessage(ChatColor.RED + sender.getName() + "플레이어를 찾을 수 없습니다.");
			return true;
		}

		if(is_rsp_state.get(player))
		{
			sender.sendMessage(ChatColor.RED + sender.getName() + "이미 명령어를 사용했습니다.");
			return true;
		}

		int calculatedX;
		int calculatedZ;
		World world = Bukkit.getWorld("wild");
		Location location;
		double ratio;

		ratio = (Math.random()*2)-1;
		calculatedX = (int)(ratio * (double)ServerConfig.LimitX);
		ratio = (Math.random()*2)-1;
		calculatedZ = (int)(ratio * (double)ServerConfig.LimitZ);
		location = new Location(world, calculatedX, ServerConfig.FixedY, calculatedZ);

		Boolean success_teleport = false;

		success_teleport = player.teleport(location);

		if(success_teleport)
		{
			//player.sendMessage(ChatColor.GREEN + "다른 위치로 이동중....");
			new PotionEffect(PotionEffectType.SLOW_FALLING, 100000,0).apply(player);
			Main.is_rsp_state.replace(player, true);
		}
		else
		{
			player.sendMessage(ChatColor.RED + "다른 위치로 이동 실패");
		}

		/*Thread thread = new Thread(() -> {
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

		//p.getStatistic(Statistic.PLAYER_KILLS);
		//p.setStatistic(Statistic.PLAYER_KILLS, 0);

		//p.getStatistic(Statistic.DEATHS);
		//p.setStatistic(Statistic.DEATHS, 0);

		return true;
	}
}
