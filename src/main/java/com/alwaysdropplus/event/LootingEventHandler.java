package com.alwaysdropplus.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import com.alwaysdropplus.AlwaysDropPlus;
import com.alwaysdropplus.Config;
import java.util.Collection;

@Mod.EventBusSubscriber
public class LootingEventHandler {

    @SubscribeEvent
    public static void onLivingDrop(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        
        if (isEntityBlacklisted(entity)) {
            return;
        }
        
        EntityLivingBase killer = event.getSource().getTrueSource() instanceof EntityLivingBase ? 
            (EntityLivingBase) event.getSource().getTrueSource() : null;
        
        if (Config.enableLootingScaling && killer != null) {
            handleLootingScaling(event, killer);
        }
        
        if (Config.enableMaxDrops) {
            handleMaxDrops(event);
        }
        
        if (Config.enableAlwaysXp) {
            handleAlwaysXp(event, killer);
        }
    }
    
    private static void handleLootingScaling(LivingDropsEvent event, EntityLivingBase killer) {
        int lootingLevel = 0;
        
        ItemStack weapon = killer.getHeldItemMainhand();
        if (weapon != null && !weapon.isEmpty()) {
            lootingLevel = EnchantmentHelper.getEnchantmentLevel(
                Enchantments.LOOTING, weapon);
        }
        
        if (lootingLevel > 0) {
            Collection<EntityItem> drops = event.getDrops();
            for (EntityItem entityItem : drops) {
                ItemStack stack = entityItem.getItem();
                if (stack != null && !stack.isEmpty()) {
                    int originalSize = stack.getCount();
                    float multiplier = (float) (1.0f + (lootingLevel * Config.lootingMultiplier));
                    int newSize = Math.max(1, Math.min((int) (originalSize * multiplier), 64));
                    
                    stack.setCount(newSize);
                    
                    if (Config.enableDebug) {
                        AlwaysDropPlus.getLogger().info(String.format(
                            "[AlwaysDropPlus] Looting scaling: %s x%d -> x%d (Looting %d)",
                            stack.getDisplayName(), originalSize, newSize, lootingLevel));
                    }
                }
            }
        }
    }
    
    private static void handleMaxDrops(LivingDropsEvent event) {
        Collection<EntityItem> drops = event.getDrops();
        
        for (EntityItem entityItem : drops) {
            ItemStack stack = entityItem.getItem();
            if (stack != null && !stack.isEmpty()) {
                int currentCount = stack.getCount();
                int boostedCount = Math.min(currentCount * 2, 64);
                
                if (currentCount < boostedCount) {
                    stack.setCount(boostedCount);
                    
                    if (Config.enableDebug) {
                        AlwaysDropPlus.getLogger().info(String.format(
                            "[AlwaysDropPlus] Max drop boost: %s x%d -> x%d",
                            stack.getDisplayName(), currentCount, boostedCount));
                    }
                }
            }
        }
    }
    
    private static void handleAlwaysXp(LivingDropsEvent event, EntityLivingBase killer) {
        EntityLivingBase entity = event.getEntityLiving();
        int xpToDrop = 0;
        
        // Only get XP if killer is a player
        if (killer instanceof EntityPlayer) {
            xpToDrop = entity.getExperiencePoints((EntityPlayer) killer);
        } else {
            xpToDrop = entity.getExperiencePoints(null);
        }
        
        if (xpToDrop > 0) {
            xpToDrop = (int) (xpToDrop * Config.xpMultiplier);
            
            int orbCount = 0;
            while (xpToDrop > 0) {
                int xpSize = Math.min(xpToDrop, 2477);
                EntityXPOrb orb = new EntityXPOrb(entity.world, entity.posX, entity.posY + 0.5, entity.posZ, xpSize);
                entity.world.spawnEntity(orb);
                xpToDrop -= xpSize;
                orbCount++;
            }
            
            if (Config.enableDebug) {
                AlwaysDropPlus.getLogger().info(String.format(
                    "[AlwaysDropPlus] XP dropped: %d orbs", orbCount));
            }
        }
    }
    
    private static boolean isEntityBlacklisted(EntityLivingBase entity) {
        if (Config.entityBlacklist.length == 0) {
            return false;
        }
        
        String entityName = entity.getClass().getSimpleName();
        for (String blacklisted : Config.entityBlacklist) {
            if (entityName.equalsIgnoreCase(blacklisted)) {
                return true;
            }
        }
        return false;
    }
}
