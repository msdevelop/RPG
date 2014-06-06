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
            Class.forName("org.hsqldb.jdbcDriver");
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
            ResultSet detMapResult = pstmt.executeQuery();
            pstmt.close();
            detMapResult.next();
            tmpModel = new DetailKartenModel(paramMapName, detMapResult.getString(2), this.trimPosition(detMapResult.getString(3)));
            this.detailKartenModelList.add(tmpModel);
            detMapResult.close();
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

    public LinkedList<CharakterModel> getCharaktersRaw()
    {
        LinkedList<CharakterModel> charakterModelList = new LinkedList<CharakterModel>();

        try
        {
            Statement stmt = this.connection.createStatement();
            ResultSet charResult = stmt.executeQuery("SELECT * FROM charakterRaw");
            stmt.close();
            while(charResult.next())
            {
                CharakterModel tmpModel = new CharakterModel(charResult.getInt("mut"), charResult.getInt("klugheit"), charResult.getInt("intuition"),
                        charResult.getInt("charisma"), charResult.getInt("fingerfertigkeit"), charResult.getInt("gewandheit"), charResult.getInt("koerperkraft"),
                        charResult.getInt("lebensPkte"), charResult.getInt("astralPkte"), charResult.getInt("aberglaube"), charResult.getInt("koerperbeherrschung"),
                        charResult.getInt("selbstbeherrschung"), charResult.getInt("aexteBeile"), charResult.getInt("dolche"), charResult.getInt("schwertSblEh"),
                        charResult.getInt("schwertSblZh"), charResult.getInt("fechtwaffen"), charResult.getInt("speerStab"), charResult.getInt("stumpfEh"),
                        charResult.getInt("stumpfZh"), charResult.getInt("armbrust"), charResult.getInt("bogen"), charResult.getInt("stufe"),
                        charResult.getInt("magieresistenz"), charResult.getInt("ausdauer"), charResult.getInt("attackeWert"), charResult.getInt("paradeWert"),
                        charResult.getInt("ausweichWert"), charResult.getInt("fernkampfWert"), charResult.getString("namensListe"), charResult.getString("klasse"),
                        charResult.getString("kopfEq"), charResult.getString("brustEq"), charResult.getString("waffenhandEq"), charResult.getString("nebenhandEq"));

                charakterModelList.add(tmpModel);
            }
            charResult.close();
        }
        catch(SQLException e)
        {}
        return charakterModelList;
    }
}