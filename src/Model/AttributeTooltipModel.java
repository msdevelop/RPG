package Model;

public class AttributeTooltipModel
{
    private String name, tooltipText;

    public AttributeTooltipModel(String paramName, String paramTooltipText)
    {
        this.name = paramName;
        this.tooltipText = paramTooltipText;

    }

    public String getName()
    {
        return this.name;
    }

    public String getTooltipText()
    {
        return this.tooltipText;
    }
}
