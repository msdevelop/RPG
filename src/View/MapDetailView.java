package View;

import Controller.MapController;
import Interface.ScreenResolution;
import Model.DetailKartenModel;
import Model.KoordinatenModel;
import View.SelectionItem.DetailSelectionItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MapDetailView extends JPanel implements ScreenResolution
{
    private Image detailMap, placeholderDetail;
    private boolean isMissionSelected = false;
    private MapController mapController;
    private int screenWidth, screenHeight;

    /**Lädt Platzhalterbild der MapDetailView*/
    public MapDetailView(MapController paramMapController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;

        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(this.calculateXPos(811), 0, this.calculateXPos(1109), this.calculateYPos(1057));

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

    /**Zeichnet das Platzhalterbild
    * Wenn eine Mission ausgewählt wurde (isMissionSelected = true) wird die entsprechende DetailMap gezeichnet*/
    public void paintComponent(Graphics detailMap)
    {
        super.paintComponent(detailMap);
        if(this.isMissionSelected)
            detailMap.drawImage(this.detailMap, 0, 0, this.calculateXPos(1109), this.calculateYPos(1057), this);
        else
            detailMap.drawImage(this.placeholderDetail, 0, 0, this.calculateXPos(1109), this.calculateYPos(1057), this);
    }

    /**Liest die angeforderte DetailMap [currentDetailMap(DetailKartenModel)] aus dem DataManager aus -> DataManager.getDetailKarte()
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

    /**Berechnet die relative x-Position abhängig von der Bildschirmauflösung*/
    private int calculateXPos(int paramX)
    {
        return ((paramX * this.screenWidth) / 1920);
    }

    /**Berechnet die relative y-Position abhängig von der Bildschirmauflösung*/
    private int calculateYPos(int paramY)
    {
        return ((paramY * this.screenHeight) / 1080);
    }
}
