package me.neznamy.tab.platforms.velocity;

import lombok.NonNull;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.chat.EnumChatFormat;
import me.neznamy.tab.api.chat.IChatBaseComponent;
import me.neznamy.tab.shared.TabScoreboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VelocityScoreboard extends TabScoreboard {

    public VelocityScoreboard(TabPlayer player) {
        super(player);
    }

    @Override
    public void setDisplaySlot(DisplaySlot slot, @NonNull String objective) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardDisplayObjective", slot.ordinal(), objective);
    }

    @Override
    public void registerObjective0(@NonNull String objectiveName, @NonNull String title, boolean hearts) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardObjective", objectiveName, 0,
                player.getVersion().getMinorVersion() < 13 ? cutTo(title, 32) : title,
                IChatBaseComponent.optimizedComponent(title).toString(player.getVersion()), hearts ? 1 : 0);
    }

    @Override
    public void unregisterObjective0(@NonNull String objectiveName) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardObjective", objectiveName, 1);
    }

    @Override
    public void updateObjective0(@NonNull String objectiveName, @NonNull String title, boolean hearts) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardObjective", objectiveName, 2,
                player.getVersion().getMinorVersion() < 13 ? cutTo(title, 32) : title,
                IChatBaseComponent.optimizedComponent(title).toString(player.getVersion()), hearts ? 1 : 0);
    }

    @Override
    public void registerTeam0(@NonNull String name, String prefix, String suffix, String visibility, String collision, Collection<String> players, int options) {
        List<Object> args = new ArrayList<>();
        args.add("PacketPlayOutScoreboardTeam");
        args.add(name);
        args.add(0);
        args.add(players.size());
        args.addAll(players);
        String finalPrefix = player.getVersion().getMinorVersion() < 13 ? cutTo(prefix, 16) : prefix;
        String finalSuffix = player.getVersion().getMinorVersion() < 13 ? cutTo(suffix, 16) : suffix;
        args.add(finalPrefix);
        args.add(IChatBaseComponent.optimizedComponent(finalPrefix).toString(player.getVersion()));
        args.add(finalSuffix);
        args.add(IChatBaseComponent.optimizedComponent(finalSuffix).toString(player.getVersion()));
        args.add(options);
        args.add(visibility);
        args.add(collision);
        args.add(EnumChatFormat.lastColorsOf(prefix).ordinal());
        ((VelocityTabPlayer)player).sendPluginMessage(args.toArray());
    }

    @Override
    public void unregisterTeam0(@NonNull String name) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardTeam", name, 1, 0);
    }

    @Override
    public void updateTeam0(@NonNull String name, String prefix, String suffix, String visibility, String collision, int options) {
        List<Object> args = new ArrayList<>();
        args.add("PacketPlayOutScoreboardTeam");
        args.add(name);
        args.add(2);
        args.add(0);
        String finalPrefix = player.getVersion().getMinorVersion() < 13 ? cutTo(prefix, 16) : prefix;
        String finalSuffix = player.getVersion().getMinorVersion() < 13 ? cutTo(suffix, 16) : suffix;
        args.add(finalPrefix);
        args.add(IChatBaseComponent.optimizedComponent(finalPrefix).toString(player.getVersion()));
        args.add(finalSuffix);
        args.add(IChatBaseComponent.optimizedComponent(finalSuffix).toString(player.getVersion()));
        args.add(options);
        args.add(visibility);
        args.add(collision);
        args.add(EnumChatFormat.lastColorsOf(prefix).ordinal());
        ((VelocityTabPlayer)player).sendPluginMessage(args.toArray());
    }

    @Override
    public void setScore0(@NonNull String objective, @NonNull String playerName, int score) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardScore", objective, 0, playerName, score);
    }

    @Override
    public void removeScore0(@NonNull String objective, @NonNull String playerName) {
        ((VelocityTabPlayer)player).sendPluginMessage("PacketPlayOutScoreboardScore", objective, 1, playerName, 0);
    }
}
