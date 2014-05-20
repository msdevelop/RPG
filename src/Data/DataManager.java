package Data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Model.KoordinateModel;
import org.w3c.dom.*;
import Model.DetailKartenModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DataManager
{
    private DocumentBuilder docBuilder;
    private DocumentBuilderFactory docFactory;
    private Document doc;
    private NodeList nodeKartenListe;
    private Node root;
    private List<DetailKartenModel> detailKartenModelList;
    private List<KoordinateModel> koordinateModelList = new LinkedList<KoordinateModel>();

    public DataManager()
    {
        docFactory = DocumentBuilderFactory.newInstance();

        try
        {
            docBuilder = docFactory.newDocumentBuilder();
        }
        catch(ParserConfigurationException pce)
        {}

        LoadData();
    }

    public void LoadData()
    {

    }

    public void getDetailKarte(String kartenName)
    {
        try
        {
            File xmlFile = new File("data//xml//DetailKarten.xml");
            doc = docBuilder.parse(xmlFile);
            root = doc.getDocumentElement();
            //nodeKartenListe = doc.getElementsByTagName("name");
        }
        catch(Exception e)
        {}

        if(root.hasChildNodes())
        {
            nodeKartenListe = root.getChildNodes();
        }

        Node karte = null;

        for(int i = 0; i < nodeKartenListe.getLength(); i++)
        {
            Element tmp = (Element) nodeKartenListe.item(i);
            String name = tmp.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            if(name.equals(kartenName))
            {
                karte = nodeKartenListe.item(i);
                break;
            }
        }


        int x, y;

        if(karte.hasChildNodes())
        {
            NodeList koordinateNodeList = karte.getChildNodes();
            for(int i = 0; i < koordinateNodeList.getLength(); i++)
            {
                if(koordinateNodeList.item(i).hasAttributes())
                {
                    NamedNodeMap nodeMap = koordinateNodeList.item(i).getAttributes();

                    Node xNode = nodeMap.item(0);
                    Node yNode = nodeMap.item(1);
                    x = Integer.parseInt(xNode.getNodeValue());
                    y = Integer.parseInt(yNode.getNodeValue());
                    koordinateModelList.add(new KoordinateModel(x, y));

                }
            }
        }
        detailKartenModelList.add(new DetailKartenModel(kartenName, koordinateModelList));
    }
}


