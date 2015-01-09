package View.SelectionItem;

import Controller.CharakterSelectionController;

import javax.swing.*;
import java.awt.*;

public class TooltipSelectionItem extends JPanel
{
    public TooltipSelectionItem(int paramXPos, int paramYPos, int paramWidth, int paramHeight, String paramIdentifier, CharakterSelectionController paramCharakterselectionController)
    {
        this.setOpaque(false);
        //TODO border nur zu testzwecken
        this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1, true));
        this.setBounds(paramXPos, paramYPos, paramWidth, paramHeight);
        this.setName("tooltip_" + paramIdentifier);
        this.addMouseListener(paramCharakterselectionController);
    }
}
