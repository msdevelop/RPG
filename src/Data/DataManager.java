package Data;

import Model.CharakterModel;
import Model.DetailKartenModel;
import Model.KoordinatenModel;

import java.sql.*;
import java.util.LinkedList;

public class DataManager
{
    private LinkedList<DetailKartenModel> detailKartenModelList = new LinkedList<DetailKartenModel>();
    private Connection connection = null;

    public DataManager()
    {
        try
        {
            Class.forName( "org.hsqldb.jdbcDriver" );
            this.connection = DriverManager.getConnection("jdbc:hsqldb:file:data\\hsql\\db", "root", "");
        }
        catch (Exception e)
        {}
    }

    public DetailKartenModel getDetailKarte(String paramMapName)
    {
        for(int j = 0; j < detailKartenModelList.size(); j++)
        {
            if(detailKartenModelList.get(j).getName().equals(paramMapName))
            {
                return detailKartenModelList.get(j);
            }
        }

        DetailKartenModel tmpModel = null;
        try
        {
            PreparedStatement pstmt = this.connection.prepareStatement("SELECT * FROM detailMap WHERE (name = ?)");
            pstmt.setString(1, paramMapName);
            ResultSet result = pstmt.executeQuery();
            pstmt.close();
            result.next();
            tmpModel = new DetailKartenModel(paramMapName, result.getString(2), this.trimPosition(result.getString(3)));
            this.detailKartenModelList.add(tmpModel);
        }
        catch(SQLException e)
        {}
        return tmpModel;
    }

    public LinkedList<KoordinatenModel> trimPosition(String paramPos)
    {
        LinkedList<KoordinatenModel> koordinatenModelList = new LinkedList<KoordinatenModel>();
        int k = 0;
        int posLength = paramPos.length();

        while(k < posLength)
        {
            String xPos = "";
            String yPos = "";

            while(paramPos.charAt(k) != ',')
            {
                xPos += paramPos.charAt(k);
                k++;
            }
            k++;

            while(paramPos.charAt(k) != '\n')
            {
                yPos += paramPos.charAt(k);
                k++;
                if(k >= posLength)
                    break;
            }
            k++;
            koordinatenModelList.add(new KoordinatenModel(Integer.parseInt(xPos), Integer.parseInt(yPos)));
        }
        return koordinatenModelList;
    }

    public CharakterModel getCharakterRaw()
    {return null;}
}