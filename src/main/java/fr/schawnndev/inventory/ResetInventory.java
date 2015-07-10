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
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
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
    private int level;

    @Getter
    private Collection<PotionEffect> effects;

    @Getter
    private boolean isSaved = false;

    public ResetInventory(UUID uuid, boolean forceClean) {
        this.uuid = uuid;
        this.items = new ArrayList<>();
        this.effects = new ArrayList<>();

        save(true);

        if (forceClean)
            clean();

        System.out.println(serialize(1, "soup"));

    }

    public ResetInventory(UUID uuid, List<Map.Entry<Integer, ResetItem>> items, boolean forceClean) {
        this.uuid = uuid;
        this.items = items;
        this.effects = new ArrayList<>();

        // save(false);
        isSaved = true;

        if (forceClean)
            clean();

        System.out.println(serialize(1, "soup"));

    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void clean() {
        if (!isSaved)
            save(true);

        final Player player = getPlayer();

        player.setExp(0f);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        for (PotionEffect potionEffect : player.getActivePotionEffects())
            player.removePotionEffect(potionEffect.getType());

        player.updateInventory();

    }

    public void save(boolean withItems) {
        final Player player = getPlayer();

        this.exp = player.getExp();
        this.level = player.getLevel();
        player.setLevel(0);

        if(withItems) {

            Inventory inventory = player.getInventory();

            for (int slot = 0; slot < inventory.getSize(); slot++) {

                final ItemStack itemStack = inventory.getItem(slot);

                if (itemStack != null)
                    items.add(new AbstractMap.SimpleEntry<>(slot, new ResetItem(itemStack.getType(), itemStack.getItemMeta(), itemStack.getAmount(), itemStack.getDurability(), itemStack.getData(), itemStack.getEnchantments())));

            }

        }

        effects.addAll(player.getActivePotionEffects());

        player.updateInventory();

        isSaved = true;
    }

    public void reset() {
        if (!isSaved)
            return;

        final Player player = getPlayer();

        if (player != null) {
            clean();

            player.setExp(this.exp);
            player.setLevel(this.level);

            for (Map.Entry<Integer, ResetItem> items : getItems())
                player.getInventory().setItem(items.getKey(), items.getValue().build());

            for (PotionEffect potionEffect : getEffects())
                player.addPotionEffect(potionEffect);

            player.updateInventory();

            effects.clear();
            items.clear();
        }

    }

    public String serialize(int id, String inventoryName) {
        String data = "";
        int count = 0;

        for(Map.Entry<Integer, ResetItem> item : items){

            int slot = item.getKey();
            ItemStack itemStack = item.getValue().build();
            String displayName = "none";


            if(itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName() != null)
                displayName = itemStack.getItemMeta().getDisplayName();

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

        return id + " /// " + inventoryName + " /// " + data;
    }


    public ResetInventory deserialize(UUID uuid, String data) {
        String[] splitted = data.split(" /// ");
        //
        int id = Integer.parseInt(splitted[0]);
        String name = splitted[1];
        List<Map.Entry<Integer, ResetItem>> itemList = new ArrayList<>();

        List<String> items = Arrays.asList(splitted[2].split("/::/"));

        for (int i = 0; i < items.size(); i++) {
            List<String> itemData = Arrays.asList(items.get(i).split("/:/"));

            int slot = Integer.parseInt(itemData.get(0));
            Material material = Material.getMaterial(Integer.parseInt(itemData.get(1)));
            int amount = Integer.parseInt(itemData.get(2));
            short damage = Short.parseShort(itemData.get(3));
            MaterialData materialData = new MaterialData(material, Byte.parseByte(itemData.get(4)));
            String displayName = itemData.get(5);
            Map<Enchantment, Integer> enchantmentMap = new HashMap<>();

            // Enchantments

            if (!itemData.get(6).equalsIgnoreCase("aucun")) {

                List<String> enchantments = Arrays.asList(itemData.get(6).split("//"));

                for (int e = 0; e < enchantments.size(); e++)
                    enchantmentMap.put(Enchantment.getById(Integer.valueOf(enchantments.get(e).split("#")[0])), Integer.valueOf(enchantments.get(e).split("#")[1]));

            }

            itemList.add(new AbstractMap.SimpleEntry<>(slot, new ResetItem(material, displayName, amount, damage, materialData, enchantmentMap)));

        }

        return new ResetInventory(uuid, itemList, true);
    }

}
