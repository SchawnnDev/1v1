/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.out.Console) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:51.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.out;

public class Console {

    public static void printMsg(Object object) {
        System.out.println("\u001B[36m" + "[1vs1] " + "\u001B[37m" + object.toString() + "\u001B[0m");
    }

    public static void printErr(Object object) {
        System.out.println("\u001B[36m" + "[1vs1] " + "\u001B[31m" + object.toString() + "\u001B[0m");
    }

}
