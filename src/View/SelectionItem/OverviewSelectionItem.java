package View.SelectionItem;

import Controller.MapController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OverviewSelectionItem extends JPanel
{
    private Image selectionImg_100, selectionImg_50;
    private boolean isInMouseFocus = false;

    public OverviewSelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController)
    {
        this.setLayout(null);
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 200, 200);
        this.addMouseListener(paramMapController);

        try
        {
            this.selectionImg_100 = ImageIO.read(new File("data//img//map//overview//overviewSelectionItem_100.png"));
            this.selectionImg_50 = ImageIO.read(new File("data//img//map//overview//overviewSelectionItem_50.png"));
        }
        catch(IOException e)
        {}
    }

    public void paintComponent(Graphics selectionItem)
    {
        super.paintComponent(selectionItem);

        if(this.isInMouseFocus)
        {
            selectionItem.drawImage(this.selectionImg_100, 0, 0, this);
        }
        else
            selectionItem.drawImage(this.selectionImg_50, 0, 0, this);
    }

    public void setMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
