package Model;

public class KoordinatenModel
{
    int xPosition, yPosition;

    public KoordinatenModel(int newX, int newY)
    {
        xPosition = newX;
        yPosition = newY;
    }

    public void setxPosition(int newX)
    {
        xPosition = newX;
    }

    public void setyPosition(int newY)
    {
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
