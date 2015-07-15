/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.commands.ui.CommandArenasList) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 15:52.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.commands.ui;

import fr.schawnndev.arenas.Arena;
import fr.schawnndev.arenas.ArenaManager;
import fr.schawnndev.commands.Command;
import org.bukkit.entity.Player;

public class CommandArenasList implements Command {

    @Override
    public String command() {
        return "1v1 arenas list";
    }

    @Override
    public void execute(Player player) {

        player.sendMessage("§f------- §4Liste des arènes §f-------");

         for(Arena arena : ArenaManager.arenas)
             player.sendMessage("§f- §6Arène " + arena.getName() + " (" + arena.getId() + ") ");

    }
}
