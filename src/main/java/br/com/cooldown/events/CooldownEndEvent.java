package br.com.cooldown.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CooldownEndEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final String cooldownKey;

    public CooldownEndEvent(Player player, String cooldownKey) {
        this.player = player;
        this.cooldownKey = cooldownKey;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCooldownKey() {
        return cooldownKey;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
