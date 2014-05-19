package Model;

/**
 * Created by msmichi on 19.05.2014.
 */
public class KoordinateModel
{
    int x, y;

    public KoordinateModel(int newX, int newY)
    {
        x = newX;
        y = newY;
    }

    public void setX(int newX)
    {
        x = newX;
    }

    public void setY(int newY)
    {
        y = newY;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
