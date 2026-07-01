package com.alwaysdropplus;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import com.alwaysdropplus.event.LootingEventHandler;

@Mod(
    modid = AlwaysDropPlus.MOD_ID,
    name = AlwaysDropPlus.MOD_NAME,
    version = AlwaysDropPlus.VERSION,
    acceptedMinecraftVersions = "[1.12.2]"
)
public class AlwaysDropPlus {
    public static final String MOD_ID = "alwaysdropplus";
    public static final String MOD_NAME = "AlwaysDropPlus";
    public static final String VERSION = "1.0.0";

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("=================================");
        logger.info("AlwaysDropPlus Pre-Initialization");
        logger.info("Version: " + VERSION);
        logger.info("=================================");
        
        Config.loadConfig();
        
        logger.info("Configuration loaded successfully");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Registering event handlers...");
        MinecraftForge.EVENT_BUS.register(LootingEventHandler.class);
        logger.info("AlwaysDropPlus initialized successfully");
    }

    public static Logger getLogger() {
        return logger;
    }
}
