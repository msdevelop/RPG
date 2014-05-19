package Data;

import java.io.File;
import java.util.LinkedList;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import Model.KoordinateModel;
import org.w3c.dom.*;
import Model.DetailKartenModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Created by msmichi on 19.05.2014.
 */
public class DataManager
{
    private DocumentBuilder docBuilder;
    private Document doc;
    private NodeList nodeKartenListe;
    private List<DetailKartenModel> detailKartenModelList;
    private List<KoordinateModel> koordinateModelList = new LinkedList<KoordinateModel>();

    public DataManager()
    {
        LoadData();
    }

    public void LoadData()
    {

    }

    public void getDetailKarte(String kartenName)
    {
        try
        {
            File XmlFile = new File("DetailKarten.xml");
            doc = docBuilder.parse(XmlFile);
            //nodeKartenListe = doc.getElementsByTagName("name");
        }
        catch(Exception e)
        {
        }

        if(doc.hasChildNodes())
        {
            nodeKartenListe = doc.getChildNodes();
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


