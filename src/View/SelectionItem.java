package View;

import Controller.MapController;

import javax.swing.*;
import java.awt.*;

public class SelectionItem extends JPanel
{
    public SelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController)
    {
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 106, 106);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.addMouseListener(paramMapController);
    }
}
