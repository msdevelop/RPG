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

    /*Baut die Verbindung zur angegebenen Datenbank auf*/
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

    /*Gleicht gegeben die Datenbanktabelle 'user' ab ob der übergebene Benutzername bereits vorhanden ist -> return false
    * Ist der Benutzername gültig, wird er der Datenbanktabelle 'user' hinzugefügt -> this.addUser() -> return true
    * SQLException -> return false*/
    public boolean isValidUsername(String paramUsername)
    {
        try
        {
            PreparedStatement pstmt = this.connection.prepareStatement("SELECT * FROM user WHERE (name = ?)");
            pstmt.setString(1, paramUsername);
            ResultSet userResult = pstmt.executeQuery();
            pstmt.close();
            if(userResult.next())
            {
                userResult.close();
                return false;
            }
            else
            {
                userResult.close();
                this.addUser(paramUsername);
                return true;
            }
        }
        catch(SQLException e)
        {
            return false;
        }
    }

    /*Fügt den übergebenen Benutzernamen der Datenbanktabelle 'user' hinzu (status = false)
    * Das Datenfeld 'status <boolean>'  gibt an ob der Beutzer die CharakterSelektion bereits abgeschlossen hat (true) oder nicht (false = default)*/
    public void addUser(String paramUsername) throws SQLException
    {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("INSERT INTO user(name, status) VALUES('" + paramUsername + "', false)");
            stmt.close();
    }

    /*Prüft ob die angeforderte DetailMap bereits aus der DB ausgelesen und in der Liste detailKartenModelList gespeichert wurde -> return DetailKartenModel
    * Sonst: liest den Datensatz der angeforderten DetailMap aus der Datenbanktabelle 'detailMap' aus und erzeugt ein neues DetaiKartenModel aus den ausgelesenen Daten
    * fügt das neue DetailKartenModel der detailKartenModelList hinzu
    * -> return DetailKartenModel*/
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

    /*Wird von getDetailKarte aufgerufen um die Liste der x,y - Positionen aus der Datenbank in einzelne Koordinatenpaare aufzuteilen
    * x und y Position sind durch (,) getrennt, Koordinatenpaare durch (\n)
    * erzeugt aus einem Koordinatenpaar (x,y) ein KoordinatenModel
    * speichert alle KoordinatenModels in einer koordinatenModelList
    * -> return koordinatenModelList*/
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

    /*Liest alle Charaktere aus der Datenbanktabelle 'charakterRaw' aus
    * erzeugt für jeden Charakter ein CharakterModel und befüllt dieses mit den Daten aus dem Datensatz
    * speichert alle CharakterModel in einer charakterModelList
    * -> return charakterModelList*/
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
                CharakterModel tmpModel = new CharakterModel(charResult.getInt("charID"), charResult.getInt("mut"), charResult.getInt("klugheit"), charResult.getInt("intuition"),
                        charResult.getInt("charisma"), charResult.getInt("fingerfertigkeit"), charResult.getInt("gewandheit"), charResult.getInt("koerperkraft"),
                        charResult.getInt("lebensPkte"), charResult.getInt("astralPkte"), charResult.getInt("aberglaube"), charResult.getInt("koerperbeherrschung"),
                        charResult.getInt("selbstbeherrschung"), charResult.getInt("aexteBeile"), charResult.getInt("dolche"), charResult.getInt("schwertSblEh"),
                        charResult.getInt("schwertSblZh"), charResult.getInt("fechtwaffen"), charResult.getInt("speerStab"), charResult.getInt("stumpfEh"),
                        charResult.getInt("stumpfZh"), charResult.getInt("armbrust"), charResult.getInt("bogen"), charResult.getInt("stufe"),
                        charResult.getInt("magieresistenz"), charResult.getInt("ausdauer"), charResult.getInt("attackeWert"), charResult.getInt("paradeWert"),
                        charResult.getInt("ausweichWert"), charResult.getInt("fernkampfWert"), charResult.getString("namensListe"), charResult.getString("klasse"),
                        charResult.getString("kopfEq"), charResult.getString("brustEq"), charResult.getString("waffenhandEq"), charResult.getString("nebenhandEq"),
                        charResult.getString("url"));

                charakterModelList.add(tmpModel);
            }
            charResult.close();
        }
        catch(SQLException e)
        {}
        return charakterModelList;
    }

    /*Erzeugt eine neue Datenbanktabelle nach dem Muster [Benutzername_charakter]
    * kopiert die Datensätze der Charaktere mit ID aus paramCharIDCollection aus der Datenbanktabelle 'charakterRaw' in die neu erstellte
    * die Namen der Charaktere werden durch die vom Benutzer ausgewählten Namen ersetzt
    * das Datenfeld 'status' in der Datenbanktabelle 'user' wird für den Benutzer 'paramUser' auf true gesetzt*/
    public void createNewCharTableForUser(String paramUser, int[] paramCharIDCollection, String[] paramCharNameCollection)
    {
        try
        {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("CREATE TABLE " + paramUser + "_charakter AS (SELECT * FROM charakterraw WHERE charID = " + paramCharIDCollection[0]
            + " OR charID = " + paramCharIDCollection[1] + " OR charID = " + paramCharIDCollection[2] + " OR charID = " + paramCharIDCollection[3]
            + " OR charID = " + paramCharIDCollection[4] + " OR charID = " + paramCharIDCollection[5] + ") WITH DATA");
            for(int i = 0; i < 6; i++)
            {
                stmt.executeQuery("UPDATE " + paramUser + "_charakter SET namensliste = '" + paramCharNameCollection[i] + "' WHERE charID = " + paramCharIDCollection[i]);
            }
            stmt.executeQuery("UPDATE user SET status = true WHERE name = '" + paramUser + "'");
            stmt.close();
        }
        catch(SQLException e)
        {}
    }

    /*Wird immer dann aufgerufen wenn das Programm geschlossen wird (außer ALT+F4 oder Absturz)
    * Schließt die Datenbankverbindung (falls vorhanden) um COMMIT der Daten zu gewährleisten*/
    public void closeConnection()
    {
        try
        {
            if(this.connection != null)
                this.connection.close();

        }
        catch(SQLException e)
        {}
    }
}