/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.inventory.ResetInventory) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:11.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.inventory;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResetInventory {

    @Getter
    private UUID uuid;

    @Getter
    private List<Map.Entry<Integer, ResetItem>> items;

    @Getter
    private float exp;

    @Getter
    private List<PotionEffect> effects;

    @Getter
    private boolean isSaved = false;

    public ResetInventory(UUID uuid, boolean forceClean){
        this.uuid = uuid;

        save();

        if(forceClean)
            clean();

    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public void clean(){

        if(!isSaved)
            save();


    }

    public void save(){

        final Player player = getPlayer();

        this.exp = player.getExp();

        Inventory inventory = player.getInventory();

        for(int slot = 0; slot < inventory.getSize(); slot++){

            ItemStack itemStack = inventory.getItem(slot);

            if(itemStack != null)
                items.add(new AbstractMap.SimpleEntry<>(slot, new ResetItem(itemStack.getType(), itemStack.getItemMeta(), itemStack.getAmount(), itemStack.getDurability(), itemStack.getData().getData(), itemStack.getEnchantments())));

        }

        effects.addAll(player.getActivePotionEffects());




        isSaved = true;
    }

    public void reset(){
        if(!isSaved)
            return;

        final Player player = getPlayer();

        if(player != null){
            clean();

            player.setExp(this.exp);

        }

    }


}
