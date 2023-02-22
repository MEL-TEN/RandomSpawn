package Main;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ServerConfig {
    public static String ServerName = "[RandomSpawn]";

    public static void ChatException(String args, Exception exception)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + ServerConfig.ServerName + " " + args + ":" + exception.getMessage());
    }

    public static void ChatAlert(String args)
    {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + ServerConfig.ServerName + " " + args);
    }

    public static long LimitX = 5000;
    public static long LimitZ = 5000;
    public static long FixedY = 320;
}
