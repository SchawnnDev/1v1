/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.Main) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 15:53.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev;

import fr.schawnndev.events.PlayerListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  J'aurais voulu la nommer 1v1 mais en java on ne peut pas créer de classe avec des nombres ..
 */

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        init();
    }

    @Override
    public void onDisable() {

    }

    void init(){

        // Listeners

        new PlayerListener();

    }
}
