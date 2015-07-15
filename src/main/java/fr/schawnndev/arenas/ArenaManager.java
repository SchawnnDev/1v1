/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.arenas.ArenaManager) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 20:57.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.arenas;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArenaManager {

    @Getter
    public static List<Arena> arenas = new ArrayList<>();

    public static boolean isInGame(Player player){

        UUID uuid = player.getUniqueId();

        for(Arena arena : arenas)
            if(arena.getPlayersPlaying().contains(uuid))
                return true;

        return false;
    }


}
