package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WHILE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WORKFLOW_ACTIVITIES;

import java.security.InvalidParameterException;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Edge;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.LoopConditionalNode;
import nl.utwente.eemcs.graph.LoopConstruct;
import nl.utwente.eemcs.graph.Node;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.lifting.ActivityParser;
import br.ufscar.dc.utils.XMLUtils;

public class WhileParser extends ActivityParser<Element, LoopConstruct> {

   private static final long serialVersionUID = -4196984214806911896L;

   public WhileParser(Element activity) {
      super(activity);

      if (!activity.getNodeName().equals(WHILE))
         throw new IllegalArgumentException();
   }

   @Override
   public LoopConstruct parse() throws UnsupportedDataTypeException {
      String name;

      try {
         name = activity.getAttributes().getNamedItem("name").getTextContent();
      } catch (NullPointerException exception) {
         name = WHILE;
      }

      LoopConstruct loopConstruct = new LoopConstruct(name);

      /*
       * Identifying the loop as while activity.
       */
      loopConstruct.setEvaluateConditionBefore(true);

      /*
       * Getting loop condtion
       */
      loopConstruct.setConditionNode(new LoopConditionalNode(name
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
                     innerGraph.addEdge(new Edge(innerGraph.getEndNode(),
                           (Node) nestedActivity, EdgeType.Control));

                  innerGraph.getNodes().add((Node) nestedActivity);

                  if (innerGraph.getStartNode() == null)
                     innerGraph.setStartNode((Node) nestedActivity);

               } else
                  throw new InvalidParameterException();

               loopConstruct.setInnerGraph(innerGraph);
            }
         }
      }
      return loopConstruct;
   }
}
