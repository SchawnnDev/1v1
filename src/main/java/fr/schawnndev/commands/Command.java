/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.commands.Command) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 15:50.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.commands;

import org.bukkit.entity.Player;

public interface Command {

    public String command();

    public void execute(Player player);

}
