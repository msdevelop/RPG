package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapDetail extends JPanel
{
    private Image detailMap, placeholderDetail;
    private boolean isMissionSelected = false;

    public MapDetail()
    {
        this.setLayout(null);
        this.setBounds(821, 0, 1099, 1057);

        try
        {
            this.detailMap = ImageIO.read(new File("img//map//detailMap.png"));
            this.placeholderDetail = ImageIO.read(new File("img//map//placeholderDetail.png"));
        }
        catch(IOException e)
        {}

        this.setVisible(true);
    }

    public void paintComponent(Graphics detailMap)
    {
        super.paintComponent(detailMap);
        if(this.isMissionSelected)
            detailMap.drawImage(this.detailMap, 0, 0, this);
        else
            detailMap.drawImage(this.placeholderDetail, 0, 0, this);
    }

    public void setMissionSelected(boolean paramBool)
    {
        this.isMissionSelected = paramBool;
        this.repaint();
    }
}
