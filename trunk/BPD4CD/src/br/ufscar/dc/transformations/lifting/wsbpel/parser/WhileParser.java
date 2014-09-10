package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WHILE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WORKFLOW_ACTIVITIES;

import java.security.InvalidParameterException;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.construction.Loop;
import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.node.control.LoopNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;
import br.ufscar.dc.utils.XMLUtils;

public class WhileParser extends ActivityParser<Element, Loop> {

   private static final long serialVersionUID = -4196984214806911896L;

   public WhileParser(Element activity) {
      super(activity);

      if (!activity.getNodeName().equals(WHILE))
         throw new IllegalArgumentException();
   }

   @Override
   public Loop parse() throws UnsupportedDataTypeException {
      String name;

      try {
         name = activity.getAttributes().getNamedItem("name").getTextContent();
      } catch (NullPointerException exception) {
         name = WHILE;
      }

      Loop loopConstruct = new Loop(name);

      /*
       * Identifying the loop as while activity.
       */
      loopConstruct.setConditionEvaluatedBefore(true);

      /*
       * Getting loop condtion
       */
      loopConstruct.setNode(new LoopNode(name
            .concat("Condition"), XMLUtils.getFirstNamedNode(
            activity.getChildNodes(), CONDITION).getTextContent()));

      /*
       * Getting nested activities
       */
      for (int i = 0; i < activity.getChildNodes().getLength(); i++) {
         Graph innerGraph = new Graph();

         if (WORKFLOW_ACTIVITIES.contains(activity.getChildNodes().item(i)
               .getNodeName())) {
            if (activity.getChildNodes().item(i).getNodeType() == Element.ELEMENT_NODE) {
               Object nestedActivity = new Parser((Element) activity
                     .getChildNodes().item(i)).parse();

               if (nestedActivity instanceof Graph)
                  innerGraph.merge((Graph) nestedActivity);
               
               else if (nestedActivity instanceof Node) {

                  if (!innerGraph.getNodes().isEmpty())
                     innerGraph.addEdge(new ControlEdge(innerGraph.getEndNode(),
                           (Node) nestedActivity));

                  innerGraph.getNodes().add((Node) nestedActivity);

                  if (innerGraph.getStartNode() == null)
                     innerGraph.setStartNode((Node) nestedActivity);

               } else
                  throw new InvalidParameterException();

               loopConstruct.setIterativeBranch(innerGraph);
            }
         }
      }
      return loopConstruct;
   }
}
