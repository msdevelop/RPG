package View;

import Controller.MapController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapDetail extends JPanel
{
    private Image detailMap, placeholderDetail;
    private String url;
    private boolean isMissionSelected = false;
    private MapController mapController;

    public MapDetail(MapController paramMapController)
    {
        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(821, 0, 1099, 1057);

        try
        {
            this.detailMap = ImageIO.read(new File("data//img//map//detail//detailMap.png"));
            this.placeholderDetail = ImageIO.read(new File("data//img//map//detail//placeholderDetail.png"));
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

    public void selectMission(String paramMapName)
    {
        this.url = this.mapController.getGameFrameController().getDataManager().getDetailKarte(paramMapName).getUrl();
        this.setMissionSelected(true);
    }

    public void setMissionSelected(boolean paramBool)
    {
        this.isMissionSelected = paramBool;
        this.repaint();
    }
}
