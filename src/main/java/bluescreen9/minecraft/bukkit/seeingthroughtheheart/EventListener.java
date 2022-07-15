package bluescreen9.minecraft.bukkit.seeingthroughtheheart;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EventListener implements Listener{
					@EventHandler
					public void onEntityHurt(EntityDamageEvent event) {
							Entity entity = event.getEntity();
							if (entity instanceof LivingEntity && !(entity instanceof Player)) {
									Main.showMobHealth((LivingEntity) entity);
									return;
							}
							
							if (entity instanceof Player) {
								Main.showPlayerHealth((Player)entity);
							}
					}
					
					@EventHandler
					public void onEntityHeal(EntityRegainHealthEvent event) {
						Entity entity = event.getEntity();
						if (entity instanceof LivingEntity && !(entity instanceof Player)) {
								Main.showMobHealth((LivingEntity) entity);
								return;
						}
						
						if (entity instanceof Player) {
							Main.showPlayerHealth((Player)entity);
						}
					}
}
