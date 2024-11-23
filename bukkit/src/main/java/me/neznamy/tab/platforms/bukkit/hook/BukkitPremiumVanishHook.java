package me.neznamy.tab.platforms.bukkit.hook;

import de.myzelyam.api.vanish.VanishAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.platforms.bukkit.BukkitTabPlayer;
import me.neznamy.tab.shared.hook.PremiumVanishHook;

/**
 * PremiumVanish hook for Bukkit.
 */
public class BukkitPremiumVanishHook extends PremiumVanishHook {

    @Override
    public boolean canSee(TabPlayer viewer, TabPlayer target) {
        return VanishAPI.canSee(((BukkitTabPlayer)viewer).getPlayer(), ((BukkitTabPlayer)target).getPlayer());
    }

    @Override
    public boolean isVanished(TabPlayer player) {
        return VanishAPI.isInvisible(((BukkitTabPlayer)player).getPlayer());
    }
}
