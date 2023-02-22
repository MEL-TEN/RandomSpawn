package Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GameProcess {
	private static GameProcess singleton = null;

	public static GameProcess inst()
	{
		if(singleton == null) {
			singleton = new GameProcess();
		}
		return singleton;
	}

	private static SignalGenerator signalGenerator;

	GameProcess()
	{
		signalGenerator = SignalGenerator.inst();
	}

	public void CycleStart()
	{
		signalGenerator.Start();
	}

	public void CycleStop()
	{
		signalGenerator.Cancel();
	}

	public void CycleProgress()
	{
		/*
		Iterator<Map.Entry<Player, Location>> entries = Main.teleport_request.entrySet().iterator();
		while(entries.hasNext())
		{
			Map.Entry<Player, Location> entry = entries.next();
			Player player = entry.getKey();
			Location location = entry.getValue();



			Boolean success_teleport = false;

			success_teleport = player.teleport(location);

			if(success_teleport)
			{
				player.sendMessage(ChatColor.GREEN + "다른 위치로 이동중....");
				new PotionEffect(PotionEffectType.SLOW_FALLING, 100000,0).apply(player);
				Main.is_rsp_state.replace(player, true);
			}
			else
			{
				player.sendMessage(ChatColor.RED + "다른 위치로 이동 실패");
			}
			Main.teleport_request.remove(player);
		}
		*/

		//Main.teleport_request.forEach((player, location)->{
		//	Boolean success_teleport = false;
		//
		//	success_teleport = player.teleport(location);
		//
		//	if(success_teleport)
		//	{
		//		player.sendMessage(ChatColor.GREEN + "다른 위치로 이동중....");
		//		new PotionEffect(PotionEffectType.SLOW_FALLING, 100000,0).apply(player);
		//		Main.is_rsp_state.replace(player, true);
		//	}
		//	else
		//	{
		//		player.sendMessage(ChatColor.RED + "다른 위치로 이동 실패");
		//	}
		//	Main.teleport_request.remove(player);
		//});
	}
}
