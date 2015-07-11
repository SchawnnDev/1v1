/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.data.ui.HelpMessage) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 14:44.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.data.ui;

import fr.schawnndev.data.Message;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageHelp implements Message {

    private static Map<Integer, List<String>> _messages;

    static {
        _messages = new HashMap<>();
        //
        _messages.put(1, new ArrayList<String>());
        _messages.get(1).add("       §c-- §d1v1 help §c--");
        _messages.get(1).add("§6Gerer les arènes: §f/1v1 arena");
        _messages.get(1).add("§6Gerer les inventaires: §f/1v1 inventories");
        //
        _messages.put(2, new ArrayList<String>());
        _messages.get(2).add("       §c-- §d1v1 inventories help §c--");
        _messages.get(2).add("§6Create: §f/1v1 inventories create <nom>");
        _messages.get(2).add("§6Load: §f/1v1 inventories load <id ou nom>");
        _messages.get(2).add("§6Save: §f/1v1 inventories save <id ou nom>");
        _messages.get(2).add("§6List: §f/1v1 inventories list");
        //
        _messages.put(3, new ArrayList<String>());
        _messages.get(3).add("       §c-- §d1v1 arenas help §c--");
        _messages.get(3).add("§6Create: §f/1v1 arenas create <nom>");
        _messages.get(3).add("§6Rename: §f/1v1 arenas rename <id ou nom> <nouveau nom>");
        _messages.get(3).add("§6List: §f/1v1 arenas list");
        _messages.get(3).add("§6Set inventory: §f/1v1 arenas setinventory <id ou nom> <id ou nom inventaire>");
        _messages.get(3).add("§6Selection stick: §f/1v1 arenas stick");
        _messages.get(3).add("§6Set spawns: §f/1v1 inventories setspawn <id ou nom> <1 ou 2>");

    }

    @Override
    public Map<Integer, List<String>> messages() {
        return _messages;
    }

    @Override
    public void print(Player player, int category) {
        List<String> messages = _messages.get(category);

        for (String msg : messages)
            player.sendMessage(msg);

    }
}

