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
import fr.schawnndev.inventory.ResetInventory;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  J'aurais voulu la nommer 1v1 mais en java on ne peut pas créer de classe avec des nombres ..
 */

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    public ResetInventory resetInventory = null;

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

    /**
     *  Test
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("a")){

            if(!(sender instanceof Player)){
                sender.sendMessage("§cTu n'es pas un joueur !");
                return true;
            }

            final Player player = (Player) sender;

            if(args.length == 0){
                player.sendMessage("§cPas assez d'arguments !");
                player.sendMessage(" ");
                player.sendMessage("§f------ §bHelp §f------");
                player.sendMessage("§6Save son inventaire: §f/a save");
                player.sendMessage("§6Load son inventaire save: §f/a load");
                player.sendMessage("§f------------------");
                return true;
            } else if (args.length == 1){

                if(args[0].equalsIgnoreCase("save")){
                    resetInventory = new ResetInventory(player.getUniqueId(), true);
                    return true;
                } else if (args[0].equalsIgnoreCase("load")){

                    if(resetInventory == null){
                        player.sendMessage("§cAucun inventaire saved !");
                        return true;
                    }

                    resetInventory.reset();

                    return true;
                }

                player.sendMessage("§f" + "/a " + args[0] + " §cn'est pas un arg valable !");
                return true;
            } else {
                player.sendMessage("§cTrop d'arguments !");
                return true;
            }




            return true;
        }
    }
}
