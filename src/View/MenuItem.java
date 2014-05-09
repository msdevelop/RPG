package View;

import Controller.MenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tommy on 09.05.2014.
 */
public class MenuItem extends JPanel
{
    MenuController menuController;
    private String text;
    private int xPos, yPos;

    public MenuItem(MenuController paramController, String paramText, int paramXPos, int paramYPos)
    {
        this.menuController = paramController;
        this.text = paramText;
        this.xPos = paramXPos;
        this.yPos = paramYPos;

        this.addMouseListener(this.menuController);

        this.setSize(200,200);
        this.setVisible(true);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        g.drawString(this.text, this.xPos, this.yPos);
    }


}
