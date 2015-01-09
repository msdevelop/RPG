package Controller;

import CustomExceptions.CustomImageException;
import View.*;
import View.SelectionItem.DetailSelectionItem;
import View.SelectionItem.OverviewSelectionItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapController implements MouseListener
{
    private MapOverview mapOverview;
    private MapDetailView mapDetailView;
    private GameFrameController gameFrameController;

    /**
     * erzeugt MapOverview(View)
     * erzeugt MapDetailView(View)
     */
    public MapController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;

        try
        {
            this.mapOverview = new MapOverview(this);
            this.mapDetailView = new MapDetailView(this);
        }
        catch(CustomImageException e)
        {
            this.gameFrameController.handleCustomImageException(e);
        }
        this.gameFrameController.getGameFrame().getContentPane().add(this.mapOverview);
        this.gameFrameController.getGameFrame().getContentPane().add(this.mapDetailView);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        /**MouseClicked für OverviewSelectionItem(SelectionItem)*/
        String tmpComponentName = e.getComponent().getName();

        if(tmpComponentName.startsWith("overview"))
        {
            /**Gibt den Namen der in der View ausgewählten Karte an die MapDetailView weiter um den Kartenausschnitt anzuzeigen
             * entfernt alle OverviewSelectionItems von der MapOverview*/
            try
            {
                this.mapDetailView.selectMission("detail_" + tmpComponentName.substring(9));
            }
            catch(CustomImageException ciE)
            {
                this.gameFrameController.handleCustomImageException(ciE);
            }
            this.mapOverview.disableView();
        }
        /**MouseClicked für DetailSelectionItem(SelectionItem)*/
        else if(tmpComponentName.startsWith("detail"))
        {
            this.mapOverview.setVisible(false);
            this.mapDetailView.setVisible(false);
            this.gameFrameController.getGameFrame().getContentPane().remove(this.mapOverview);
            this.gameFrameController.getGameFrame().getContentPane().remove(this.mapDetailView);
            this.gameFrameController.initiateLevel(tmpComponentName.substring(7));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        /**MouseEntered für OverviewSelectionItem*/
        String tmpComponentName = e.getComponent().getName();

        if(tmpComponentName.startsWith("overview"))
        {
            OverviewSelectionItem tmpOverviewItem = (OverviewSelectionItem) e.getComponent();
            tmpOverviewItem.setMouseFocus(true);
        }
        /**MouseEntered für DetailSelectionItem*/
        else if(tmpComponentName.startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.blue, 2, true));
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        /**MouseExited für OverviewSelectionItem*/
        String tmpComponentName = e.getComponent().getName();

        if(tmpComponentName.startsWith("overview"))
        {
            OverviewSelectionItem tmpItem = (OverviewSelectionItem) e.getComponent();
            tmpItem.setMouseFocus(false);
        }
        /**MouseExited für DetailSelectionItem*/
        else if(tmpComponentName.startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
    }

    /**
     * Gibt aktuellen GameFrameController zurück
     */
    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override
    /**not in use*/ public void mousePressed(MouseEvent e)
    {

    }

    @Override
    /**not in use*/ public void mouseReleased(MouseEvent e)
    {

    }
}
