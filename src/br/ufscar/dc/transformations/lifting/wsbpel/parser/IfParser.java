package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.DOCUMENTATION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSEIF;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.IF;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.construction.ConditionalBranch;
import br.ufscar.dc.gwm.node.control.EifNode;
import br.ufscar.dc.gwm.node.control.IfNode;
import br.ufscar.dc.languages.wsbpel.WSBPELLanguage;
import br.ufscar.dc.transformations.lifting.ActivityParser;
import br.ufscar.dc.utils.XMLUtils;

public class IfParser extends ActivityParser<Element, ConditionalBranch> {

   private static final long serialVersionUID = -530622952915061110L;

   public IfParser(Element activity) {

      super(activity);

      if (!activity.getNodeName().equals(IF))
         throw new IllegalArgumentException();
   }

   @Override
   public ConditionalBranch parse() throws UnsupportedDataTypeException {

      /* getting name attribute */
      String name;

      try {
         name = new String(super.activity.getAttributes().getNamedItem("name")
               .getNodeValue());
      } catch (Exception e) {
         name = IF;
      }

      /* creating an instance of ConditionalBranch class */
      ConditionalBranch conditionalBranch = new ConditionalBranch(name);

      /* getting all arbitrary attributes */
      for (int index = 0; index < activity.getAttributes().getLength(); index++)
         if (!activity.getAttributes().item(index).getNodeName().equals("name"))
            conditionalBranch.addAttribute(
                  activity.getAttributes().item(index).getNodeName(), activity
                        .getAttributes().item(index).getNodeValue());

      /* creating an instance of ConditionalStart */
      IfNode startNode = new IfNode(
            name.concat("Start"));

      /* setting condition */
      startNode.setCondition(XMLUtils.getFirstNamedNode(
            super.activity.getChildNodes(), "condition").getTextContent());

      /* creating an instance of ConditionalEnd */
      EifNode endNode = new EifNode(name.concat("End"));

      /* setting data */
      startNode.setParentConstruction(conditionalBranch);
      conditionalBranch.setStartNode(startNode);
      endNode.setParentConstruction(conditionalBranch);
      conditionalBranch.setEndNode(endNode);

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
      conditionalBranch.addBranch(true, trueBranch);

      for(int i = 0; i < activity.getChildNodes().getLength(); i++) {
         Node n = activity.getChildNodes().item(i);
         
         if (n.getNodeName().equals(ELSE) || n.getNodeName().equals(ELSEIF))
            conditionalBranch.addBranch(
               false,
               recursiveFalseCondition(
                  activity.getChildNodes(), i, 
                  activity.getChildNodes().getLength()));
      }

      return conditionalBranch;
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
            ConditionalBranch conditionalBranch = new ElseIfParser(elseif).parse();

            if (startIndex < endIndex)
               conditionalBranch.addBranch(
            		false, recursiveFalseCondition(nodes, ++startIndex,endIndex));

            result.addNode(conditionalBranch);
         }
      }
      return result;
   }
}
