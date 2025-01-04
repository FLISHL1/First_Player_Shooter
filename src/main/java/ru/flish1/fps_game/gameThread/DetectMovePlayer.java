package ru.flish1.fps_game.gameThread;

import com.googlecode.lanterna.terminal.Terminal;
import ru.flish1.fps_game.entity.MapGame;
import ru.flish1.fps_game.entity.Player;
import ru.flish1.fps_game.exception.InputKeyException;
import ru.flish1.fps_game.view.ConsoleWindow;

import java.io.IOException;

public class DetectMovePlayer extends Thread {
    private final Player player;
    private final ConsoleWindow consoleWindow;
    private final Terminal terminal;
    private final MapGame mapGame;
    private final int valueChangeCord = 28;
    private final double valueChangeAngle = 6;

    public DetectMovePlayer(Player player, ConsoleWindow window, MapGame mapGame) {
        super();
        this.player = player;
        this.consoleWindow = window;
        this.terminal = window.getTerminal();
        this.mapGame = mapGame;
    }

    @Override
    public void run() {
        while (true) {
            try {
                char inputKey = terminal.readInput().getCharacter();
                if (inputKey == 'a') {
                    changeCordA();
                } else if (inputKey == 'd') {
                    player.setAngelView(player.getAngelView() + valueChangeAngle * consoleWindow.getElapsedTime());
                } else if (inputKey == 'w') {
                    changeCordW();
                } else if (inputKey == 's') {
                    changeCordS();
                }
            } catch (IOException e) {
                throw new InputKeyException(e.getMessage());
            }
        }
    }

    private void changeCordA() {
        player.setAngelView(player.getAngelView() - valueChangeAngle * consoleWindow.getElapsedTime());
    }

    private void changeCordS() {
        player.setPositionX(player.getPositionX() - Math.sin(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        player.setPositionY(player.getPositionY() - Math.cos(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        if (mapGame.getMap()[(int) player.getPositionY()].charAt((int) player.getPositionX()) == '#') {
            player.setPositionX(player.getPositionX() + Math.sin(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
            player.setPositionY(player.getPositionY() + Math.cos(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        }
    }

    private void changeCordW() {
        player.setPositionX(player.getPositionX() + Math.sin(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        player.setPositionY(player.getPositionY() + Math.cos(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        if (mapGame.getMap()[(int) player.getPositionY()].charAt((int) player.getPositionX()) == '#') {
            player.setPositionX(player.getPositionX() - Math.sin(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
            player.setPositionY(player.getPositionY() - Math.cos(player.getAngelView()) * valueChangeCord * consoleWindow.getElapsedTime());
        }
    }
}
