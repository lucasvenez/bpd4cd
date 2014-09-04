package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.SEQUENCE;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class GraphGenerator extends ActivityGenerator<Graph, Element> {

   private static final long serialVersionUID = -7109402906369168692L;

   public GraphGenerator(Graph activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      
      Element result = null;
      
      if (activity.getNodesInWorkflowSequence().size() == 1)
         result = new Generator(activity.getNodesInWorkflowSequence().get(0)).generate();
      
      else if (activity.getNodesInWorkflowSequence().size() > 1) {
         
    	 result = createElement(SEQUENCE);
         
         for(Node n : activity.getNodesInWorkflowSequence()) {
            
            Element e = new Generator(n).generate();
            
            if (e != null)
               result.appendChild(e);
         }
      }
      
      return result;
   }

}
