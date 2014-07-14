package View.DesignElement;

import View.GameFrame;

import javax.swing.*;
import java.awt.*;

public class LevelBorder extends JPanel
{
    public LevelBorder(GameFrame gameFrame)
    {
        this.setBounds(0, 0, 1920, 1057);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
        gameFrame.getContentPane().add(this);
    }

    public void paintComponent(Graphics border)
    {
        super.paintComponent(border);
        border.setColor(Color.black);
        border.fillRect(0, 0, 1920, 1080);
    }
}
