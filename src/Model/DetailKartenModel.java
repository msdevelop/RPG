package Model;

import java.util.List;

public class DetailKartenModel
{
    private String name;
    private List<KoordinateModel> koordinateModelList;

    public DetailKartenModel(String kartenName, List<KoordinateModel> liste)
    {
        name = kartenName;
        koordinateModelList = liste;
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