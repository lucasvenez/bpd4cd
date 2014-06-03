package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.SEQUENCE;

import java.security.InvalidParameterException;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Edge;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;

import org.w3c.dom.Element;

import nl.utwente.eemcs.graph.Node;

import br.ufscar.dc.transformations.lifting.ActivityParser;

public class SequenceParser extends ActivityParser<Element, Graph> {

   private static final long serialVersionUID = 7063486666342988934L;

   public SequenceParser(Element activity) {
      super(activity);

      if (!activity.getNodeName().equals(SEQUENCE))
         throw new IllegalArgumentException(activity.toString());
   }

   @Override
   public Graph parse() throws UnsupportedDataTypeException {

      Graph sequenceGraph = new Graph();

      for (int i = 0; i < activity.getChildNodes().getLength(); i++) {
         if (activity.getChildNodes().item(i).getNodeType() == Element.ELEMENT_NODE) {
            Element child = (Element) activity.getChildNodes().item(i);

            Object nestedActivity = new Parser(child).parse();

            if (nestedActivity instanceof Graph)
               sequenceGraph.merge((Graph) nestedActivity);

            else if (nestedActivity instanceof Node) {
               Node nestedNode = (Node) nestedActivity;

               if (!sequenceGraph.getNodes().isEmpty())
                  sequenceGraph.addEdge(new Edge(sequenceGraph.getEndNode(),
                        nestedNode, EdgeType.Control));

               sequenceGraph.addNode(nestedNode);

               if (sequenceGraph.getStartNode() == null)
                  sequenceGraph.setStartNode(nestedNode);

            } else
               throw new InvalidParameterException();
         }
      }

      return sequenceGraph;
   }
}
