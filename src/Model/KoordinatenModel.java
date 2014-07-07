package Model;

public class KoordinatenModel
{
    int xPosition, yPosition;

    public KoordinatenModel(int newX, int newY)
    {
        xPosition = newX;
        yPosition = newY;
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
