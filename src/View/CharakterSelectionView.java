package View;

import Controller.CharakterSelectionController;
import Interface.ScreenResolution;
import Model.CharakterModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class CharakterSelectionView extends JPanel implements ScreenResolution
{
    private Image bgImage;
    private JTextField txtfName;
    private boolean isCharSelected = false;
    private int nameIndex = - 1;
    private String klasse, lebensPkte, astralPkte;
    private String mut, klugheit, intuition, charisma, fingerfertigkeit, gewandheit, koerperkraft, aberglaube, koerperbeherrschung;
    private String selbstbeherrschung, aexteBeile, dolche, schwertSblEh, schwertSblZh, fechtwaffen, speerStab, stumpfEh, stumpfZh;
    private String armbrust, bogen, magieresistenz, ausdauer, attackeWert, paradeWert, ausweichWert, fernkampfWert;
    private LinkedList<String> namensListe;
    private LinkedList<String> selectedCharakterImages = new LinkedList<>();
    private int screenWidth, screenHeight;

    /**liest das Hintergrundbild ein -> this.bgImage
    * erzeugt ein JTextField zur Eingabe des Charakternamens
    * erzeugt einen JButton zur Randomisierung des Charakternamens*/
    public CharakterSelectionView(CharakterSelectionController paramController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;

        try
        {
            this.bgImage = ImageIO.read(new File("data//img//charSelection//charSelection.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Hintergrundbild\n CharakterSelectionView.constructor()");
        }

        this.txtfName = new JTextField();
        this.txtfName.setBounds(this.calculateXPos(549), this.calculateYPos(259), this.calculateXPos(155), this.calculateYPos(30));
        this.txtfName.setFont(new Font("TimesNewRoman", Font.PLAIN, this.calculateXPos(20)));
        this.txtfName.setForeground(Color.WHITE);
        this.txtfName.setBackground(Color.DARK_GRAY);
        this.txtfName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.add(this.txtfName);

        JButton btnRandomName = new JButton();
        btnRandomName.setIcon(new ImageIcon("data//img//charSelection//button//random.png"));
        btnRandomName.setBounds(this.calculateXPos(405), this.calculateYPos(252), this.calculateXPos(42), this.calculateXPos(42));
        btnRandomName.addActionListener(paramController);
        this.add(btnRandomName);

        this.setLayout(null);
        this.setBounds(0, 0, this.screenWidth, (this.screenHeight - 23));
        this.setVisible(true);
    }

    /**Zeichnet das Hintergrundbild
    * wenn ein Charakter ausgewählt wurde (charIsSelected = true) werden die Werte des Charakters in die View gezeichnet
    * zeichnet die Bilder der sich aktuell in der Gruppe befindlichen Charaktere*/
    public void paintComponent(Graphics charBg)
    {
        super.paintComponent(charBg);

        charBg.drawImage(this.bgImage, 0, 0, this.screenWidth, this.screenHeight, this);

        if(this.isCharSelected)
        {
            charBg.setColor(Color.white);
            charBg.setFont(new Font("TimesNewRoman", Font.PLAIN, this.calculateXPos(20)));

            charBg.drawString(this.klasse, this.calculateXPos(549), this.calculateYPos(233));
            charBg.drawString(this.lebensPkte, this.calculateXPos(825), this.calculateYPos(235));
            charBg.drawString(this.astralPkte, this.calculateXPos(825), this.calculateYPos(280));
            charBg.drawString(this.mut, this.calculateXPos(825), this.calculateYPos(335));
            charBg.drawString(this.klugheit, this.calculateXPos(825), this.calculateYPos(375));
            charBg.drawString(this.intuition, this.calculateXPos(825), this.calculateYPos(415));
            charBg.drawString(this.charisma, this.calculateXPos(825), this.calculateYPos(450));
            charBg.drawString(this.fingerfertigkeit, this.calculateXPos(825), this.calculateYPos(485));
            charBg.drawString(this.gewandheit, this.calculateXPos(825), this.calculateYPos(530));
            charBg.drawString(this.koerperkraft, this.calculateXPos(825), this.calculateYPos(565));
            charBg.drawString(this.aberglaube, this.calculateXPos(825), this.calculateYPos(605));
            charBg.drawString(this.ausdauer, this.calculateXPos(825), this.calculateYPos(655));
            charBg.drawString(this.magieresistenz, this.calculateXPos(825), this.calculateYPos(695));
            charBg.drawString(this.koerperbeherrschung, this.calculateXPos(825), this.calculateYPos(741));
            charBg.drawString(this.selbstbeherrschung, this.calculateXPos(825), this.calculateYPos(785));
            charBg.drawString(this.aexteBeile, this.calculateXPos(1060), this.calculateYPos(285));
            charBg.drawString(this.fechtwaffen, this.calculateXPos(1060), this.calculateYPos(325));
            charBg.drawString(this.dolche, this.calculateXPos(1060), this.calculateYPos(365));
            charBg.drawString(this.armbrust, this.calculateXPos(1060), this.calculateYPos(405));
            charBg.drawString(this.bogen, this.calculateXPos(1060), this.calculateYPos(440));
            charBg.drawString(this.attackeWert, this.calculateXPos(1060), this.calculateYPos(490));
            charBg.drawString(this.paradeWert, this.calculateXPos(1060), this.calculateYPos(530));
            charBg.drawString(this.ausweichWert, this.calculateXPos(1060), this.calculateYPos(570));
            charBg.drawString(this.fernkampfWert, this.calculateXPos(1060), this.calculateYPos(608));
            charBg.drawString(this.stumpfEh, this.calculateXPos(1400), this.calculateYPos(285));
            charBg.drawString(this.stumpfZh, this.calculateXPos(1400), this.calculateYPos(325));
            charBg.drawString(this.schwertSblEh, this.calculateXPos(1400), this.calculateYPos(365));
            charBg.drawString(this.schwertSblZh, this.calculateXPos(1400), this.calculateYPos(405));
            charBg.drawString(this.speerStab, this.calculateXPos(1400), this.calculateYPos(440));
        }

        if(this.selectedCharakterImages.size() > 0)
        {
            for(int i = 0; i < this.selectedCharakterImages.size(); i++)
            {
                try
                {
                    Image tmpImage = ImageIO.read(new File(this.selectedCharakterImages.get(i)));
                    charBg.drawImage(tmpImage, this.calculateXPos((642 + (75 * i))), this.calculateYPos((825 + (48 - (this.calculateXPos(48))))), this.calculateXPos(48), this.calculateXPos(48), this);
                }
                catch(IOException e)
                {
                    System.err.println("IOException\nFehler beim Laden von Charakterbildern\nCharakterSelectionView.paintComponent()");
                }
            }
        }
    }

    /**speichert die Werte des übergebenen CharakterModels in den globalen Variablen der Klasse
    * lässt einen Zufallsnamen aus der namensListe auswählen -> this.selectRandomName()
    * setzt isCharSelected(boolean) auf true um die ausgelesenen Werte in der View anzuzeigen
    * this.repaint()*/
    public void synchronizeCharProperties(CharakterModel paramModel)
    {
        this.klasse = paramModel.getKlasse();
        this.mut = String.valueOf(paramModel.getMut());
        this.klugheit = String.valueOf(paramModel.getKlugheit());
        this.intuition = String.valueOf(paramModel.getIntuition());
        this.charisma = String.valueOf(paramModel.getCharisma());
        this.fingerfertigkeit = String.valueOf(paramModel.getFingerfertigkeit());
        this.gewandheit = String.valueOf(paramModel.getGewandheit());
        this.koerperkraft = String.valueOf(paramModel.getKoerperkraft());
        this.aberglaube = String.valueOf(paramModel.getAberglaube());
        this.koerperbeherrschung = String.valueOf(paramModel.getKoerperbeherrschung());
        this.selbstbeherrschung = String.valueOf(paramModel.getSelbstbeherrschung());
        this.aexteBeile = String.valueOf(paramModel.getAexteBeile());
        this.dolche = String.valueOf(paramModel.getDolche());
        this.schwertSblEh = String.valueOf(paramModel.getSchwertSblEh());
        this.schwertSblZh = String.valueOf(paramModel.getSchwertSblZh());
        this.fechtwaffen = String.valueOf(paramModel.getFechtwaffen());
        this.speerStab = String.valueOf(paramModel.getSpeerStab());
        this.stumpfEh = String.valueOf(paramModel.getStumpfEh());
        this.stumpfZh = String.valueOf(paramModel.getStumpfZh());
        this.armbrust = String.valueOf(paramModel.getArmbrust());
        this.bogen = String.valueOf(paramModel.getBogen());
        this.magieresistenz = String.valueOf(paramModel.getMagieresistenz());
        this.ausdauer = String.valueOf(paramModel.getAusdauer());
        this.attackeWert = String.valueOf(paramModel.getAttackeWert());
        this.paradeWert = String.valueOf(paramModel.getParadeWert());
        this.ausweichWert = String.valueOf(paramModel.getAusweichWert());
        this.fernkampfWert = String.valueOf(paramModel.getFernkampfWert());
        this.lebensPkte = String.valueOf(paramModel.getLebensPkte());
        this.astralPkte = String.valueOf(paramModel.getAstralPkte());
        this.namensListe = paramModel.getNamensListe();

        this.selectRandomName();
        this.isCharSelected = true;
        this.repaint();
    }

    /**Wählt zufällig einen Namen aus der namensListe des Charakters aus
    * gleicher Name kann nicht zweimal hintereinander kommen
    * ausgewählter Name wird im JTextField txtfName ausgegeben*/
    public void selectRandomName()
    {
        int range = this.namensListe.size();
        try
        {
            Random randomizer = new Random();
            int tmpIndex = randomizer.nextInt(range);
            while(this.nameIndex == tmpIndex)
            {
                tmpIndex = randomizer.nextInt(range);
            }
            this.nameIndex = tmpIndex;
            this.txtfName.setText(this.namensListe.get(this.nameIndex));
        }
        catch(NullPointerException e)
        {
            System.err.println("NullPointerException\nFehler beim Generieren von randomNumber\nCharakterSelectionView.selectRandomName");
        }
    }

    /**fügt die übergebene BildURL der Liste selectedCharakterImages hinzu
    * das Charakterbild wird jetzt in der Gruppenauswahl der View angezeigt
    * this.repaint()*/
    public void addSelectedCharakterImage(String paramUrl)
    {
        this.selectedCharakterImages.add(paramUrl);
        this.repaint();
    }

    /**entfernt die BildURL des zuletzt zur Gruppe hinzugefügten Charakter aus der Liste selectedCharakterImages
    * entfernt somit das Bild aus der Gruppenauswahl der View
    * this.repaint()*/
    public void removeSelectedCharakterImage()
    {
        this.selectedCharakterImages.removeLast();
        this.repaint();
    }

    /**setzte isCharSelected auf den übergebenen Wert
    * löscht den Inhalt des JTextFields txtfName
    * this.repaint()*/
    public void setIsCharSelected(boolean paramBool)
    {
        this.isCharSelected = paramBool;
        this.txtfName.setText("");
        this.repaint();
    }

    /**Gibt den aktuellen Wert von isCharSelected zurück*/
    public boolean getIsCharSelected()
    {
        return this.isCharSelected;
    }

    /**Gibt den aktuellen Inhalt des JTextFields txtfName zurück*/
    public String getNameFromTextfield()
    {
        return this.txtfName.getText();
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
