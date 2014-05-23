package Model;

import java.util.List;

public class DetailKartenModel
{
    private String name;
    private String url;
    private List<KoordinatenModel> koordinatenModelList;

    public DetailKartenModel(String paramKartenName, String paramUrl, List<KoordinatenModel> paramListe)
    {
        this.name = paramKartenName;
        this.url = paramUrl;
        this.koordinatenModelList = paramListe;
    }

    public void setName(String paramKartenName)
    {
        this.name = paramKartenName;
    }

    public void setKoordinatenModelList(List<KoordinatenModel> paramListe)
    {
        this.koordinatenModelList = paramListe;
    }

    public String getName()
    {
        return this.name;
    }

    public List<KoordinatenModel> getKoordinatenModelList()
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
