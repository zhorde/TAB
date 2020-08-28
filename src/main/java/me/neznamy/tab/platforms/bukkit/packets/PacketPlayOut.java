package me.neznamy.tab.platforms.bukkit.packets;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.neznamy.tab.platforms.bukkit.Main;
import me.neznamy.tab.shared.ProtocolVersion;

/**
 * An abstract class extended by packet classes
 */
public abstract class PacketPlayOut {
	
	public abstract Object toNMS(ProtocolVersion clientVersion) throws Exception;

	public static List<Field> getFields(Class<?> clazz, Class<?> type){
		List<Field> list = new ArrayList<Field>();
		if (clazz == null) return list;
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.getType() == type) list.add(field);
		}
		return list;
	}

	public static Class<?> getNMSClass(String name) throws ClassNotFoundException {
		return Class.forName("net.minecraft.server." + Main.serverPackage + "." + name);
	}
}