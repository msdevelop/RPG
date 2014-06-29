package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionView extends JPanel
{
    private Image bgImage;
    JTextField txtfName;

    public CharakterSelectionView()
    {
        try
        {
            this.bgImage = ImageIO.read(new File("data//img//charSelection//charSelection.png"));
        }
        catch(IOException e)
        {
        }

        txtfName = new JTextField();
        txtfName.setBounds(549, 259, 155, 30);
        txtfName.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
        txtfName.setForeground(Color.WHITE);
        txtfName.setBackground(Color.DARK_GRAY);
        txtfName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.add(this.txtfName);

        this.setLayout(null);
        this.setBounds(0, 0, 1920, 1057);
        this.setVisible(true);
    }

    public void paintComponent(Graphics charBg)
    {
        super.paintComponent(charBg);
        charBg.drawImage(this.bgImage, 0, 0, this);
    }
}
