package ru.flish1.fps_game;


import com.googlecode.lanterna.terminal.Terminal;
import ru.flish1.fps_game.entity.MapGame;
import ru.flish1.fps_game.entity.Player;
import ru.flish1.fps_game.view.ConsoleWindow;

import java.io.IOException;

public class FpsGameApplication {
    static double elapsedTime;

    public static void main(String[] args) throws IOException {
        ConsoleWindow consoleWindow = new ConsoleWindow();
        Terminal terminal = consoleWindow.createTerminal();
        terminal.setCursorVisible(false);
        Player player = new Player();
        player.setPositionX(2);
        player.setPositionY(2);
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        int changeCoord = 28;

                        double valueChangePlayerAngle = 12;
                        if (terminal.readInput().getCharacter() == 'a') {
                            player.setAngelView(player.getAngelView() - valueChangePlayerAngle * elapsedTime);
                        } else if (terminal.readInput().getCharacter() == 'd') {
                            player.setAngelView(player.getAngelView() + valueChangePlayerAngle * elapsedTime);
                        }else if (terminal.readInput().getCharacter() == 'w') {
                            player.setPositionX(player.getPositionX() + Math.sin(player.getAngelView()) * changeCoord * elapsedTime);
                            player.setPositionY(player.getPositionY() + Math.cos(player.getAngelView()) * changeCoord * elapsedTime);
                            if (MapGame.map[(int) player.getPositionY()].charAt((int) player.getPositionX()) == '#'){
                                player.setPositionX(player.getPositionX() - Math.sin(player.getAngelView()) * changeCoord * elapsedTime);
                                player.setPositionY(player.getPositionY() - Math.cos(player.getAngelView()) * changeCoord * elapsedTime);
                            }
                        } else if (terminal.readInput().getCharacter() == 's') {
                            player.setPositionX(player.getPositionX() - Math.sin(player.getAngelView()) * changeCoord * elapsedTime);
                            player.setPositionY(player.getPositionY() - Math.cos(player.getAngelView()) * changeCoord * elapsedTime);
                            if (MapGame.map[(int) player.getPositionY()].charAt((int) player.getPositionX()) == '#') {
                                player.setPositionX(player.getPositionX() + Math.sin(player.getAngelView()) * changeCoord * elapsedTime);
                                player.setPositionY(player.getPositionY() + Math.cos(player.getAngelView()) * changeCoord * elapsedTime);
                            }
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

        float fFov = (float) (Math.PI / 2.0);
        double fDepth = 16; // Граница для определения выхода за стены
        try {
            terminal.enterPrivateMode();
//            TextGraphics textGraphics = terminal.newTextGraphics();

            long startTime = System.currentTimeMillis();
            while (true) {
                long endTime = System.currentTimeMillis();
                elapsedTime = (endTime - startTime) / 1000.0;
                startTime = endTime;


//                terminal.clearScreen();
                for (int x = 0; x < consoleWindow.getWidth(); x++) {
                    // Угол куда смотрит игрок
                    double fRayAngle = (player.getAngelView() - fFov / 2.0) + ((double) x / (double) consoleWindow.getWidth()) * fFov;
                    double fEyeX = Math.sin(fRayAngle);
                    double fEyeY = Math.cos(fRayAngle);
                    double fDistanceOfWall = 0;
                    boolean bHitWall = false;
                    while (!bHitWall && fDistanceOfWall < fDepth) {
                        fDistanceOfWall += 0.1;

                        int nTestX = (int) (player.getPositionX() + fEyeX * fDistanceOfWall);
                        int nTestY = (int) (player.getPositionY() + fEyeY * fDistanceOfWall);

                        //Если мы находимся за пределами карты
                        if (nTestX < 0 || nTestX >= MapGame.width || nTestY < 0 || nTestY >= MapGame.height) {
                            bHitWall = true;
                            fDistanceOfWall = fDepth;

                        } else if (MapGame.map[nTestY].charAt(nTestX) == '#') {
                            bHitWall = true;
                        }
                    }

                    int nCeiling = (int) ((consoleWindow.getHeight() / 2.0) - consoleWindow.getHeight() / fDistanceOfWall);
                    int nFloor = consoleWindow.getHeight() - nCeiling;
                    char charWall = ' ';
                    if (fDistanceOfWall <= fDepth / 4) {
                        charWall = '█';
                    } else if (fDistanceOfWall < fDepth / 3) {
                        charWall = '▓';
                    } else if (fDistanceOfWall < fDepth / 2) {
                        charWall = '▒';
                    } else if (fDistanceOfWall < fDepth) {
                        charWall = '░';
                    }else {
                        charWall = ' ';
                    }
                    for (int y = 0; y < consoleWindow.getHeight(); y++) {
                        if (y <= nCeiling) {
                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(' ');
                        } else if (y > nCeiling && y <= nFloor) {
                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(charWall);
                        } else {
                            double distFloor = 1 - ((y - consoleWindow.getHeight() / 2.0) / (consoleWindow.getHeight() / 2.0));
                            if (distFloor < 0.25){
                                charWall = '#';
                            }else if (distFloor < 0.5){
                                charWall = 'X';
                            } else if (distFloor < 0.75){
                                charWall = '.';
                            } else if (distFloor < 0.9){
                                charWall = '_';
                            } else {
                                charWall = ' ';
                            }
                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(charWall);
                        }
                    }
                }

                terminal.setCursorPosition(0, 0);
                terminal.putString(String.format("X=%(.2f), Y=%(.2f), A=%(.2f), FPS=%(.2f)", player.getPositionX(), player.getPositionY(), player.getAngelView(), 1/elapsedTime));
                String[] mapWithPlayer = MapGame.map.clone();
                char[] row = mapWithPlayer[(int) player.getPositionY()].toCharArray();
                row[(int) player.getPositionX()] = 'P';
                mapWithPlayer[(int) player.getPositionY()] = String.valueOf(row);
                for (int i = 1; i<=mapWithPlayer.length; i++){
                    terminal.setCursorPosition(0, i);
                    terminal.putString(mapWithPlayer[i-1]);
                }

                terminal.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
