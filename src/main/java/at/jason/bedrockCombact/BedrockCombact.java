package at.jason.bedrockCombact;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BedrockCombact extends JavaPlugin implements Listener {

    private static final double BEDROCK_ATTACK_SPEED = 1024.0D;
    private static final String LOCATOR_BAR_ENABLED_PATH = "locator-bar.enabled";

    private final Map<UUID, Double> originalAttackSpeed = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);

        applyLocatorBarSetting();

        for (Player player : Bukkit.getOnlinePlayers()) {
            applyBedrockCombat(player);
        }

        getLogger().info("1.9 PvP-Cooldown deaktiviert - Attack Speed auf 1024 gesetzt.");
        getLogger().info("Locator Bar ist " + (isLocatorBarEnabled() ? "aktiviert" : "deaktiviert") + ".");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            restoreAttackSpeed(player);
        }

        originalAttackSpeed.clear();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        applyBedrockCombat(event.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Bukkit.getScheduler().runTask(this, () -> applyBedrockCombat(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Bukkit.getScheduler().runTask(this, () -> applyBedrockCombat(event.getPlayer()));
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        applyLocatorBarSetting(event.getWorld());
    }

    private void applyBedrockCombat(Player player) {
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.ATTACK_SPEED);
        if (attackSpeedAttribute == null) {
            return;
        }

        originalAttackSpeed.putIfAbsent(player.getUniqueId(), attackSpeedAttribute.getBaseValue());

        if (attackSpeedAttribute.getBaseValue() != BEDROCK_ATTACK_SPEED) {
            attackSpeedAttribute.setBaseValue(BEDROCK_ATTACK_SPEED);
        }
    }

    private void restoreAttackSpeed(Player player) {
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.ATTACK_SPEED);
        Double originalValue = originalAttackSpeed.remove(player.getUniqueId());

        if (attackSpeedAttribute == null || originalValue == null) {
            return;
        }

        attackSpeedAttribute.setBaseValue(originalValue);
    }

    private boolean isLocatorBarEnabled() {
        return getConfig().getBoolean(LOCATOR_BAR_ENABLED_PATH, false);
    }

    private void applyLocatorBarSetting() {
        for (World world : Bukkit.getWorlds()) {
            applyLocatorBarSetting(world);
        }
    }

    private void applyLocatorBarSetting(World world) {
        world.setGameRule(GameRule.LOCATOR_BAR, isLocatorBarEnabled());
    }
}
