package me.neznamy.tab.platforms.bukkit.bossbar;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.legacy.bossbar.BossColor;
import com.viaversion.viaversion.api.legacy.bossbar.BossStyle;
import lombok.RequiredArgsConstructor;
import me.neznamy.tab.shared.platform.bossbar.BossBar;
import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.shared.chat.rgb.RGBUtils;
import me.neznamy.tab.platforms.bukkit.BukkitTabPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handler for 1.9+ players on 1.8 server using ViaVersion API.
 */
@RequiredArgsConstructor
public class BukkitBossBarVia implements BossBar {

    /** Player this handler belongs to */
    private final BukkitTabPlayer player;

    /** ViaVersion BossBars this 1.9+ player can see on 1.8 server */
    private final Map<UUID, com.viaversion.viaversion.api.legacy.bossbar.BossBar> viaBossBars = new HashMap<>();

    @Override
    public void create(@NotNull UUID id, @NotNull String title, float progress, @NotNull BarColor color, @NotNull BarStyle style) {
        if (viaBossBars.containsKey(id)) return;
        com.viaversion.viaversion.api.legacy.bossbar.BossBar bar = Via.getAPI().legacyAPI().createLegacyBossBar(
                RGBUtils.getInstance().convertToBukkitFormat(title, player.getVersion().getMinorVersion() >= 16),
                progress,
                BossColor.valueOf(color.name()),
                BossStyle.valueOf(style.getBukkitName()));
        viaBossBars.put(id, bar);
        bar.addPlayer(player.getPlayer().getUniqueId());
    }

    @Override
    public void update(@NotNull UUID id, @NotNull String title) {
        viaBossBars.get(id).setTitle(RGBUtils.getInstance().convertToBukkitFormat(title,
                player.getVersion().getMinorVersion() >= 16));
    }

    @Override
    public void update(@NotNull UUID id, float progress) {
        viaBossBars.get(id).setHealth(progress);
    }

    @Override
    public void update(@NotNull UUID id, @NotNull BarStyle style) {
        viaBossBars.get(id).setStyle(BossStyle.valueOf(style.getBukkitName()));
    }

    @Override
    public void update(@NotNull UUID id, @NotNull BarColor color) {
        viaBossBars.get(id).setColor(BossColor.valueOf(color.name()));
    }

    @Override
    public void remove(@NotNull UUID id) {
        viaBossBars.remove(id).removePlayer(player.getPlayer().getUniqueId());
    }
}