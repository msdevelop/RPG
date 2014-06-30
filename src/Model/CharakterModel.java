package Model;

import java.util.LinkedList;

public class CharakterModel
{
    private LinkedList<String> namensListe;
    /**String name := name der vom spieler ausgewählt/gegeben wird*/
    //TODO bei charakterauswahl namen abfragen und eintragen
    private String klasse, waffenhandEq, nebenhandEq, brustEq, kopfEq, url, name;
    private int mut, klugheit, intuition, charisma, fingerfertigkeit, gewandheit, koerperkraft, aberglaube, koerperbeherrschung;
    private int selbstbeherrschung, aexteBeile, dolche, schwertSblEh, schwertSblZh, fechtwaffen, speerStab, stumpfEh, stumpfZh;
    private int armbrust, bogen, stufe, magieresistenz, ausdauer, attackeWert, paradeWert, ausweichWert, fernkampfWert;
    private int lebensPkte, astralPkte, charID;

    public CharakterModel(int charID, int mut, int klugheit, int intuition, int charisma, int fingerfertigkeit, int gewandheit, int koerperkraft, int lebensPkte, int astralPkte, int aberglaube,
            int koerperbeherrschung, int selbstbeherrschung, int aexteBeile, int dolche, int schwertSblEh, int schwertSblZh, int fechtwaffen, int speerStab, int stumpfEh, int stumpfZh,
            int armbrust, int bogen, int stufe, int magieresistenz, int ausdauer, int attackeWert, int paradeWert, int ausweichWert, int fernkampfWert,
            String namensListe, String klasse, String kopfEq, String brustEq, String waffenhandEq, String nebenhandEq, String url)
    {
        this.charID = charID;
        this.klasse = klasse;
        this.waffenhandEq = waffenhandEq;
        this.nebenhandEq = nebenhandEq;
        this.brustEq = brustEq;
        this.kopfEq = kopfEq;
        this.url = url;
        this.mut = mut;
        this.klugheit = klugheit;
        this.intuition = intuition;
        this.charisma = charisma;
        this.fingerfertigkeit = fingerfertigkeit;
        this.gewandheit = gewandheit;
        this.koerperkraft = koerperkraft;
        this.aberglaube = aberglaube;
        this.koerperbeherrschung = koerperbeherrschung;
        this.selbstbeherrschung = selbstbeherrschung;
        this.aexteBeile = aexteBeile;
        this.dolche = dolche;
        this.schwertSblEh = schwertSblEh;
        this.schwertSblZh = schwertSblZh;
        this.fechtwaffen = fechtwaffen;
        this.speerStab = speerStab;
        this.stumpfEh = stumpfEh;
        this.stumpfZh = stumpfZh;
        this.armbrust = armbrust;
        this.bogen = bogen;
        this.stufe = stufe;
        this.magieresistenz = magieresistenz;
        this.ausdauer = ausdauer;
        this.attackeWert = attackeWert;
        this.paradeWert = paradeWert;
        this.ausweichWert = ausweichWert;
        this.fernkampfWert = fernkampfWert;
        this.lebensPkte = lebensPkte;
        this.astralPkte = astralPkte;
        this.namensListe = this.trimNamensListe(namensListe);
    }

    public LinkedList<String> trimNamensListe(String paramNamensListe)
    {
        int i = 0;
        int paramLength = paramNamensListe.length();
        LinkedList<String> paramLinkedList = new LinkedList<String>();

        while(i < paramLength)
        {
            String tmpName = "";

            while(paramNamensListe.charAt(i) != '\n')
            {
                tmpName += paramNamensListe.charAt(i);
                i++;
                if(i >= paramLength)
                    break;
            }
            paramLinkedList.add(tmpName);
            i++;
        }
        return paramLinkedList;
    }

    public String getName()
    {
        return this.name;
    }

    public String getKlasse()
    {
        return this.klasse;
    }

    public String getWaffenhandEq()
    {
        return this.waffenhandEq;
    }

    public String getNebenhandEq()
    {
        return this.nebenhandEq;
    }

    public String getBrustEq()
    {
        return this.brustEq;
    }

    public String getKopfEq()
    {
        return this.kopfEq;
    }

    public String getUrl()
    {
        return this.url;
    }

    public int getCharID()
    {
        return this.charID;
    }

    public int getMut()
    {
        return this.mut;
    }

    public int getKlugheit()
    {
        return this.klugheit;
    }

    public int getIntuition()
    {
        return this.intuition;
    }

    public int getCharisma()
    {
        return this.charisma;
    }

    public int getFingerfertigkeit()
    {
        return this.fingerfertigkeit;
    }

    public int getGewandheit()
    {
        return this.gewandheit;
    }

    public int getKoerperkraft()
    {
        return this.koerperkraft;
    }

    public int getAberglaube()
    {
        return this.aberglaube;
    }

    public int getKoerperbeherrschung()
    {
        return this.koerperbeherrschung;
    }

    public int getSelbstbeherrschung()
    {
        return this.selbstbeherrschung;
    }

    public int getAexteBeile()
    {
        return this.aexteBeile;
    }

    public int getDolche()
    {
        return this.dolche;
    }

    public int getSchwertSblEh()
    {
        return this.schwertSblEh;
    }

    public int getSchwertSblZh()
    {
        return this.schwertSblZh;
    }

    public int getFechtwaffen()
    {
        return this.fechtwaffen;
    }

    public int getSpeerStab()
    {
        return this.speerStab;
    }

    public int getStumpfEh()
    {
        return this.stumpfEh;
    }

    public int getStumpfZh()
    {
        return this.stumpfZh;
    }

    public int getArmbrust()
    {
        return this.armbrust;
    }

    public int getBogen()
    {
        return this.bogen;
    }

    public int getStufe()
    {
        return this.stufe;
    }

    public int getMagieresistenz()
    {
        return this.magieresistenz;
    }

    public int getAusdauer()
    {
        return this.ausdauer;
    }

    public int getAttackeWert()
    {
        return this.attackeWert;
    }

    public int getParadeWert()
    {
        return this.paradeWert;
    }

    public int getAusweichWert()
    {
        return this.ausweichWert;
    }

    public int getFernkampfWert()
    {
        return this.fernkampfWert;
    }

    public int getLebensPkte()
    {
        return this.lebensPkte;
    }

    public int getAstralPkte()
    {
        return this.astralPkte;
    }

    public LinkedList<String> getNamensListe()
    {
        return this.namensListe;
    }
}