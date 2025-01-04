package ru.flish1.fps_game;


import ru.flish1.fps_game.entity.MapGame;
import ru.flish1.fps_game.entity.Player;
import ru.flish1.fps_game.gameThread.DetectMovePlayer;
import ru.flish1.fps_game.gameThread.RenderingGraphics;
import ru.flish1.fps_game.view.ConsoleWindow;

import java.io.IOException;

public class FpsGameApplication {
    public static void main(String[] args) throws IOException {
        ConsoleWindow consoleWindow = new ConsoleWindow();
        Player player = new Player();
        player.setPositionX(2);
        player.setPositionY(2);
        String[] map = new String[16];
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
        MapGame mapGame = new MapGame(map, '#');
        DetectMovePlayer detectMovePlayer = new DetectMovePlayer(player, consoleWindow, mapGame);
        RenderingGraphics renderingGraphics = new RenderingGraphics(player, consoleWindow, mapGame);
        renderingGraphics.start();
        player.setCallback(renderingGraphics);
        detectMovePlayer.start();
    }

}
