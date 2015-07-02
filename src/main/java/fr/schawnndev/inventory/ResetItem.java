/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.inventory.ResetItem) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 17:25.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.inventory;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ResetItem {

    @Getter
    private Material material;

    @Getter
    private ItemMeta itemMeta;

    @Getter
    private short damage;

    @Getter
    private byte data;

    @Getter
    private int amount;

    public ResetItem(Material material, ItemMeta itemMeta, int amount){
        this.material = material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = (short)-1;
        this.data = (byte)-1;
    }

    public ResetItem(Material material, ItemMeta itemMeta, int amount, short damage){
        this.material = material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = damage;
        this.data = (byte)-1;
    }

    public ResetItem(Material material, ItemMeta itemMeta, int amount, short damage, byte data){
        this.material = material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = damage;
        this.data = data;
    }

    public ItemStack build(){
        ItemStack itemStack = null;

        if(damage <= -1 && data <= -1)
            itemStack = new ItemStack(material, amount);
        else if (damage > -1 && data <= -1)
            itemStack = new ItemStack(material, amount, damage);
        else if (damage > -1 && data > -1)
            itemStack = new ItemStack(material, amount, damage, data);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
