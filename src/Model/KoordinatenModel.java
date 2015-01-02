package Model;

public class KoordinatenModel
{
    private int xPosition, yPosition;

    public KoordinatenModel(int newX, int newY)
    {
        this.xPosition = newX;
        this.yPosition = newY;
    }

    public int getxPosition()
    {
        return xPosition;
    }

    public int getyPosition()
    {
        return yPosition;
    }
}
