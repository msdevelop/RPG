package Model;

import java.util.LinkedList;

public class DetailKartenModel
{
    private String name;
    private String url;
    private LinkedList<KoordinatenModel> koordinatenModelList;

    public DetailKartenModel(String paramKartenName, String paramUrl, LinkedList<KoordinatenModel> paramListe)
    {
        this.name = paramKartenName;
        this.url = paramUrl;
        this.koordinatenModelList = paramListe;
    }

    public void setName(String paramKartenName)
    {
        this.name = paramKartenName;
    }

    public void setKoordinatenModelList(LinkedList<KoordinatenModel> paramListe)
    {
        this.koordinatenModelList = paramListe;
    }

    public String getName()
    {
        return this.name;
    }

    public LinkedList<KoordinatenModel> getKoordinatenModelList()
    {
        return this.koordinatenModelList;
    }

    public void setUrl(String paramUrl)
    {
        this.url = paramUrl;
    }

    public String getUrl()
    {
        return this.url;
    }
}
