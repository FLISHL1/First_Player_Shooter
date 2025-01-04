package ru.flish1.fps_game.entity;

public class Player {
    private double positionX = 0.0;
    private double positionY = 0.0;
    private double angelView = 0.0;
    private double fov = Math.PI / 2.0;
    private char charPlayer = 'P';
    private Runnable callback;


    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
        if (callback != null) {
            callback.run();
        }
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
        if (callback != null) {
            callback.run();
        }
    }

    public double getAngelView() {
        return angelView;
    }

    public void setAngelView(double angelView) {
        this.angelView = angelView;
        if (callback != null) {
            callback.run();
        }
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public char getCharPlayer() {
        return charPlayer;
    }

    public void setCharPlayer(char charPlayer) {
        this.charPlayer = charPlayer;
    }
}
