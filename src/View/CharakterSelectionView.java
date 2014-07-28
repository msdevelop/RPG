package View;

import Controller.CharakterSelectionController;
import Model.CharakterModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class CharakterSelectionView extends JPanel
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

    /*liest das Hintergrundbild ein -> this.bgImage
    * erzeugt ein JTextField zur Eingabe des Charakternamens
    * erzeugt einen JButton zur Randomisierung des Charakternamens*/
    public CharakterSelectionView(CharakterSelectionController paramController)
    {
        try
        {
            this.bgImage = ImageIO.read(new File("data//img//charSelection//charSelection.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Hintergrundbild\n CharakterSelectionView.constructor()");
        }

        this.txtfName = new JTextField();
        this.txtfName.setBounds(549, 259, 155, 30);
        this.txtfName.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
        this.txtfName.setForeground(Color.WHITE);
        this.txtfName.setBackground(Color.DARK_GRAY);
        this.txtfName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        this.add(this.txtfName);

        JButton btnRandomName = new JButton();
        btnRandomName.setIcon(new ImageIcon("data//img//charSelection//button//random.png"));
        btnRandomName.setBounds(405, 252, 42, 42);
        btnRandomName.addActionListener(paramController);
        this.add(btnRandomName);

        this.setLayout(null);
        this.setBounds(0, 0, 1920, 1057);
        this.setVisible(true);
    }

    /*Zeichnet das Hintergrundbild
    * wenn ein Charakter ausgewählt wurde (charIsSelected = true) werden die Werte des Charakters in die View gezeichnet
    * zeichnet die Bilder der sich aktuell in der Gruppe befindlichen Charaktere*/
    public void paintComponent(Graphics charBg)
    {
        super.paintComponent(charBg);

        charBg.drawImage(this.bgImage, 0, 0, this);

        if(this.isCharSelected)
        {
            charBg.setColor(Color.white);
            charBg.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));

            charBg.drawString(this.klasse, 549, 233);
            charBg.drawString(this.lebensPkte, 825, 235);
            charBg.drawString(this.astralPkte, 825, 280);
            charBg.drawString(this.mut, 825, 335);
            charBg.drawString(this.klugheit, 825, 375);
            charBg.drawString(this.intuition, 825, 415);
            charBg.drawString(this.charisma, 825, 450);
            charBg.drawString(this.fingerfertigkeit, 825, 485);
            charBg.drawString(this.gewandheit, 825, 530);
            charBg.drawString(this.koerperkraft, 825, 565);
            charBg.drawString(this.aberglaube, 825, 605);
            charBg.drawString(this.ausdauer, 825, 655);
            charBg.drawString(this.magieresistenz, 825, 695);
            charBg.drawString(this.koerperbeherrschung, 825, 741);
            charBg.drawString(this.selbstbeherrschung, 825, 785);
            charBg.drawString(this.aexteBeile, 1060, 285);
            charBg.drawString(this.fechtwaffen, 1060, 325);
            charBg.drawString(this.dolche, 1060, 365);
            charBg.drawString(this.armbrust, 1060, 405);
            charBg.drawString(this.bogen, 1060, 440);
            charBg.drawString(this.attackeWert, 1060, 490);
            charBg.drawString(this.paradeWert, 1060, 530);
            charBg.drawString(this.ausweichWert, 1060, 570);
            charBg.drawString(this.fernkampfWert, 1060, 608);
            charBg.drawString(this.stumpfEh, 1400, 285);
            charBg.drawString(this.stumpfZh, 1400, 325);
            charBg.drawString(this.schwertSblEh, 1400, 365);
            charBg.drawString(this.schwertSblZh, 1400, 405);
            charBg.drawString(this.speerStab, 1400, 440);
        }

        if(this.selectedCharakterImages.size() > 0)
        {
            for(int i = 0; i < this.selectedCharakterImages.size(); i++)
            {
                try
                {
                    Image tmpImage = ImageIO.read(new File(this.selectedCharakterImages.get(i)));
                    charBg.drawImage(tmpImage, 642 + (75 * i), 825, this);
                }
                catch(IOException e)
                {
                    System.err.println("IOException\nFehler beim Laden von Charakterbildern\nCharakterSelectionView.paintComponent()");
                }
            }
        }
    }

    /*speichert die Werte des übergebenen CharakterModels in den globalen Variablen der Klasse
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

    /*Wählt zufällig einen Namen aus der namensListe des Charakters aus
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

    /*fügt die übergebene BildURL der Liste selectedCharakterImages hinzu
    * das Charakterbild wird jetzt in der Gruppenauswahl der View angezeigt
    * this.repaint()*/
    public void addSelectedCharakterImage(String paramUrl)
    {
        this.selectedCharakterImages.add(paramUrl);
        this.repaint();
    }

    /*entfernt die BildURL des zuletzt zur Gruppe hinzugefügten Charakter aus der Liste selectedCharakterImages
    * entfernt somit das Bild aus der Gruppenauswahl der View
    * this.repaint()*/
    public void removeSelectedCharakterImage()
    {
        this.selectedCharakterImages.removeLast();
        this.repaint();
    }

    /*setzte isCharSelected auf den übergebenen Wert
    * löscht den Inhalt des JTextFields txtfName
    * this.repaint()*/
    public void setIsCharSelected(boolean paramBool)
    {
        this.isCharSelected = paramBool;
        this.txtfName.setText("");
        this.repaint();
    }

    /*Gibt den aktuellen Wert von isCharSelected zurück*/
    public boolean getIsCharSelected()
    {
        return this.isCharSelected;
    }

    /*Gibt den aktuellen Inhalt des JTextFields txtfName zurück*/
    public String getNameFromTextfield()
    {
        return this.txtfName.getText();
    }
}
