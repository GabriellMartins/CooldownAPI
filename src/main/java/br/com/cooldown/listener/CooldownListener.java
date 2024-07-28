package br.com.cooldown.listener;

import br.com.cooldown.events.CooldownCancelEvent;
import br.com.cooldown.events.CooldownEndEvent;
import br.com.cooldown.events.CooldownStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;

public class CooldownListener implements Listener {


    //EXEMPLO COOLDOWN STRART
    @EventHandler
    public void onCooldownStart(CooldownStartEvent event) {
        Bukkit.getLogger().info(event.getPlayer().getName() + " iniciou um cooldown para " + event.getCooldownKey());
    }
    //EXEMPLO COOLDOWN CANCEL
    @EventHandler
    public void onCooldownCancel(CooldownCancelEvent event) {
        Bukkit.getLogger().info(event.getPlayer().getName() + " cancelou um cooldown para " + event.getCooldownKey());
    }

    //EXEMPLO COOLDOWN END
    @EventHandler
    public void onCooldownEnd(CooldownEndEvent event) {
        Bukkit.getLogger().info(event.getPlayer().getName() + " terminou um cooldown para " + event.getCooldownKey());
    }
}
