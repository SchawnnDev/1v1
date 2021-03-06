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

import fr.schawnndev.data.ui.MessageHelp;
import fr.schawnndev.events.PlayerListener;
import fr.schawnndev.inventory.InventoryManager;
import fr.schawnndev.inventory.ResetInventory;
import fr.schawnndev.out.FileManager;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * J'aurais voulu la nommer 1v1 mais en java on ne peut pas créer de classe avec des nombres ..
 */

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    public List<ResetInventory> inventoryList = new CopyOnWriteArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        init();
    }

    @Override
    public void onDisable() {

    }

    void init() {

        // Listeners

        new PlayerListener();

        // Files

        FileManager.init();

        // Inventories

        new InventoryManager();

    }

    public ResetInventory getResetInventory(UUID uuid) {
        for (ResetInventory r : inventoryList)
            if (r != null && r.getUuid() == uuid)
                return r;

        return null;
    }

    public boolean hasResetInventory(UUID uuid) {
        for (ResetInventory r : inventoryList)
            if (r != null && r.getUuid() == uuid)
                return true;

        return false;
    }

    /**
     * Test
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("1v1")){

            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTu n'es pas un joueur !");
                return true;
            }

            Player player = (Player)sender;

            if(player.hasPermission("1v1.admin") || player.isOp()){

                if(args.length == 0){

                    new MessageHelp().print(player, 1);

                    return true;
                } else if (args.length == 1){

                    if(args[0].equalsIgnoreCase("inventories")){

                        new MessageHelp().print(player, 2);

                        return true;
                    } else if (args[0].equalsIgnoreCase("arenas")){

                        new MessageHelp().print(player, 3);

                        return true;
                    } else {

                        player.sendMessage("§cCommande §f/1v1 " + args[0] + " §cinconnue !");

                        return true;
                    }




                }







            } else {
                player.sendMessage("§fUnknown command. Type "+ '"' + "/help" + '"' + " for help.");
                return true;
            }



            return true;
        }





        if (command.getName().equalsIgnoreCase("a")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTu n'es pas un joueur !");
                return true;
            }

            final Player player = (Player) sender;

            if (args.length == 0) {

                if (player.getItemInHand() != null && player.getItemInHand().getData() != null)
                    System.out.println(player.getItemInHand().getData().toString());


                player.sendMessage("§cPas assez d'arguments !");
                player.sendMessage(" ");
                player.sendMessage("§f------ §bHelp §f------");
                player.sendMessage("§6Save son inventaire: §f/a save");
                player.sendMessage("§6Load son inventaire save: §f/a load");
                player.sendMessage("§f------------------");
                return true;
            } else if (args.length == 1) {

                if (args[0].equalsIgnoreCase("save")) {
                    inventoryList.add(new ResetInventory(player.getUniqueId(), true));
                    return true;
                } else if (args[0].equalsIgnoreCase("load")) {

                    if (!hasResetInventory(player.getUniqueId())) {
                        player.sendMessage("§cAucun inventaire saved !");
                        return true;
                    }

                    getResetInventory(player.getUniqueId()).reset();
                    inventoryList.remove(getResetInventory(player.getUniqueId()));

                    return true;
                }

                player.sendMessage("§f" + "/a " + args[0] + " §cn'est pas un arg valable !");
                player.sendMessage(" ");
                player.sendMessage("§f------ §bHelp §f------");
                player.sendMessage("§6Save son inventaire: §f/a save");
                player.sendMessage("§6Load son inventaire save: §f/a load");
                player.sendMessage("§f------------------");
                return true;
            } else {
                player.sendMessage("§cTrop d'arguments !");
                return true;
            }


        }

        return true;
    }
}
