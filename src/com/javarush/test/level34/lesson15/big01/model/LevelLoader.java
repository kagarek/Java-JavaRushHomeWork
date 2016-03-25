package com.javarush.test.level34.lesson15.big01.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by igormakarychev on 3/10/16.
 */
public class LevelLoader
{
    private Path levels;

    public LevelLoader(Path levels) { this.levels = levels; }

    public GameObjects getLevel(int level) {
        Player player = null;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();

        if (level > 60) level = level - level / 60 * 60;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toString())))
        {
            int x0 = Model.FIELD_SELL_SIZE / 2;
            int y0 = Model.FIELD_SELL_SIZE / 2;
            String line = bufferedReader.readLine();
            while (!line.startsWith("Maze: "+ level)){ line = bufferedReader.readLine(); }

            String params_FileOffset = bufferedReader.readLine();
            String params_sizeX = bufferedReader.readLine();
            String params_sizeY = bufferedReader.readLine();
            String params_end = bufferedReader.readLine();
            String params_Length = bufferedReader.readLine();
            bufferedReader.readLine(); // for empty row

            int sizeX = Integer.parseInt(params_sizeX.substring(params_sizeX.indexOf(": ")+2));
            int sizeY = Integer.parseInt(params_sizeY.substring(params_sizeY.indexOf(": ")+2));

            String[] objects;
            for (int rows = 0; rows <= sizeY; rows++)
            {
                line = bufferedReader.readLine();
                objects = line.split("");

                //if (objects.length==0) continue;
                //int x = x0;

                for (int cols = 0; cols < objects.length; cols++)
                {
                    String figure = objects[cols];
                    switch (figure) {
                        case "X": {
                            walls.add(new Wall(x0 + Model.FIELD_SELL_SIZE * cols, y0 + Model.FIELD_SELL_SIZE * rows));
                            //walls.add(new Wall(x,y0));
                            break;
                        }
                        case "*": {
                            boxes.add(new Box(x0 + Model.FIELD_SELL_SIZE * cols, y0 + Model.FIELD_SELL_SIZE * rows));
                            //boxes.add(new Box(x,y0));
                            break;
                        }
                        case ".": {
                            homes.add(new Home(x0 + Model.FIELD_SELL_SIZE * cols, y0 + Model.FIELD_SELL_SIZE * rows));
                            //homes.add(new Home(x,y0));
                            break;
                        }
                        case "&": {
                            boxes.add(new Box(x0 + Model.FIELD_SELL_SIZE * cols, y0 + Model.FIELD_SELL_SIZE * rows));
                            homes.add(new Home(x0 + Model.FIELD_SELL_SIZE * cols, y0 + Model.FIELD_SELL_SIZE * rows));
                            //boxes.add(new Box(x,y0));
                            //homes.add(new Home(x,y0));
                            break;
                        }
                        case "@": {
                            player = new Player(x0 + Model.FIELD_SELL_SIZE * cols,y0 + Model.FIELD_SELL_SIZE * rows);
                            break;
                        }
                    }
                    //x = x + Model.FIELD_SELL_SIZE;
                }
                //y0 = y0 + Model.FIELD_SELL_SIZE;
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return new GameObjects(walls,boxes,homes,player);
    }
}