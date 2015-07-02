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

import java.util.List;
import java.util.Map;

public class GameInventory {

    @Getter
    private List<Map.Entry<Integer, ResetItem>> items;

    @Getter
    private int id;

    @Getter
    private String name;

    /*

        Format:

        id @ name @ 2:3:5 \ 2:3:5:salut \ 2:3:5



     */

    public GameInventory(String data){
        String[] splitted = data.split(" @ ");

        for(int i = 0; i < splitted.length; i++){

            String split = splitted[i];

            if(split != null){
                if(i == 0) {
                    this.id = Integer.parseInt(split);
                } else if (i == 1) {
                    this.name = split;
                } else if (i > 1) {

                    String[] _split = split.split(" | ");

                    for(int _i = 0; _i < _split.length; _i++){

                        String[] __split = _split[_i].split(":");

                    }




                }
            }





        }


    }

    public String serialize(){

    }

}
