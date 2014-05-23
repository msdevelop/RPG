package Model;

import java.util.List;

public class DetailKartenModel
{
    private String name, url;
    private List<KoordinateModel> koordinateModelList;

    public DetailKartenModel(String paramKartenName, String paramUrl, List<KoordinateModel> paramListe)
    {
        name = paramKartenName;
        url = "data//img//map//detail//" + paramUrl;
        koordinateModelList = paramListe;
    }

    public void setName(String kartenName)
    {
        name = kartenName;
    }

    public void setUrl(String kartenUrl)
    {
        url = kartenUrl;
    }

    public void setKoordinateModelList(List<KoordinateModel> liste)
    {
        koordinateModelList = liste;
    }

    public String getName()
    {
        return name;
    }
    public String getUrl()
    {
        return url;
    }
    public List<KoordinateModel> getKoordinateModelList()
    {
        return koordinateModelList;
    }
}
