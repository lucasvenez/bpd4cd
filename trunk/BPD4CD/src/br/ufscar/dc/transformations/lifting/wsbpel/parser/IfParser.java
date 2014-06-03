package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.DOCUMENTATION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSEIF;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.IF;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.ConditionalConstruct;
import nl.utwente.eemcs.graph.ConditionalEndNode;
import nl.utwente.eemcs.graph.ConditionalStartNode;
import nl.utwente.eemcs.graph.Graph;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufscar.dc.languages.wsbpel.WSBPELLanguage;
import br.ufscar.dc.transformations.lifting.ActivityParser;
import br.ufscar.dc.utils.XMLUtils;

public class IfParser extends ActivityParser<Element, ConditionalConstruct> {

   private static final long serialVersionUID = -530622952915061110L;

   public IfParser(Element activity) {

      super(activity);

      if (!activity.getNodeName().equals(IF))
         throw new IllegalArgumentException();
   }

   @Override
   public ConditionalConstruct parse() throws UnsupportedDataTypeException {

      /* getting name attribute */
      String name;

      try {
         name = new String(super.activity.getAttributes().getNamedItem("name")
               .getNodeValue());
      } catch (Exception e) {
         name = IF;
      }

      /* creating an instance of ConditionalConstruct class */
      ConditionalConstruct conditionalConstruct = new ConditionalConstruct(name);

      /* getting all arbitrary attributes */
      for (int index = 0; index < activity.getAttributes().getLength(); index++)
         if (!activity.getAttributes().item(index).getNodeName().equals("name"))
            conditionalConstruct.addAttribute(
                  activity.getAttributes().item(index).getNodeName(), activity
                        .getAttributes().item(index).getNodeValue());

      /* creating an instance of ConditionalStart */
      ConditionalStartNode startNode = new ConditionalStartNode(
            name.concat("Start"));

      /* setting condition */
      startNode.setCondition(XMLUtils.getFirstNamedNode(
            super.activity.getChildNodes(), "condition").getTextContent());

      /* creating an instance of ConditionalEnd */
      ConditionalEndNode endNode = new ConditionalEndNode(name.concat("End"));

      /* setting data */
      startNode.setParentConstruct(conditionalConstruct);
      conditionalConstruct.setStartNode(startNode);
      endNode.setParentConstruct(conditionalConstruct);
      conditionalConstruct.setEndNode(endNode);

      Graph trueBranch = null;

      boolean lastIsCondition = false;
      for (int i = 0; i < activity.getChildNodes().getLength(); i++) {

         if (activity.getChildNodes().item(i).getNodeName().equals("condition"))
            lastIsCondition = true;

         else if (lastIsCondition
               && activity.getChildNodes().item(i).getNodeType() == Element.ELEMENT_NODE
               && !activity.getChildNodes().item(i).getNodeName()
                     .equals("else")
               && !activity.getChildNodes().item(i).getNodeName()
                     .equals("elseif")) {

            trueBranch = (Graph) new Parser((Element) activity.getChildNodes()
                  .item(i)).parse();
            break;
         }
      }

      /* if true activity */
      conditionalConstruct.setBranch(trueBranch, true);

      for(int i = 0; i < activity.getChildNodes().getLength(); i++) {
         Node n = activity.getChildNodes().item(i);
         
         if (n.getNodeName().equals(ELSE) || n.getNodeName().equals(ELSEIF))
            conditionalConstruct.setBranch(
               recursiveFalseCondition(
                  activity.getChildNodes(), i, 
                  activity.getChildNodes().getLength()),
               false);
      }

      return conditionalConstruct;
   }

   private Graph recursiveFalseCondition(NodeList nodes, int startIndex, int endIndex) 
         throws IllegalArgumentException, UnsupportedDataTypeException {

      Graph result = null;
      
      Node n = nodes.item(startIndex);
      
      /*
       * Excluding documentation element and text nodes from the analysis
       */
      if ((n.getNodeType() != Node.ELEMENT_NODE || 
            n.getNodeName().equals(DOCUMENTATION)) &&
            startIndex < endIndex)
         recursiveFalseCondition(nodes, ++startIndex, endIndex);
      
      else if (!n.getNodeName().equals(DOCUMENTATION) && 
            n.getNodeType() == Node.ELEMENT_NODE && startIndex <= endIndex) {
         
         Element e = (Element)n;
         
         if (e.getNodeName().equals(ELSE))
            for(int k = 0; k < e.getChildNodes().getLength(); k++)
               if (WSBPELLanguage.ACTIVITIES.contains(e.getChildNodes().item(k).getNodeName()))
                  result = (Graph) new Parser((Element)e.getChildNodes().item(k)).parse();
   
         else if (e.getNodeName().equals(ELSEIF)) {
            result = new Graph();
            Element elseif = (Element) nodes.item(startIndex);
            ConditionalConstruct conditionalConstruct = new ElseIfParser(elseif).parse();

            if (startIndex < endIndex)
               conditionalConstruct.setBranch(
                  recursiveFalseCondition(
                     nodes, ++startIndex,endIndex), false);

            result.addNode(conditionalConstruct);
         }
      }
      return result;
   }
}
