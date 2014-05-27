package View;

import Controller.MapController;
import Model.DetailKartenModel;
import Model.KoordinatenModel;
import View.SelectionItem.DetailSelectionItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MapDetail extends JPanel
{
    private Image detailMap, placeholderDetail;
    private boolean isMissionSelected = false;
    private MapController mapController;
    private List<KoordinatenModel> koordinatenModelList;

    public MapDetail(MapController paramMapController)
    {
        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(811, 0, 1109, 1057);

        try
        {
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
        DetailKartenModel currentDetailMap = this.mapController.getGameFrameController().getDataManager().getDetailKarte(paramMapName);
        this.koordinatenModelList = currentDetailMap.getKoordinatenModelList();

        try
        {
            this.detailMap = ImageIO.read(new File(currentDetailMap.getUrl()));
        }
        catch(IOException e)
        {}

        for(int i = 0; i < koordinatenModelList.size(); i++)
        {
            KoordinatenModel currentModel = koordinatenModelList.get(i);
            int xPos = currentModel.getxPosition();
            int yPos = currentModel.getyPosition();
            this.add(new DetailSelectionItem(paramMapName, xPos, yPos, this.mapController));
        }

        this.setMissionSelected(true);
    }

    public void setMissionSelected(boolean paramBool)
    {
        this.isMissionSelected = paramBool;
        this.repaint();
    }
}
