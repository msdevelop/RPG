package Data;

import java.io.File;
import java.util.LinkedList;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Controller.GameFrameController;
import Model.KoordinatenModel;
import Model.DetailKartenModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DataManager
{
    private DocumentBuilder docBuilder;
    private DocumentBuilderFactory docFactory;
    private Document doc;
    private NodeList nodeKartenListe;
    private Node root;
    private List<DetailKartenModel> detailKartenModelList = new LinkedList<DetailKartenModel>();
    private List<KoordinatenModel> koordinatenModelList = new LinkedList<KoordinatenModel>();
    private GameFrameController gameFrameController;

    public DataManager(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;

        docFactory = DocumentBuilderFactory.newInstance();

        try
        {
            docBuilder = docFactory.newDocumentBuilder();
        }
        catch(ParserConfigurationException pce)
        {
        }
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

        try
        {
            File detailMapXmlFile = new File("data//xml//detailMap.xml");
            doc = docBuilder.parse(detailMapXmlFile);
            root = doc.getDocumentElement();
        }
        catch(Exception e)
        {}

        //get Liste aller Karten
        if(root.hasChildNodes())
        {
            nodeKartenListe = root.getChildNodes();
        }

        Element detailMap = null;

        //suche passende Karte nach Kartennamen
        for(int i = 0; i < nodeKartenListe.getLength(); i++)
        {
            detailMap = (Element) nodeKartenListe.item(i);
            if(detailMap.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(paramMapName))
                break;
        }

        String url = detailMap.getElementsByTagName("url").item(0).getFirstChild().getNodeValue();
        NodeList koordinatenList = detailMap.getElementsByTagName("position").item(0).getChildNodes();

        for(int i = 0; i < koordinatenList.getLength(); i++)
        {
            NodeList tmpList = koordinatenList.item(i).getChildNodes();

            int xPos = Integer.parseInt(tmpList.item(0).getFirstChild().getNodeValue());
            int yPos = Integer.parseInt(tmpList.item(1).getFirstChild().getNodeValue());

            koordinatenModelList.add(new KoordinatenModel(xPos, yPos));
        }
        DetailKartenModel tmpModel = new DetailKartenModel(paramMapName, url, koordinatenModelList);
        detailKartenModelList.add(tmpModel);

        return tmpModel;
    }
}


