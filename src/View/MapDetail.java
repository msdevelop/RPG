package View;

import Data.DataManager;
import Model.DetailKartenModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapDetail extends JPanel
{
    private Image detailMap, placeholderDetail;
    private boolean isMissionSelected = false;
    private DataManager dataManager;

    public MapDetail(String mapName)
    {
        dataManager = new DataManager(mapName);

        this.setLayout(null);
        this.setBounds(821, 0, 1099, 1057);

        try
        {
            DetailKartenModel karte = dataManager.detailKartenModelList.get(0);
            //this.detailMap = ImageIO.read(new File("data//img//map//detail//detailMap.png"));
            this.detailMap = ImageIO.read(new File(karte.getUrl()));
            this.placeholderDetail = ImageIO.read(new File("data//img//map//detail//placeholderDetail.png"));
        }
        catch(IOException e)
        {
        }

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
