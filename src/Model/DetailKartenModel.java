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

    public String getName()
    {
        return this.name;
    }

    public LinkedList<KoordinatenModel> getKoordinatenModelList()
    {
        return this.koordinatenModelList;
    }

    public String getUrl()
    {
        return this.url;
    }
}
