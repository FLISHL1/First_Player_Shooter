package ru.flish1.fps_game.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger;

import java.awt.*;
import java.io.IOException;

public class ConsoleWindow {
    private final int width = 120;
    private final int height = 40;
    private final int fontSize = 12;
    private final String fontFamily = "Courier New";
    private final Terminal terminal;
    private double elapsedTime;

    public ConsoleWindow() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();
        setTerminalFactoryOptional(factory);
        terminal = factory.createTerminal();
        terminal.setCursorVisible(false);
    }

    private void setTerminalFactoryOptional(DefaultTerminalFactory factory) {
        factory.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
        factory.setInitialTerminalSize(new TerminalSize(width, height));
        factory.setForceAWTOverSwing(false);
        factory.setTerminalEmulatorFontConfiguration(getSwingFont());
    }

    private SwingTerminalFontConfiguration getSwingFont() {
        Font myFont = new Font(fontFamily, Font.PLAIN, fontSize);
        return SwingTerminalFontConfiguration.newInstance(myFont);
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
