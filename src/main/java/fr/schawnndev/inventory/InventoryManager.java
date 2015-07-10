/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.inventory.InventoryManager) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:21.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.inventory;

import fr.schawnndev.out.FileManager;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    @Getter
    private static List<GameInventory> gameInventoryList = new ArrayList<>();

    public InventoryManager(){
        if(FileManager.getInventoryConfig().contains("inventories")){
            for(String file : (List<String>)FileManager.getInventoryConfig().getList("inventories")){
                gameInventoryList.add(new GameInventory(file));
            }
        }
    }

    public static GameInventory getGameInventory(int id){
        for(GameInventory gameInventory : gameInventoryList)
            if(gameInventory != null && gameInventory.getId() == id)
                return gameInventory;
        return null;
    }

    public static boolean isGameInventory(int id){
        for(GameInventory gameInventory : gameInventoryList)
            if(gameInventory != null && gameInventory.getId() == id)
                return true;
        return false;
    }

    public static GameInventory getGameInventory(String name){
        for(GameInventory gameInventory : gameInventoryList)
            if(gameInventory != null && gameInventory.getName().equalsIgnoreCase(name))
                return gameInventory;
        return null;
    }

    public static boolean isGameInventory(String name){
        for(GameInventory gameInventory : gameInventoryList)
            if(gameInventory != null && gameInventory.getName().equalsIgnoreCase(name))
                return true;
        return false;
    }


}
