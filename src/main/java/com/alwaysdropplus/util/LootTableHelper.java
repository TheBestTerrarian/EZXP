package com.alwaysdropplus.util;

import net.minecraft.entity.EntityLivingBase;

public class LootTableHelper {
    public static int getMaxDropsForEntity(EntityLivingBase entity) {
        return 1;
    }
    
    public static boolean shouldEntityDropLoot(EntityLivingBase entity, EntityLivingBase killer) {
        return killer != null;
    }
}
