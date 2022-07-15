package bluescreen9.minecraft.bukkit.seeingthroughtheheart;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.text.DecimalFormat;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
					protected static Plugin stth;
	
					@Override
					public void onEnable() {
								stth = Main.getPlugin(Main.class);
								getServer().getPluginManager().registerEvents(new EventListener(), stth);
								
								new BukkitRunnable() {
									public void run() {
										for (Player player:getServer().getOnlinePlayers()) {
												showPlayerHealth(player);
										}
										for (World world:getServer().getWorlds()) {
												for (LivingEntity entity:world.getEntitiesByClass(LivingEntity.class)) {
													if (entity instanceof Player) {
														continue;
													}
													showMobHealth(entity);
												}
										}
									}
								}.runTaskTimer(stth, 20L, 20L);
					}
					
					
					
					public static void showMobHealth(LivingEntity entity) {
						if (entity instanceof Wither || entity instanceof EnderDragon) {
							return;
						}
							double health = entity.getHealth();
							String orginName = entity.getCustomName();
							entity.setCustomName(ChatColor.RED + "♥ " + ChatColor.WHITE + format.format(health) + "\n" + orginName);
					}
					
					public static void showPlayerHealth(Player player) {
							Scoreboard board = player.getScoreboard();
							if (board == null) {
								board = Bukkit.getScoreboardManager().getNewScoreboard();
							}
							Objective ob = board.getObjective("health");
							if (ob == null) {
								ob = board.registerNewObjective("health", "health",ChatColor.RED + "♥ ");
							}
							
							ob.setDisplaySlot(DisplaySlot.BELOW_NAME);
							/*
							Team team = board.registerNewTeam("HEALTH");
							team.addEntry("health");
							team.setPrefix(ChatColor.RED + "♥ ");
							
							double health = player.getHealth();
							
							team.setSuffix(format.format(health));*/
					}
					
					private static DecimalFormat format = new DecimalFormat("0.0");
}
