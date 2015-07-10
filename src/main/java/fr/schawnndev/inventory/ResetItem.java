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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Map;

public class ResetItem {

    @Getter
    private Material material;

    @Getter
    private ItemMeta itemMeta;

    @Getter
    private String name;

    @Getter
    private short damage;

    @Getter
    private MaterialData data;

    @Getter
    private int amount;

    @Getter
    private Map<Enchantment, Integer> enchantments;

    @Deprecated
    public ResetItem(Material material, ItemMeta itemMeta, int amount, Map<Enchantment, Integer> enchantments) {
        this.material = material == null ? Material.AIR : material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = (short) -1;
        this.data = null;
        this.enchantments = enchantments;
    }

    public ResetItem(Material material, ItemMeta itemMeta, int amount, short damage, Map<Enchantment, Integer> enchantments) {
        this.material = material == null ? Material.AIR : material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = damage;
        this.data = null;
        this.enchantments = enchantments;
    }

    public ResetItem(Material material, ItemMeta itemMeta, int amount, short damage, MaterialData data, Map<Enchantment, Integer> enchantments) {
        this.material = material == null ? Material.AIR : material;
        this.itemMeta = itemMeta;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = damage;
        this.data = data;
        this.enchantments = enchantments;
    }

    public ResetItem(Material material, String name, int amount, short damage, MaterialData data, Map<Enchantment, Integer> enchantments) {
        this.material = material == null ? Material.AIR : material;
        this.name = name;
        this.amount = amount <= 0 ? 1 : amount;
        this.damage = damage;
        this.data = data;
        this.enchantments = enchantments;
    }

    public ItemStack build() {
        ItemStack itemStack = null;

        if (damage <= -1 && data == null)
            itemStack = new ItemStack(material, amount);
        else if (damage > -1)
            if (data == null) {
                itemStack = new ItemStack(material, amount, damage);
            } else {
                itemStack = new ItemStack(material, amount, damage);
                itemStack.setData(data);
            }


        if (itemStack == null)
            return new ItemStack(Material.AIR);

        if (enchantments != null && enchantments.size() > 0)
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet())
                itemStack.addUnsafeEnchantment(entry.getKey(), entry.getValue());

        if (itemMeta != null) {
            itemStack.setItemMeta(itemMeta);
        } else if (itemMeta == null && name != null && !name.equalsIgnoreCase("none")) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            itemStack.setItemMeta(meta);
        }


        return itemStack;
    }

}
