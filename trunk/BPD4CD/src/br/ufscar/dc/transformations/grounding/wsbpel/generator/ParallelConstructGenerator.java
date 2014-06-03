package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FLOW;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.ParallelConstruct;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class ParallelConstructGenerator extends ActivityGenerator<ParallelConstruct, Element> {

   private static final long serialVersionUID = -4677020019736012618L;

   public ParallelConstructGenerator(ParallelConstruct activity) {
      super(activity);
   }

   @Override
   public Element generate() throws DOMException, UnsupportedDataTypeException {
      Element result = createElement(FLOW);
      
      result.setAttribute("name", activity.getName());
      
      for(String key : activity.getAttributes().keySet())
         if (activity.getAttribute(key) instanceof String)
            result.setAttribute(key, (String)activity.getAttribute(key));
      
      for(Graph g : activity.getBranches())
         result.appendChild(new GraphGenerator(g).generate());
      
      return result;
   }
}
