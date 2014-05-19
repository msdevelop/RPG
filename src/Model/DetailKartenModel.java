package Model;

import java.util.List;

/**
 * Created by msmichi on 19.05.2014.
 */
public class DetailKartenModel
{
    private String name;
    private List<Koordinate> koordinateList;

    public DetailKartenModel(String kartenName, List<Koordinate> liste)
    {
        name = kartenName;
        koordinateList = liste;
    }

    public void setName(String kartenName)
    {
        name = kartenName;
    }

    public void setKoordinateList(List<Koordinate> liste)
    {
        koordinateList = liste;
    }

    public String getName()
    {
        return name;
    }

    public List<Koordinate> getKoordinateList()
    {
        return koordinateList;
    }
}
