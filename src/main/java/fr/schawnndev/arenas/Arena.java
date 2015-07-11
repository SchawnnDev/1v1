/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.arenas.Arena) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 11/07/15 19:06.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.arenas;

import fr.schawnndev.Main;
import fr.schawnndev.inventory.GameInventory;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    @Getter
    private int id;

    @Getter
    private String name;

    @Getter
    private GameInventory gameInventory;

    @Getter @Setter
    private Location spawn1;

    @Getter @Setter
    private Location spawn2;

    @Getter
    private List<UUID> playersWaiting;

    @Getter
    private List<UUID> playersPlaying;

    @Getter
    private BukkitTask bukkitTask;

    @Getter @Setter
    private boolean inGame;

    @Getter @Setter
    private int length;

    public Arena(int id, String name, GameInventory gameInventory, int length){
        this.id = id;
        this.name = name;
        this.gameInventory = gameInventory;
        this.playersPlaying = new ArrayList<>();
        this.playersWaiting = new ArrayList<>();
        this.inGame = false;
        this.length = length;

        new BukkitRunnable(){

            @Override
            public void run() {

                if(!inGame){
                    if(playersWaiting.size() >= 2){
                        startNewGame();
                    }
                } else {
                    if(playersPlaying.size() <= 1){
                        stopGame();
                    }

                }

            }

        }.runTaskTimer(Main.getInstance(), 0L, 20L);


    }

    public void startNewGame(){

        if(!inGame && playersWaiting.size() >= 2){

            final Player player1 = Bukkit.getPlayer((UUID)playersWaiting.toArray()[0]);
            final Player player2 = Bukkit.getPlayer((UUID)playersWaiting.toArray()[1]);

            playersPlaying.add(player1.getUniqueId());
            playersPlaying.add(player2.getUniqueId());

            player1.playSound(player1.getLocation(), Sound.NOTE_PLING, 1f, 1f);
            player2.playSound(player2.getLocation(), Sound.NOTE_PLING, 1f, 1f);

            this.bukkitTask = new BukkitRunnable(){

                int timer = length;

                // length = 120 | timer = 119 | timer - 5 = 114 | length - (timer - 5) = 120 - (119 - 5)


                @Override
                public void run() {

                    if(timer > (length - 5)){
                        int time = (length - timer - 5) * -1;

                        if(time > 1){
                            player1.sendMessage("§7Tu vas être téléporté dans§f %t secondes§7.".replace("%t", "" + time));
                            player2.sendMessage("§7Tu vas être téléporté dans §f%t secondes§7.".replace("%t", "" + time));
                        } else {
                            player1.sendMessage("§aTu joues contre§6 %p §a! Bonne chance !".replace("%p", player2.getName()));
                            player2.sendMessage("§aTu joues contre§6 %p §a! Bonne chance !".replace("%p", player1.getName()));
                        }


                    }

                    if(timer == (length / 2)){
                        player1.sendMessage("§7Il y a encore§f %t secondes de jeu§7.".replace("%t", "" + timer));
                        player2.sendMessage("§7Il y a encore§f %t secondes de jeu§7.".replace("%t", "" + timer));
                    }



                    timer--;

                }

            }.runTaskTimer(Main.getInstance(), 0l, 20l);



        }

    }

    public void stopGame(){
        if(!inGame)
            return;





        playersPlaying.clear();

    }

}
