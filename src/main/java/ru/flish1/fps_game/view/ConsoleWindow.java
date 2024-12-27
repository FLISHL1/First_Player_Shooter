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
    private Terminal terminal;
    private final DefaultTerminalFactory factory;

    public ConsoleWindow(){
        Font myFont = new Font(fontFamily, Font.PLAIN, fontSize); // Change the number 20 to your desired font size
        SwingTerminalFontConfiguration myFontConfiguration = SwingTerminalFontConfiguration.newInstance(myFont);
        factory = new DefaultTerminalFactory();
        factory.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode);
        factory.setInitialTerminalSize(new TerminalSize(width, height));
        factory.setForceAWTOverSwing(false);
        factory.setTerminalEmulatorFontConfiguration(myFontConfiguration);
    }

    public Terminal createTerminal() throws IOException {
        return factory.createTerminal();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
