package View.SelectionItem;

import Controller.CharakterSelectionController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionButton extends JPanel
{
    private Image btnImage, btnImageActive;
    private boolean isInMouseFocus = false;

    public CharakterSelectionButton(String paramName, int paramXPos, int paramYPos, CharakterSelectionController paramCharakterSelectionController)
    {
        this.setName("btn_" + paramName);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
        this.setBounds(paramXPos, paramYPos, 280, 60);
        this.addMouseListener(paramCharakterSelectionController);
        try
        {
            this.btnImage = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + ".png"));
            this.btnImageActive = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + "_active.png"));
        }
        catch(IOException e)
        {}
    }

    public void paintComponent(Graphics button)
    {
        super.paintComponent(button);

        button.drawImage(this.btnImage, 0, 0, this);

        if(this.isInMouseFocus)
            button.drawImage(this.btnImageActive, 0, 0, this);
    }

    public void setIntMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
