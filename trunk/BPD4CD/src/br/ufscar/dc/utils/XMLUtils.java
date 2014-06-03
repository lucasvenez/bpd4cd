package br.ufscar.dc.utils;

import java.util.HashMap;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Contains methods helpful for XML resources handle.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 *
 */
public class XMLUtils {

   
   public static Document document;
   
   static {
      try {
         
         document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      } 
      catch (ParserConfigurationException e) {
         e.printStackTrace();
      }
   }

   public static final Document createDocument(boolean standalone, String xmlVersion) {
      Document result = null;
      
      try {
         
         result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
         result.setXmlStandalone(standalone);
         result.setXmlVersion(xmlVersion);
      }
      catch (ParserConfigurationException e) {

         e.printStackTrace();
      }
      
      return result;
   }
   
   /**
    * This method return the first named node with a certain name from a node list
    * 
    * @param list - Investigated list
    * @param name - The wanted name
    * @return The first node with a certain name from a node list
    */
   public static Element getFirstNamedNode(NodeList list, String name) {
      Element result = null;
      
      for(int i = 0; i < list.getLength(); i++)
         if (list.item(i).getNodeName().equals(name)) {
            result = (Element)list.item(i);
            break;
         }
      
      return result;
   }
   
   public static Element createElement(String name) {
      return document.createElement(name);
   }
   
   public static CDATASection createCDATASection(String content) {
      return document.createCDATASection(content);
   }

   public static Element createElement(String name, HashMap<String, Object> attributes) {
      
      Element e = document.createElement(name);
      
      for(String a : attributes.keySet())
         if (attributes.get(a) instanceof String)
            e.setAttribute(a, (String)attributes.get(a));
      
      return e;
   }
   
   public static final Document getDocument() {
      return document;
   }
   
   public static final Node copyElement(Node e) throws UnsupportedDataTypeException {
      Node result = null;
      
      switch (e.getNodeType()) {
      case Node.ELEMENT_NODE:
         result = document.createElement(e.getNodeName());
         
         for(int i = 0; i < e.getAttributes().getLength(); i++) {
            Attr attr = document.createAttribute(e.getAttributes().item(i).getNodeName());
            attr.setNodeValue(e.getAttributes().item(i).getNodeValue());
            result.getAttributes().setNamedItem(attr);
         }
         break;

      case Node.CDATA_SECTION_NODE:
         result = document.createCDATASection(((CDATASection)e).getData());
         break;
      case Node.TEXT_NODE:
         if (((Text)e).getTextContent().replaceAll("\t", "").trim() != "")
            result = document.createTextNode(((Text)e).getTextContent().replaceAll("\t", "").trim());
         break;
       
      default:
         throw new UnsupportedDataTypeException(new StringBuilder(e.getNodeType()).toString());
      }
      
      for(int i = 0; i < e.getChildNodes().getLength(); i++)
         result.appendChild(copyElement(e.getChildNodes().item(i)));
      
      return result;
   }
}
