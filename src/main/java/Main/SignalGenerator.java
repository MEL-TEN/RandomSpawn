package Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SignalGenerator {
	private static SignalGenerator singleton = null;

	public static SignalGenerator inst()
	{
		if(singleton == null){
			singleton = new SignalGenerator();
		}
		return singleton;
	}

	private static Plugin plugin;
	public static Plugin getPlugin() {return plugin;}
	public void setPlugin(Plugin plugin) {this.plugin = plugin;}

	private static GameProcess gameProcess;
	private int TaskID = -1;

	//SignalGenerator() { }

	public void Initialize() { gameProcess = GameProcess.inst(); }

	//SignalGenerator() { gameProcess = GameProcess.inst(); }

	//===============================
	public void Cancel() {
		if(TaskID != -1)
			Bukkit.getScheduler().cancelTask(TaskID);
	}

	public void Start()
	{
		TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), new SignalGenerator20Hz(), 1L, 1L);
	}

	class SignalGenerator20Hz implements Runnable {

		@Override
		public void run() {
			gameProcess.CycleProgress();
		}
	}
}
