/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.inventory.GameInventory) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:11.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.inventory;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.material.MaterialData;

import java.util.*;

public class GameInventory {

    @Getter
    private List<Map.Entry<Integer, ResetItem>> items;

    @Getter
    private int id;

    @Getter
    private String name;

    /*

        Format:

        id @@ name @@ 2:3:5 \ 2:3:5:salut \ 2:3:5



     */

    public GameInventory(String data) {
        String[] splitted = data.split(" @@ ");

        id = Integer.parseInt(splitted[0]);
        name = splitted[1];

        String[] items = splitted[2].split(" || ");

        for (int i = 0; i < items.length; i++) {

            String[] itemData = items[i].split("::");

            int slot = Integer.parseInt(itemData[0]);
            Material material = Material.getMaterial(Integer.parseInt(itemData[1]));
            int amount = Integer.parseInt(itemData[2]);
            short damage = Short.parseShort(itemData[3]);
            MaterialData materialData = new MaterialData(material, Byte.parseByte(itemData[4]));
            String displayName = itemData[5];
            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();

            // Enchantments

            if (!itemData[6].equalsIgnoreCase("aucun")) {

                String[] enchantments = itemData[6].split("//");

                for (int e = 0; e < enchantments.length; e++)
                    enchantmentMap.put(Enchantment.getById(Integer.valueOf(enchantments[e].split("#")[1])), Integer.valueOf(enchantments[e].split("#")[0]));

            }

            this.items = new ArrayList<>();
            this.items.add(new AbstractMap.SimpleEntry<Integer, ResetItem>(slot, new ResetItem(material, displayName, amount, damage, materialData, enchantmentMap)));

        }
    }

    public String serialize() {
        return "hello";
    }

}
