/*
 * ******************************************************
 *  * Copyright (C) 2015 SchawnnDev <contact@schawnndev.fr>
 *  *
 *  * This file (fr.schawnndev.out.FileManager) is part of 1v1.
 *  *
 *  * Created by SchawnnDev on 02/07/15 16:33.
 *  *
 *  * 1v1 can not be copied and/or distributed without the express
 *  * permission of SchawnnDev.
 *  ******************************************************
 */

package fr.schawnndev.out;

import fr.schawnndev.Main;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static File coordsFile;
    @Getter
    private static FileConfiguration coordsConfig;
    //
    private static File inventoryFile;
    @Getter
    private static FileConfiguration inventoryConfig;

    public static void init() {
        boolean newCoordsFile = false;
        boolean newInventoryFile = false;

        File pluginFolder = new File(Main.getInstance().getDataFolder().getPath());

        coordsFile = new File(Main.getInstance().getDataFolder().getPath() + "/coords.yml");
        inventoryFile = new File(Main.getInstance().getDataFolder().getPath() + "/inventories.yml");

        try {
            if (!pluginFolder.exists())
                pluginFolder.mkdirs();

            if (!coordsFile.exists()) {
                coordsFile.createNewFile();
                newCoordsFile = true;
            }
            if (!inventoryFile.exists()) {
                inventoryFile.createNewFile();
                newInventoryFile = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        coordsConfig = YamlConfiguration.loadConfiguration(coordsFile);
        inventoryConfig = YamlConfiguration.loadConfiguration(inventoryFile);

        if(newInventoryFile) {

            List<String> defaultList = new ArrayList<>();
            defaultList.add("1 /// default /// 0");

            inventoryConfig.set("inventories", defaultList);
            save(FileType.INVENTORY);

        }

        List<FileType> files = Arrays.asList(FileType.values());

        Console.printMsg("Loading " + files.size() + " files....");

        for (FileType fileType : files)
            Console.printMsg("Load " + fileType.getFile().getName() + " in " + fileType.getFile().getPath().toString());

        Console.printMsg("All files loaded !");

    }

    public static void save(FileType fileType) {
        try {
            fileType.getFileConfiguration().save(fileType.getFile());
        } catch (IOException e) {
            Console.printErr("Error on saving file " + fileType.getFile().getName() + " (" + fileType.getId() + ')');
            e.printStackTrace();
        }
    }

    public static void reload() {
        Console.printMsg("Reloading file");
        init();
    }

    public enum FileType {
        COORDS(1, "Stockage de locations", coordsFile, coordsConfig),
        INVENTORY(2, "Stockage d'inventaires", inventoryFile, inventoryConfig);

        @Getter
        private int id;
        @Getter
        private File file;
        @Getter
        private FileConfiguration fileConfiguration;

        private FileType(int id, String description, File file, FileConfiguration fileConfiguration) {
            this.id = id;
            this.file = file;
            this.fileConfiguration = fileConfiguration;
        }

    }

}