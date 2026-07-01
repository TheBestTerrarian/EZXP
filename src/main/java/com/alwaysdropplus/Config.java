package com.alwaysdropplus;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class Config {
    private static Configuration config;
    
    public static boolean enableMaxDrops = true;
    public static boolean enableLootingScaling = true;
    public static boolean enableAlwaysXp = true;
    public static boolean enableDebug = false;
    
    public static double lootingMultiplier = 1.0;
    public static float xpMultiplier = 1.0f;
    public static String[] entityBlacklist = {};

    public static void loadConfig() {
        try {
            File configDir = new File("config");
            if (!configDir.exists()) {
                configDir.mkdirs();
            }
            
            File configFile = new File(configDir, "AlwaysDropPlus.cfg");
            config = new Configuration(configFile);
            
            config.load();
            
            String generalCategory = Configuration.CATEGORY_GENERAL;
            enableMaxDrops = config.getBoolean("enableMaxDrops", generalCategory, true, 
                "If true, mobs will drop the maximum quantity of items from their loot tables");
            enableLootingScaling = config.getBoolean("enableLootingScaling", generalCategory, true,
                "If true, Looting enchantment will further increase dropped item quantities");
            enableAlwaysXp = config.getBoolean("enableAlwaysXp", generalCategory, true,
                "If true, all mobs will drop experience regardless of how they die");
            enableDebug = config.getBoolean("enableDebug", generalCategory, false,
                "If true, debug messages will be logged to console");
            
            String scalingCategory = "scaling";
            lootingMultiplier = config.getFloat("lootingMultiplier", scalingCategory, 1.0f, 0.5f, 5.0f,
                "Base multiplier for Looting enchantment scaling (applied per level)");
            xpMultiplier = config.getFloat("xpMultiplier", scalingCategory, 1.0f, 0.1f, 10.0f,
                "Multiplier for experience drops");
            
            String blacklistCategory = "blacklist";
            entityBlacklist = config.getStringList("entityBlacklist", blacklistCategory, new String[]{},
                "List of entity types to exclude from AlwaysDropPlus mechanics");
            
            if (config.hasChanged()) {
                config.save();
            }
            
            AlwaysDropPlus.getLogger().info("Configuration loaded successfully");
            if (enableDebug) {
                AlwaysDropPlus.getLogger().info("Debug mode enabled");
            }
            
        } catch (Exception e) {
            AlwaysDropPlus.getLogger().error("Error loading configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
