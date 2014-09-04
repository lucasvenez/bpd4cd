package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.INVOKE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.RECEIVE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPLY;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.node.communication.CommunicationNode;
import br.ufscar.dc.gwm.node.communication.GetNode;
import br.ufscar.dc.gwm.node.communication.RecNode;
import br.ufscar.dc.gwm.node.communication.RepNode;
import br.ufscar.dc.gwm.node.communication.ReqNode;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class CommunicationNodeGenerator extends
      ActivityGenerator<CommunicationNode, Element> {

   private static final long serialVersionUID = 5125018154654942565L;

   public CommunicationNodeGenerator(CommunicationNode activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = null;
      
      /*
       * Receive and reply activities
       */
      if (activity instanceof RecNode || activity instanceof RepNode) {

         result = activity instanceof RecNode ? createElement(RECEIVE) : createElement(REPLY);
         result.setAttribute("name", activity.getName());
         
         for(String a : activity.getKeys())
            if (activity.getAttribute(a) instanceof String)
               result.setAttribute(a, (String)activity.getAttribute(a));
      }
      
      /*
       * Invoke activity
       */
      else if (activity instanceof ReqNode) {
         result = createElement(INVOKE);
         
         for (String key : activity.getKeys())
            if (activity.getAttribute(key) instanceof String)
               result.setAttribute(key, (String)activity.getAttribute(key));

         if (activity.getParentGraph().getOutgoingControlEdges(activity).size() == 1) {
         
            Node n = activity.getParentGraph()
            		.getOutgoingControlEdges(activity)
            			.toArray(new ControlEdge[0])[0].getEndNode();
            
            if (n instanceof CommunicationNode) 
               if (n instanceof GetNode) {
                  for (String key : n.getKeys())
                     if (n.getAttribute(key) instanceof String)
                        result.setAttribute(key, (String)n.getAttribute(key));
               }
         }
      }
      
      /*
       * Unsupported data type
       */
      else
         throw new UnsupportedDataTypeException(activity.getClass().getName());
      
      return result;
   }
}
