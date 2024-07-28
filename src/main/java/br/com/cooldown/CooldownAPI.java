package br.com.cooldown;

import br.com.cooldown.managers.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CooldownAPI extends JavaPlugin {
    private static CooldownAPI instance;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;
        cooldownManager = new CooldownManager();

        getServer().getScheduler().runTaskTimer(this, () -> cooldownManager.checkCooldowns(), 20L, 20L);
    }

    public static CooldownAPI getInstance() {
        return instance;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}
