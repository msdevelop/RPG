package View.SelectionItem;

import Controller.MenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuSelectionItem extends JPanel
{
    private Image borderImg;
    private boolean isInMouseFocus = false;

    public MenuSelectionItem(int paramXPos, int paramYPos, MenuController paramMenuController, String paramName)
    {
        this.setLayout(null);
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 346, 88);
        this.addMouseListener(paramMenuController);
        try
        {
            this.borderImg = ImageIO.read(new File("data//img//menu//selectionItem.png"));
        }
        catch(IOException e)
        {}
    }

    public void paintComponent(Graphics selectionItem)
    {
        super.paintComponent(selectionItem);

        if(isInMouseFocus)
        {
            selectionItem.drawImage(this.borderImg, 0, 0, this);
        }
    }

    public void setInMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
