package me.neznamy.tab.shared.features.interfaces;

import me.neznamy.tab.shared.ITabPlayer;
import me.neznamy.tab.shared.cpu.CPUFeature;
import me.neznamy.tab.shared.packets.PacketPlayOutPlayerInfo;

/**
 * Classes implementing this interface will receive PacketPlayOutPlayerInfo
 */
public interface PlayerInfoPacketListener {

	public PacketPlayOutPlayerInfo onPacketSend(ITabPlayer receiver, PacketPlayOutPlayerInfo info);
	public CPUFeature getCPUName();
}