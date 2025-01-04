package ru.flish1.fps_game.gameThread;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;
import ru.flish1.fps_game.entity.MapGame;
import ru.flish1.fps_game.entity.Player;
import ru.flish1.fps_game.view.ConsoleWindow;

import java.io.IOException;

public class RenderingGraphics extends Thread {
    private final Player player;
    private final ConsoleWindow consoleWindow;
    private final MapGame mapGame;
    private final Terminal terminal;

    public RenderingGraphics(Player player, ConsoleWindow consoleWindow, MapGame mapGame) {
        super();
        this.player = player;
        this.consoleWindow = consoleWindow;
        this.terminal = consoleWindow.getTerminal();
        this.mapGame = mapGame;
    }

    private void paintMap(Player player) throws IOException {
        String[] mapWithPlayer = mapGame.getMap().clone();
        char[] row = new char[1];
        if (player.getPositionY() < mapWithPlayer.length && player.getPositionY() >= 0) {
            row = mapWithPlayer[(int) player.getPositionY()].toCharArray();
        } else return;
        row[(int) player.getPositionX()] = player.getCharPlayer();
        mapWithPlayer[(int) player.getPositionY()] = String.valueOf(row);
        for (int i = 1; i <= mapWithPlayer.length; i++) {
            terminal.setCursorPosition(0, i);
            terminal.putString(mapWithPlayer[i - 1]);
        }
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        repaintWindow();
        consoleWindow.setElapsedTime((System.currentTimeMillis() - startTime) / 1000.0);
    }

    private void repaintWindow() {
        try {
            double fDistanceOfWall = 0;
            for (int x = 0; x < consoleWindow.getWidth(); x++) {

                // Угол куда смотрит игрок
                double fRayAngle = (player.getAngelView() - player.getFov() / 2.0) + ((double) x / (double) consoleWindow.getWidth()) * player.getFov();
                double fEyeX = Math.sin(fRayAngle);
                double fEyeY = Math.cos(fRayAngle);
                double changeDistanceOfWall = 0.1;
                fDistanceOfWall = calcDistanceOfWall(false, 0, changeDistanceOfWall, fEyeX, fEyeY);

                int nCeiling = (int) ((consoleWindow.getHeight() / 2.0) - consoleWindow.getHeight() / fDistanceOfWall);
                int nFloor = consoleWindow.getHeight() - nCeiling;
                char charWall = calcCharWall(fDistanceOfWall);

                TextGraphics textGraphics = terminal.newTextGraphics();
                textGraphics.drawLine(x, 0, x, nCeiling, ' ');
                textGraphics.drawLine(x, nCeiling, x, nFloor, charWall);
                for (int y = nFloor; y < consoleWindow.getHeight(); y++) {
                    char charFloor = calcCharFloor(y);
                    textGraphics.setCharacter(x, y, charFloor);
                }
            }

            paintMap(player);
            printStatistic(fDistanceOfWall);
            terminal.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printStatistic(double fDistanceOfWall) throws IOException {
        terminal.setCursorPosition(0, 0);
        terminal.putString(String.format("X=(%(.2f), Y=(%(.2f), A=(%(.2f), FPS=(%(.2f), FDist=(%(.2f)", player.getPositionX(), player.getPositionY(), player.getAngelView(), 1 / consoleWindow.getElapsedTime(), fDistanceOfWall));
    }

    private char calcCharFloor(int y) throws IOException {
        double distFloor = 1 - ((y - consoleWindow.getHeight() / 2.0) / (consoleWindow.getHeight() / 2.0));
        char charFloor;
        if (distFloor < 0.25) {
            charFloor = '#';
        } else if (distFloor < 0.5) {
            charFloor = 'X';
        } else if (distFloor < 0.75) {
            charFloor = '.';
        } else if (distFloor < 0.9) {
            charFloor = '_';
        } else {
            charFloor = ' ';
        }
        return charFloor;
    }

    private char calcCharWall(double fDistanceOfWall) {
        char charWall;
        if (fDistanceOfWall <= mapGame.calcDepth() / 4.0) {
            charWall = '█';
        } else if (fDistanceOfWall < mapGame.calcDepth() / 3.0) {
            charWall = '▓';
        } else if (fDistanceOfWall < mapGame.calcDepth() / 2.0) {
            charWall = '▒';
        } else if (fDistanceOfWall < mapGame.calcDepth()) {
            charWall = '░';
        } else {
            charWall = ' ';
        }
        return charWall;
    }

    private double calcDistanceOfWall(boolean bHitWall, double fDistanceOfWall, double changeDistanceOfWall, double fEyeX, double fEyeY) {

        while (!bHitWall && fDistanceOfWall < mapGame.calcDepth()) {
            fDistanceOfWall += changeDistanceOfWall;
            int nTestX = (int) (player.getPositionX() + fEyeX * fDistanceOfWall);
            int nTestY = (int) (player.getPositionY() + fEyeY * fDistanceOfWall);

            //Если мы находимся за пределами карты
            if (nTestX < 0 || nTestX >= mapGame.getWidth() || nTestY < 0 || nTestY >= mapGame.getHeight()) {
                bHitWall = true;
                fDistanceOfWall = mapGame.calcDepth();
            } else if (mapGame.getMap()[nTestY].charAt(nTestX) == mapGame.getCharMap()) {
                bHitWall = true;
            }
        }
        return fDistanceOfWall;
    }
}
