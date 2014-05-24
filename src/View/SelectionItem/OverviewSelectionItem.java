package View.SelectionItem;

import Controller.MapController;

import javax.swing.*;
import java.awt.*;

public class OverviewSelectionItem extends JPanel
{
    public OverviewSelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController)
    {
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 106, 106);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.addMouseListener(paramMapController);
    }
}
