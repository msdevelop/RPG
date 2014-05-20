package Model;

import java.util.List;

public class DetailKartenModel
{
    private String name;
    private List<KoordinateModel> koordinateModelList;

    public DetailKartenModel(String paramKartenName, List<KoordinateModel> paramListe)
    {
        name = paramKartenName;
        koordinateModelList = paramListe;
    }

    public void setName(String kartenName)
    {
        name = kartenName;
    }

    public void setKoordinateModelList(List<KoordinateModel> liste)
    {
        koordinateModelList = liste;
    }

    public String getName()
    {
        return name;
    }

    public List<KoordinateModel> getKoordinateModelList()
    {
        return koordinateModelList;
    }
}
