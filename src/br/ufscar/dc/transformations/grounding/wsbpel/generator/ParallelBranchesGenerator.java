package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FLOW;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.construction.ParallelBranches;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class ParallelBranchesGenerator extends ActivityGenerator<ParallelBranches, Element> {

   private static final long serialVersionUID = -4677020019736012618L;

   public ParallelBranchesGenerator(ParallelBranches activity) {
      super(activity);
   }

   @Override
   public Element generate() throws DOMException, UnsupportedDataTypeException {
      Element result = createElement(FLOW);
      
      result.setAttribute("name", activity.getName());
      
      for(String key : activity.getKeys())
         if (activity.getAttribute(key) instanceof String)
            result.setAttribute(key, (String)activity.getAttribute(key));
      
      for(Graph g : activity.getBranches())
         result.appendChild(new GraphGenerator(g).generate());
      
      return result;
   }
}
