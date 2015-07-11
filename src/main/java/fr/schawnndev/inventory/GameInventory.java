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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.*;

public class GameInventory {

    @Getter
    private int id;

    @Getter
    private String name;

    @Getter
    private List<Map.Entry<Integer, ResetItem>> items;

    public GameInventory(String data) {
        this.items = new ArrayList<>();

        String[] splitted = data.split(" /// ");

        id = Integer.parseInt(splitted[0]);
        name = splitted[1];

        List<String> items = Arrays.asList(splitted[2].split("/::/"));

        for (int i = 0; i < items.size(); i++) {
            List<String> itemData = Arrays.asList(items.get(i).split("/:/"));

            int slot = Integer.parseInt(itemData.get(0));
            Material material = Material.getMaterial(Integer.parseInt(itemData.get(1)));
            int amount = Integer.parseInt(itemData.get(2));
            short damage = Short.parseShort(itemData.get(3));
            MaterialData materialData = new MaterialData(material, Byte.parseByte(itemData.get(4)));
            String displayName = itemData.get(5);

            if(displayName.contains("!e!"))
                displayName.replaceAll("!e!", "é");

            if(displayName.contains("!a!"))
                displayName.replaceAll("!a!", "à");

            if(displayName.contains("!e-!"))
                displayName.replaceAll("!e-!", "è");

            if(displayName.contains("!c!"))
                displayName.replaceAll("!c!", "ç");

            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();

            // Enchantments

            if (!itemData.get(6).equalsIgnoreCase("aucun")) {

                List<String> enchantments = Arrays.asList(itemData.get(6).split("//"));

                for (int e = 0; e < enchantments.size(); e++)
                    enchantmentMap.put(Enchantment.getById(Integer.valueOf(enchantments.get(e).split("#")[0])), Integer.valueOf(enchantments.get(e).split("#")[1]));

            }

            this.items.add(new AbstractMap.SimpleEntry<>(slot, new ResetItem(material, ChatColor.translateAlternateColorCodes('&', displayName), amount, damage, materialData, enchantmentMap)));

        }
    }

    public String serialize() {
        String data = "";
        int count = 0;

        for(Map.Entry<Integer, ResetItem> item : items){

            int slot = item.getKey();
            ItemStack itemStack = item.getValue().build();
            String displayName = "none";

            if(itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName() != null)
                displayName = itemStack.getItemMeta().getDisplayName();

            if(displayName.contains("é"))
                displayName.replaceAll("é", "!e!");

            if(displayName.contains("à"))
                displayName.replaceAll("à", "!a!");

            if(displayName.contains("è"))
                displayName.replaceAll("è", "!e-!");

            if(displayName.contains("ç"))
                displayName.replaceAll("ç", "!c!");

            String add = (count == 0 ? "" : "/::/") + slot + "/:/" + itemStack.getTypeId() + "/:/" + itemStack.getAmount() + "/:/"
                       + itemStack.getDurability() + "/:/" + itemStack.getData().getData() + "/:/" + displayName + "/:/";

            Set<Map.Entry<Enchantment, Integer>> enchantments = itemStack.getEnchantments().entrySet();

            if(enchantments.size() > 0) {

                for (int i = 0; i < enchantments.toArray().length; i++) {
                    Map.Entry<Enchantment, Integer> integerEntry = ((Map.Entry<Enchantment, Integer>) enchantments.toArray()[i]);

                    if (i == 0) {
                        add += ("" + integerEntry.getKey().getId() + "#" + integerEntry.getValue());
                    } else {
                        add += ("//" + integerEntry.getKey().getId() + "#" + integerEntry.getValue());
                    }

                }
            } else {
                add += "aucun";
            }

            count++;
            data += add;

        }

        return id + " /// " + name + " /// " + data;
    }

}
