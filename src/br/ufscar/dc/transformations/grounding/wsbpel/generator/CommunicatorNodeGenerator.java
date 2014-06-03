package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Node;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class CommunicatorNodeGenerator extends
      ActivityGenerator<CommunicatorNode, Element> {

   private static final long serialVersionUID = 5125018154654942565L;

   public CommunicatorNodeGenerator(CommunicatorNode activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = null;
      
      /*
       * Receive and reply activities
       */
      if (activity.getType().equals(CommunicatorType.Receive) || activity.getType().equals(CommunicatorType.Response)) {

         result = activity.getType().equals(CommunicatorType.Receive) ? createElement(RECEIVE) : createElement(REPLY);
         result.setAttribute("name", activity.getName());
         
         for(String a : activity.getAttributes().keySet())
            if (activity.getAttribute(a) instanceof String)
               result.setAttribute(a, (String)activity.getAttribute(a));
      }
      
      /*
       * Invoke activity
       */
      else if (activity.getType().equals(CommunicatorType.InvokeRec)) {
         result = createElement(INVOKE);
         
         for (String key : activity.getAttributes().keySet())
            if (activity.getAttribute(key) instanceof String)
               result.setAttribute(key, (String)activity.getAttribute(key));

         if (
         activity
          .getParentGraph()
          .getOutgoingEdges(
             activity, 
             EdgeType.Control)
          .size() == 1) {
         
            Node n = activity
                  .getParentGraph()
                  .getOutgoingEdges(
                     activity, 
                     EdgeType.Control).get(0).getEnd();
            
            if (n instanceof CommunicatorNode) 
               if (((CommunicatorNode)n).getType().equals(CommunicatorType.InvokeRes)) {
                  for (String key : n.getAttributes().keySet())
                     if (n.getAttribute(key) instanceof String)
                        result.setAttribute(key, (String)n.getAttribute(key));
               }
         }
      }
      
      /*
       * Unsupported data type
       */
      else
         throw new UnsupportedDataTypeException(activity.getType().toString());
      
      return result;
   }
}
