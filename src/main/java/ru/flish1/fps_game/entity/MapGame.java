package ru.flish1.fps_game.entity;

import ru.flish1.fps_game.exception.MinHeightMapException;
import ru.flish1.fps_game.exception.MinWidthMapException;

import java.util.Arrays;

public class MapGame {
    //    public static String[] map;
//    public static final int width = 16;
//    public static final int height = 16;
//    static {
//        map = new String[16];
//        map[0] = "################";
//        map[1] = "#..............#";
//        map[2] = "#..............#";
//        map[3] = "#..............#";
//        map[4] = "#..............#";
//        map[5] = "#..............#";
//        map[6] = "#..............#";
//        map[7] = "#..............#";
//        map[8] = "#..............#";
//        map[9] = "#......###.....#";
//        map[10] = "#..............#";
//        map[11] = "#..............#";
//        map[12] = "#.......########";
//        map[13] = "#..............#";
//        map[14] = "#..............#";
//        map[15] = "################";
//    }
    private final String[] map;
    private int width;
    private int height;
    private char charMap;
    public MapGame(String[] map, char charMap) {
        checkMapSize(map);
        this.map = map;
        this.height = map.length;
        this.width = Arrays.stream(map)
                .map(String::length)
                .max(Integer::compareTo).orElseThrow(NullPointerException::new);
        this.charMap = charMap;
    }

    private void checkMapSize(String[] map) {
        if (map.length < 3) {
            throw new MinHeightMapException("Минимальная высота карты должна быть 3");
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < this.width) {
            throw new MinWidthMapException("Минимальная ширина у этой карты" + this.width);
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < this.height) {
            throw new MinWidthMapException("Минимальная высота у этой карты " + this.height);
        }
        this.height = height;
    }
    public int calcDepth(){
        return Integer.max(width, height);
    }
    public String[] getMap() {
        return map;
    }

    public char getCharMap() {
        return charMap;
    }
}
