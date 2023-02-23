package io.github.mel_ten.randomspawn

class GameProcess internal constructor() {
    init {
        signalGenerator = SignalGenerator.inst()
    }

    fun CycleStart() {
        signalGenerator!!.Start()
    }

    fun CycleStop() {
        signalGenerator!!.Cancel()
    }

    fun CycleProgress() {
        /*
		Iterator<Map.Entry<Player, Location>> entries = io.github.MEL_TEN.Main.teleport_request.entrySet().iterator();
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
				io.github.MEL_TEN.Main.is_rsp_state.replace(player, true);
			}
			else
			{
				player.sendMessage(ChatColor.RED + "다른 위치로 이동 실패");
			}
			io.github.MEL_TEN.Main.teleport_request.remove(player);
		}
		*/

        //io.github.MEL_TEN.Main.teleport_request.forEach((player, location)->{
        //	Boolean success_teleport = false;
        //
        //	success_teleport = player.teleport(location);
        //
        //	if(success_teleport)
        //	{
        //		player.sendMessage(ChatColor.GREEN + "다른 위치로 이동중....");
        //		new PotionEffect(PotionEffectType.SLOW_FALLING, 100000,0).apply(player);
        //		io.github.MEL_TEN.Main.is_rsp_state.replace(player, true);
        //	}
        //	else
        //	{
        //		player.sendMessage(ChatColor.RED + "다른 위치로 이동 실패");
        //	}
        //	io.github.MEL_TEN.Main.teleport_request.remove(player);
        //});
    }

    companion object {
        private var singleton: GameProcess? = null
        fun inst(): GameProcess? {
            if (singleton == null) {
                singleton = GameProcess()
            }
            return singleton
        }

        private var signalGenerator: SignalGenerator? = null
    }
}