package View;

import Controller.GameFrameController;

import javax.swing.*;

public class GameFrame extends JFrame
{
    private GameFrameController gameFrameController;

    public GameFrame(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.setLayout(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /*Fügt dem aktuellen GameFrame eine MenuBar am oberen Bildschirmrand hinzu*/
    public void addMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuSpiel = new JMenu("Spiel");
        menuBar.add(menuSpiel);

        JMenuItem itemBeenden = new JMenuItem("Beenden");
        menuSpiel.add(itemBeenden);
        itemBeenden.addActionListener(this.gameFrameController);
        itemBeenden.setActionCommand("beenden");

        JMenu menuCharakter = new JMenu("Charakter");
        menuBar.add(menuCharakter);

        JMenuItem itemGruppe = new JMenuItem("Gruppe");
        menuCharakter.add(itemGruppe);

        JMenuItem itemInventar = new JMenuItem("Inventar");
        menuCharakter.add(itemInventar);

        JMenu menuHilfe = new JMenu("Hilfe");
        menuBar.add(menuHilfe);

        JMenuItem itemSteuerung = new JMenuItem("Steuerung");
        menuHilfe.add(itemSteuerung);
    }
}
