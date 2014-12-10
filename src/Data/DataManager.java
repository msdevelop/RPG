package Data;

import Model.*;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;

public class DataManager
{
    private LinkedList<DetailKartenModel> detailKartenModelList = new LinkedList<>();
    private Connection commonCon, levelCon;

    /**Lädt Treiberklasse
    * baut die Verbindung zur angegebenen Datenbank auf
    * -> errorMessage + exit(-1) sonst*/
    public DataManager()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");
        }
        catch(ClassNotFoundException cnfE)
        {
            JOptionPane.showMessageDialog(null, "ErrorMessage: " + cnfE.getMessage() + "\nExceptionType: ClassNotFoundException" +
                    "\nTreiberklasse konnte nicht geladen werden!", "Fehler beim Laden der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        try
        {
            this.commonCon = DriverManager.getConnection("jdbc:hsqldb:file:data\\hsql\\common\\db;ifexists=true;shutdown=true", "root", "");
            this.levelCon = DriverManager.getConnection("jdbc:hsqldb:file:data\\hsql\\level\\levelDB;ifexists=true;shutdown=true", "root", "");
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                    "\nErrorMessage: " + sqlE.getMessage() + "\nExceptionType: SQLException" +
                    "\nVerbindung zur Datenbank konnte nicht hergestellt werden!", "Fehler beim Laden der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
    }

    /**Wird immer dann aufgerufen wenn das Programm geschlossen wird (außer ALT+F4 oder Absturz)
     * Schließt die Datenbankverbindung (falls vorhanden) um commit der Daten zu gewährleisten*/
    public void closeConnection()
    {
        try
        {
            if(this.commonCon != null)
                this.commonCon.close();
            if(this.levelCon != null)
                this.levelCon.close();
            System.exit(-1);
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFFehler beim Schließen der connection!\nDataManager.closeConnection()" +
                            "\nMessage: " + sqlE.getMessage() + "\nErrorCode: " + sqlE.getErrorCode() + "\nSQLState: " + sqlE.getSQLState(), "Schließen einer connection fehlgeschlagen",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    /**Fügt den übergebenen Benutzernamen der Datenbanktabelle 'user' hinzu (status = false)
     * Das Datenfeld 'status <boolean>'  gibt an ob der Beutzer die CharakterSelektion bereits abgeschlossen hat (true) oder nicht (false = default)*/
    public void addUser(String paramUsername)
    {
        try(Statement stmt = this.commonCon.createStatement())
        {
            stmt.executeQuery("INSERT INTO user(name, status) VALUES('" + paramUsername + "', false)");
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                    "\nErrorMessage: " + sqlE.getMessage() + "\nExceptionType: SQLException" +
                    "\nFehler beim Hinzufügen von Benutzer!\nDataManager.isValidUsername()", "Fehler beim Schreiben der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
    }

    /**Gleicht gegeben die Datenbanktabelle 'user' ab ob der übergebene Benutzername bereits vorhanden ist -> return false
    * Ist der Benutzername gültig, wird er der Datenbanktabelle 'user' hinzugefügt -> this.addUser() -> return true
    * SQLException -> return false*/
    public boolean isValidUsername(String paramUsername)
    {
        try(PreparedStatement pstmt = this.commonCon.prepareStatement("SELECT * FROM user WHERE (name = ?)"))
        {
            pstmt.setString(1, paramUsername);

            try(ResultSet userResult = pstmt.executeQuery())
            {
                if(userResult.next())
                    return false;
                else
                {
                    this.addUser(paramUsername);
                    return true;
                }
            }
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                    "\nErrorMessage: " + sqlE.getMessage() + "\nExceptionType: SQLException" +
                    "\nFehler beim Prüfen von Benutzer!\nDataManager.isValidUsername()", "Fehler beim Lesen der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        return false;
    }

    /**Wird von getDetailKarte aufgerufen um die Liste der x,y - Positionen aus der Datenbank in einzelne Koordinatenpaare aufzuteilen
     * x und y Position sind durch (,) getrennt, Koordinatenpaare durch (\n)
     * erzeugt aus einem Koordinatenpaar (x,y) ein KoordinatenModel
     * speichert alle KoordinatenModels in einer koordinatenModelList
     * -> return koordinatenModelList*/
    public LinkedList<KoordinatenModel> trimPosition(String paramPos)
    {
        LinkedList<KoordinatenModel> koordinatenModelList = new LinkedList<>();
        int k = 0;
        int posLength = paramPos.length();

        while(k < posLength)
        {
            char tmpChar = paramPos.charAt(k);
            StringBuilder xPos = new StringBuilder("");

            while(tmpChar != ',')
            {
                xPos.append(tmpChar);
                k++;
                tmpChar = paramPos.charAt(k);
            }
            k++;
            tmpChar = paramPos.charAt(k);
            StringBuilder yPos = new StringBuilder("");
            while(tmpChar != '\n')
            {
                yPos.append(tmpChar);
                k++;
                if(k >= posLength)
                    break;
                tmpChar = paramPos.charAt(k);
            }
            k++;
            koordinatenModelList.add(new KoordinatenModel(Integer.parseInt(xPos.toString()), Integer.parseInt(yPos.toString())));
        }
        return koordinatenModelList;
    }

    /**Prüft ob die angeforderte DetailMap bereits aus der DB ausgelesen und in der Liste detailKartenModelList gespeichert wurde -> return DetailKartenModel
    * Sonst: liest den Datensatz der angeforderten DetailMap aus der Datenbanktabelle 'detailMap' aus und erzeugt ein neues DetaiKartenModel aus den ausgelesenen Daten
    * fügt das neue DetailKartenModel der detailKartenModelList hinzu
    * -> return DetailKartenModel*/
    public DetailKartenModel getDetailKarte(String paramMapName)
    {
        for(DetailKartenModel tmpDetailKartenModel : this.detailKartenModelList)
            if(tmpDetailKartenModel.getName().equals(paramMapName))
                return tmpDetailKartenModel;

        DetailKartenModel tmpModel = null;
        try(PreparedStatement pstmt = this.commonCon.prepareStatement("SELECT * FROM detailMap WHERE (name = ?)"))
        {
            pstmt.setString(1, paramMapName);
            try(ResultSet detMapResult = pstmt.executeQuery())
            {
                detMapResult.next();
                tmpModel = new DetailKartenModel(paramMapName, detMapResult.getString(2), this.trimPosition(detMapResult.getString(3)));
                this.detailKartenModelList.add(tmpModel);
            }
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                            "\nErrorMessage: " + sqlE.getMessage() + "\nExceptionType: SQLException" +
                            "\nFehler beim Auslesen von DetailKarte\nDataManager.getDetailKarte()",
                            "Fehler beim Lesen der Datenbank", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        return tmpModel;
    }

    /**Liest alle Charaktere aus der Datenbanktabelle 'charakterRaw' aus
    * erzeugt für jeden Charakter ein CharakterModel und befüllt dieses mit den Daten aus dem Datensatz
    * speichert alle CharakterModel in einer charakterModelList
    * -> return charakterModelList*/
    public LinkedList<CharakterModel> getCharaktersRaw()
    {
        LinkedList<CharakterModel> charakterModelList = new LinkedList<>();

        try(Statement stmt = this.commonCon.createStatement())
        {
            try(ResultSet charResult = stmt.executeQuery("SELECT * FROM charakterRaw"))
            {
                while(charResult.next())
                {
                    CharakterModel tmpModel = new CharakterModel(charResult.getInt("charID"), charResult.getInt("mut"), charResult.getInt("klugheit"), charResult.getInt("intuition"),
                            charResult.getInt("charisma"), charResult.getInt("fingerfertigkeit"), charResult.getInt("gewandheit"), charResult.getInt("koerperkraft"),
                            charResult.getInt("lebensPkte"), charResult.getInt("astralPkte"), charResult.getInt("aberglaube"), charResult.getInt("koerperbeherrschung"),
                            charResult.getInt("selbstbeherrschung"), charResult.getInt("aexteBeile"), charResult.getInt("dolche"), charResult.getInt("schwertSblEh"),
                            charResult.getInt("schwertSblZh"), charResult.getInt("fechtwaffen"), charResult.getInt("speerStab"), charResult.getInt("stumpfEh"), charResult.getInt("stumpfZh"),
                            charResult.getInt("armbrust"), charResult.getInt("bogen"), charResult.getInt("stufe"), charResult.getInt("magieresistenz"), charResult.getInt("ausdauer"),
                            charResult.getInt("attackeWert"), charResult.getInt("paradeWert"), charResult.getInt("ausweichWert"), charResult.getInt("fernkampfWert"),
                            charResult.getString("namensListe"), charResult.getString("klasse"), charResult.getString("kopfEq"), charResult.getString("brustEq"),
                            charResult.getString("waffenhandEq"), charResult.getString("nebenhandEq"), charResult.getString("url"));

                    charakterModelList.add(tmpModel);
                }
            }
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                            "\nErrorMessage: " + sqlE.getMessage() + "\nSQLException\nFehler beim Auslesen von Charakter\nDataManager.getCharaktersRaw()",
                            "Fehler beim Auslesen der Datenbank",JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        return charakterModelList;
    }

    /**Erzeugt eine neue Datenbanktabelle nach dem Muster [Benutzername_charakter]
    * kopiert die Datensätze der Charaktere mit ID aus paramChIDCol aus der Datenbanktabelle 'charakterRaw' in die neu erstellte
    * die Namen der Charaktere werden durch die vom Benutzer ausgewählten Namen ersetzt
    * das Datenfeld 'status' in der Datenbanktabelle 'user' wird für den Benutzer 'paramUser' auf true gesetzt*/
    public void createNewCharTableForUser(String paramUser, int[]paramChIDCol, String[] paramChNameCol)
    {
        try(Statement stmt = this.commonCon.createStatement())
        {
            stmt.executeQuery("CREATE TABLE " + paramUser + "_charakter AS (SELECT * FROM charakterraw WHERE charID = " + paramChIDCol[0] +
                    " OR charID = " + paramChIDCol[1] + " OR charID = " + paramChIDCol[2] + " OR charID = " + paramChIDCol[3] +
                    " OR charID = " + paramChIDCol[4] + " OR charID = " + paramChIDCol[5] + ") WITH DATA");
            for(int i = 0; i < 6; i++)
                stmt.executeQuery("UPDATE " + paramUser + "_charakter SET namensliste = '" + paramChNameCol[i] + "' WHERE charID = " + paramChIDCol[i]);
            stmt.executeQuery("UPDATE user SET status = true WHERE name = '" + paramUser + "'");
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLState: " + sqlE.getSQLState() + "\nErrorCode: " + sqlE.getErrorCode() +
                    "\nErrorMessage: " + sqlE.getMessage() + "\nSQLException\nFehler beim erzeugen von DBTable " + paramUser + "_charakter\nDataManager.createNewCharTableForUser()",
                    "Fehler beim Erstellen von Datenbanktabelle", JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
    }

    /**Liest Level aus der Datenbank aus
    * try-with-resource -> statements die in Klammern hinter dem try Aufruf erzeugt werden, werden automatisch geschlossen, wenn der try-block abgearbeitet ist
    * -> return MaterialModel[][]
    * -> kein ExceptionHandling => Level MUSS vorhanden sein*/
    public MaterialModel[][] loadLevel(String paramLevelName)
    {
        try(PreparedStatement stmt = this.levelCon.prepareStatement("SELECT * FROM " + paramLevelName))
        {
            try(ResultSet levelResult = stmt.executeQuery())
            {
                try(PreparedStatement pstmt = this.levelCon.prepareStatement("SELECT materialName FROM materialAllocation WHERE (materialID = ?)"))
                {
                    int j = 0;
                    MaterialModel[][] tmpMatModelArray = new MaterialModel[21][39];
                    while(levelResult.next())
                    {
                        for(int i = 0; i < 39; i++)
                        {
                            int tmpMatID = levelResult.getInt(i + 1);
                            pstmt.setInt(1, tmpMatID);
                            try(ResultSet materialResult = pstmt.executeQuery())
                            {
                                materialResult.next();
                                tmpMatModelArray[j][i] = new MaterialModel(materialResult.getString(1), tmpMatID);
                            }
                        }
                        j++;
                    }
                    return tmpMatModelArray;
                }
            }
        }
        catch(SQLException sqlE)
        {
            JOptionPane.showMessageDialog(null, "SQLException\nFehler beim Laden des Levels!\nDataManager.loadLevel()" +
                    "\nMessage: " + sqlE.getMessage() + "\nErrorCode: " + sqlE.getErrorCode() + "\nSQLState: " + sqlE.getSQLState(), "Laden von Level fehlgeschlagen",
                    JOptionPane.ERROR_MESSAGE);
            this.closeConnection();
        }
        return null;
    }
}