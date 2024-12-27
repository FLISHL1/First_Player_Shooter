package ru.flish1.fps_game.entity;

public class Player {
    private double positionX = 0.0;
    private double positionY = 0.0;
    private double angelView = 0.0;

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getAngelView() {
        return angelView;
    }

    public void setAngelView(double angelView) {
        this.angelView = angelView;
    }
}
