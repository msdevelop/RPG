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
    public List<DetailKartenModel> detailKartenModelList = new LinkedList<DetailKartenModel>();
    private List<KoordinateModel> koordinateModelList = new LinkedList<KoordinateModel>();

    public DataManager(String mapName)
    {
        docFactory = DocumentBuilderFactory.newInstance();

        try
        {
            docBuilder = docFactory.newDocumentBuilder();
        }
        catch(ParserConfigurationException pce)
        {
        }

        LoadData(mapName);
    }

    public void LoadData(String mapName)
    {
        getDetailKarte(mapName);
    }

    public void getDetailKarte(String kartenName)
    {
        try
        {
            File xmlFile = new File("data//xml//DetailKarten.xml");
            doc = docBuilder.parse(xmlFile);
            root = doc.getDocumentElement();
        }
        catch(Exception e)
        {
        }

        //get Liste aller Karten
        if(root.hasChildNodes())
        {
            nodeKartenListe = root.getChildNodes();
        }

        Node karte = null;
        Element rootElement = null;

        //suche passende Karte nach Kartennamen
        for(int i = 0; i < nodeKartenListe.getLength(); i++)
        {
            int asd = nodeKartenListe.getLength();
            rootElement = (Element) nodeKartenListe.item(i);
            String name = rootElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            System.out.println("name: " + name);
            if(name.equals(kartenName))
            {
                karte = nodeKartenListe.item(i);
                break;
            }
        }


        int x, y;
        String url = null;
        //get
        if(karte.hasChildNodes())
        {
            //NodeList kartenChildNodeList = karte.getChildNodes();
            url = rootElement.getElementsByTagName("url").item(0).getFirstChild().getNodeValue();
            System.out.println("url: " + url);
           // if(kartenChildNodeList.item(i).hasChildNodes())
            //{
                //NodeList koordinatenNodeList = kartenChildNodeList.item(2).getChildNodes();
                NodeList kartenChildNodeList = rootElement.getElementsByTagName("position").item(0).getChildNodes();
                for(int i = 0; i < kartenChildNodeList.getLength(); i++)
                {
                    NodeList temp = kartenChildNodeList.item(i).getChildNodes();
                    //NamedNodeMap nodeMap = kartenChildNodeList.item(i).getAttributes();


                    Node xNode = temp.item(0);
                    Node yNode = temp.item(1);
                    x = Integer.parseInt(xNode.getFirstChild().getNodeValue());
                    y = Integer.parseInt(yNode.getFirstChild().getNodeValue());

                    System.out.println("x: " + x);
                    System.out.println("y: " + y);
                    koordinateModelList.add(new KoordinateModel(x, y));
                }

           // }
           /* else
            {
                if(kartenChildNodeList.item(i).getNodeName().equals("url"))
                {
                    url = rootElement.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
                    System.out.println("url: " + url);
                }

            }*/
        }
        DetailKartenModel m = new DetailKartenModel(kartenName, url, koordinateModelList);
        System.out.println("OK");
        detailKartenModelList.add(m);
    }
}


