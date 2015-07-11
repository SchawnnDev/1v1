/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.data.Messages) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 14:39.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.data;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public interface Message {

    public Map<Integer, List<String>> messages();

    public void print(Player player, int category);

}
