package org.golde.bukkit.corpsereborn;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ConfigData {

	private static int corpseTime;
	private static boolean onDeath;
	private static boolean lootingInventory;
	private static boolean showTags;
	private static String worldName;
	private static World world;
	private static String guiName;
	private static String username;
	private static boolean autoDespawn;
	private static String finishLootingMessage;
	private static boolean newHitbox;

	public static int getCorpseTime() {return corpseTime;}
	public static boolean isOnDeath() {return onDeath;}
	public static boolean hasLootingInventory() {return lootingInventory;}
	public static boolean showTags() {return showTags;}
	public static World getWorld(){	return world;}
	public static boolean getNewHitbox(){return newHitbox;}
	public static String getInventoryName(Player p){return guiName.replaceAll("%corpse%", p.getName()).replaceAll("&", "�");}
	public static String getUsername(Player p){return username.replaceAll("%corpse%", p.getName()).replaceAll("&", "�");}
	public static String finishLootingMessage(String name){
		if(finishLootingMessage.equalsIgnoreCase("none")){
			return null;
		}
		return finishLootingMessage.replaceAll("%corpse%", name).replaceAll("&", "�");
	}

	public static boolean shouldDespawnAfterLoot(){
		if(lootingInventory && autoDespawn){
			return true;
		}
		return false;
	}

	public static void load() {
		try {
			corpseTime = Main.getPlugin().getConfig().getInt("corpse-time");
			onDeath = Main.getPlugin().getConfig().getBoolean("on-death");
			lootingInventory = Main.getPlugin().getConfig().getBoolean("looting-inventory");
			showTags = Main.getPlugin().getConfig().getBoolean("show-tags");
			worldName = Main.getPlugin().getConfig().getString("world");
			guiName = Main.getPlugin().getConfig().getString("gui-title");
			username = Main.getPlugin().getConfig().getString("username-format");
			autoDespawn = Main.getPlugin().getConfig().getBoolean("despawn-after-looted");
			finishLootingMessage = Main.getPlugin().getConfig().getString("finish-looting-message");
			newHitbox = Main.getPlugin().getConfig().getBoolean("new-hitboxes");

			if(worldName.equalsIgnoreCase("all")){
				world = null;
			}else{
				if(Bukkit.getWorld(worldName) != null){
					world = Bukkit.getWorld(worldName);
				}else{
					world = null;
					Util.severe("================================");
					Util.severe("Could not find the world: " + worldName);
					Util.severe("Defaulting to ALL WORLDS");
					Util.severe("================================");
				}
			}
			Util.info("Config successfully loaded.");

		} catch (Exception e) {
			Util.severe("================================");
			Util.severe("Could not load config!");
			Util.severe("Is it configured properly?");
			Util.severe("Have you deleted old configs?");
			Util.severe("================================");
			Main.getPlugin().cont = false;
			Bukkit.getServer().getPluginManager().disablePlugin(Main.getPlugin());
		}
	}

}