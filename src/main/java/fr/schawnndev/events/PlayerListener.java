/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.events.PlayerListener) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:19.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.events;

import fr.schawnndev.Main;
import fr.schawnndev.inventory.InventoryManager;
import fr.schawnndev.inventory.ResetInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        ResetInventory resetInventory = new ResetInventory(e.getPlayer().getUniqueId(), InventoryManager.getGameInventory(1).getItems(), true);
        resetInventory.reset();
    }

}