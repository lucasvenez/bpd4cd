package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FLOW;

import java.security.InvalidParameterException;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.construction.ParallelBranches;
import br.ufscar.dc.gwm.node.control.EparNode;
import br.ufscar.dc.gwm.node.control.ParNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class FlowParser extends ActivityParser<Element, ParallelBranches> {

   private static final long serialVersionUID = -2615030548705400252L;

   public FlowParser(Element activity) {
      super(activity);

      if (!activity.getNodeName().equals(FLOW))
         throw new IllegalArgumentException();
   }

   @Override
   public ParallelBranches parse() throws UnsupportedDataTypeException {

      /*
       * Getting WSBPEL activity name
       */
      String name;

      try {
         name = activity.getAttributes().getNamedItem("name").getTextContent();
      } catch (Exception exception) {
         name = FLOW;
      }

      ParallelBranches parallelConstruct = new ParallelBranches(name);

      for (int i = 0; i < activity.getAttributes().getLength(); i++)
         if (!activity.getAttributes().item(i).getTextContent().equals("name"))
            parallelConstruct.addAttribute(activity.getAttributes().item(i)
                  .getNodeName(), activity.getAttributes().item(i)
                  .getTextContent());

      parallelConstruct.setStartNode(new ParNode(name
            .concat("FlowStart")));

      parallelConstruct.setEndNode(new EparNode(name
            .concat("FlowEndNode")));

      /*
       * Getting nested activities.
       */
      for (int i = 0; i < activity.getChildNodes().getLength(); i++) {
         if (activity.getChildNodes().item(i).getNodeType() == Element.ELEMENT_NODE) {
            Object branch = new Parser((Element) activity.getChildNodes().item(
                  i)).parse();

            if (branch instanceof Graph)
               parallelConstruct.addBranch((Graph) branch);

            else if (branch instanceof Node) {
               Graph branchGraph = new Graph();
               branchGraph.addNode((Node) branch);
               branchGraph.setStartNode((Node) branch);
               parallelConstruct.addBranch(branchGraph);
            }

            else
               throw new InvalidParameterException();
         }
      }

      return parallelConstruct;
   }
}
