package Controller;

import View.MapDetail;
import View.MapOverview;
import View.SeparatorPanel;

public class MapController
{
    private MapOverview mapOverview;
    private MapDetail mapDetail;
    private SeparatorPanel separatorPanel;

    public MapController()
    {
        this.mapOverview = new MapOverview();
        this.mapDetail = new MapDetail();
        this.separatorPanel = new SeparatorPanel();
    }

    public MapOverview getMapOverview()
    {
        return this.mapOverview;
    }

    public MapDetail getMapDetail()
    {
        return this.mapDetail;
    }

    public SeparatorPanel getSeparatorPanel()
    {
        return this.separatorPanel;
    }
}
