package br.com.cooldown.managers;

import br.com.cooldown.events.CooldownCancelEvent;
import br.com.cooldown.events.CooldownEndEvent;
import br.com.cooldown.events.CooldownStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownManager {

    private final Map<UUID, Map<String, Long>> cooldowns = new ConcurrentHashMap<>();

    public void startCooldown(Player player, String key, long duration) {
        UUID uuid = player.getUniqueId();
        long endTime = System.currentTimeMillis() + duration;

        cooldowns.putIfAbsent(uuid, new HashMap<>());
        cooldowns.get(uuid).put(key, endTime);

        Bukkit.getPluginManager().callEvent(new CooldownStartEvent(player, key));
    }

    public void cancelCooldown(Player player, String key) {
        UUID uuid = player.getUniqueId();
        if (cooldowns.containsKey(uuid) && cooldowns.get(uuid).remove(key) != null) {
            Bukkit.getPluginManager().callEvent(new CooldownCancelEvent(player, key));
        }
    }

    public boolean isOnCooldown(Player player, String key) {
        UUID uuid = player.getUniqueId();
        return cooldowns.containsKey(uuid) && cooldowns.get(uuid).getOrDefault(key, 0L) > System.currentTimeMillis();
    }

    public long getRemainingTime(Player player, String key) {
        UUID uuid = player.getUniqueId();
        return Math.max(0, cooldowns.getOrDefault(uuid, new HashMap<>()).getOrDefault(key, 0L) - System.currentTimeMillis());
    }

    public void checkCooldowns() {
        long currentTime = System.currentTimeMillis();
        cooldowns.forEach((uuid, playerCooldowns) -> {
            playerCooldowns.entrySet().removeIf(entry -> {
                if (entry.getValue() <= currentTime) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player != null) {
                        Bukkit.getPluginManager().callEvent(new CooldownEndEvent(player, entry.getKey()));
                    }
                    return true;
                }
                return false;
            });
        });
    }
}
