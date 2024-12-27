package ru.flish1.fps_game.entity;

import java.util.List;

public class MapGame {
    public static String[] map;
    public static final int width = 16;
    public static final int height = 16;
    static {
        map = new String[16];
        map[0] = "################";
        map[1] = "#..............#";
        map[2] = "#..............#";
        map[3] = "#..............#";
        map[4] = "#..............#";
        map[5] = "#..............#";
        map[6] = "#..............#";
        map[7] = "#..............#";
        map[8] = "#..............#";
        map[9] = "#......###.....#";
        map[10] = "#..............#";
        map[11] = "#..............#";
        map[12] = "#.......########";
        map[13] = "#..............#";
        map[14] = "#..............#";
        map[15] = "################";
    }
}
