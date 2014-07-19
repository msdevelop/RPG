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
import java.util.LinkedList;

public class MapDetailView extends JPanel
{
    private Image detailMap, placeholderDetail;
    private boolean isMissionSelected = false;
    private MapController mapController;

    /*Liest Platzhalterbild der MapDetailView ein*/
    public MapDetailView(MapController paramMapController)
    {
        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(811, 0, 1109, 1057);

        try
        {
            this.placeholderDetail = ImageIO.read(new File("data//img//map//detail//placeholderDetail.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Hintergrundbild\nMapDetailView.constructor()");
        }

        this.setVisible(true);
    }

    /*Zeichnet das Platzhalterbild
    * Wenn eine Mission ausgewählt wurde (isMissionSelected = true) wird die entsprechende DetailMap gezeichnet*/
    public void paintComponent(Graphics detailMap)
    {
        super.paintComponent(detailMap);
        if(this.isMissionSelected)
            detailMap.drawImage(this.detailMap, 0, 0, this);
        else
            detailMap.drawImage(this.placeholderDetail, 0, 0, this);
    }

    /*Liest die angeforderte DetailMap [currentDetailMap(DetailKartenModel)] aus dem DataManager aus -> DataManager.getDetailKarte()
    * lädt das Bild der currentDetailMap
    * fügt für jedes Koordinatenpaar aus der koordinatenModelList der currentDetailMap ein DetailSelectionIem zur View hinzu
    * setzt isMissionSelected auf true
    * this.repaint()*/
    public void selectMission(String paramMapName)
    {
        DetailKartenModel currentDetailMap = this.mapController.getGameFrameController().getDataManager().getDetailKarte(paramMapName);
        LinkedList<KoordinatenModel> koordinatenModelList = currentDetailMap.getKoordinatenModelList();

        try
        {
            this.detailMap = ImageIO.read(new File(currentDetailMap.getUrl()));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von detailMap\nMapDetailView.selectMission()");
        }

        for(int i = 0; i < koordinatenModelList.size(); i++)
        {
            KoordinatenModel currentModel = koordinatenModelList.get(i);
            int xPos = currentModel.getxPosition();
            int yPos = currentModel.getyPosition();
            this.add(new DetailSelectionItem(paramMapName + "_" + i, xPos, yPos, this.mapController));
        }
        this.isMissionSelected = true;
        this.repaint();
    }
}
