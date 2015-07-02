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
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener {

    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

}