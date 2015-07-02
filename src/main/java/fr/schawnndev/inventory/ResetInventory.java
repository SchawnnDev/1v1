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

import java.util.*;

public class ResetInventory {

    @Getter
    private UUID uuid;

    @Getter
    private List<Map.Entry<Integer, ResetItem>> items;

    @Getter
    private float exp;

    @Getter
    private Collection<PotionEffect> effects;

    @Getter
    private boolean isSaved = false;

    public ResetInventory(UUID uuid, boolean forceClean){
        this.uuid = uuid;
        this.items = new ArrayList<>();
        this.effects = new ArrayList<>();

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

        final Player player = getPlayer();

        player.setExp(0f);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        for(PotionEffect potionEffect : player.getActivePotionEffects())
            player.removePotionEffect(potionEffect.getType());

        player.updateInventory();

    }

    public void save(){
        final Player player = getPlayer();

        this.exp = player.getExp();

        Inventory inventory = player.getInventory();

        for(int slot = 0; slot < inventory.getSize(); slot++){

            final ItemStack itemStack = inventory.getItem(slot);

            if(itemStack != null)
                items.add(new AbstractMap.SimpleEntry<>(slot, new ResetItem(itemStack.getType(), itemStack.getItemMeta(), itemStack.getAmount(), itemStack.getDurability(), itemStack.getData(), itemStack.getEnchantments())));

        }

        effects.addAll(player.getActivePotionEffects());

        player.updateInventory();

        isSaved = true;
    }

    public void reset(){
        if(!isSaved)
            return;

        final Player player = getPlayer();

        if(player != null){
            clean();

            player.setExp(this.exp);

            for(Map.Entry<Integer, ResetItem> items : getItems())
                player.getInventory().setItem(items.getKey(), items.getValue().build());

            for(PotionEffect potionEffect : getEffects())
                player.addPotionEffect(potionEffect);

            player.updateInventory();

            effects.clear();
            items.clear();
        }

    }


}
