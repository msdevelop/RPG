package View.SelectionItem;

import Controller.MenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuSelectionItem extends JPanel
{
    private Image selectionImg;
    private boolean isInMouseFocus = false;

    public MenuSelectionItem(int paramXPos, int paramYPos, MenuController paramMenuController, String paramName)
    {
        this.setLayout(null);
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 345, 88);
        this.addMouseListener(paramMenuController);
        try
        {
            this.selectionImg = ImageIO.read(new File("data//img//menu//menuSelectionItem.png"));
        }
        catch(IOException e)
        {}
    }

    public void paintComponent(Graphics selectionItem)
    {
        super.paintComponent(selectionItem);

        if(isInMouseFocus)
            selectionItem.drawImage(this.selectionImg, 0, 0, this);
    }

    public void setInMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
