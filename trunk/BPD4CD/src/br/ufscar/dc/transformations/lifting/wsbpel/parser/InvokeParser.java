package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.INVOKE;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.DataItem;
import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.edge.CommunicationEdge;
import br.ufscar.dc.gwm.edge.ControlEdge;
import br.ufscar.dc.gwm.edge.DataEdge;
import br.ufscar.dc.gwm.node.ActivityNode;
import br.ufscar.dc.gwm.node.communication.GetNode;
import br.ufscar.dc.gwm.node.communication.RecNode;
import br.ufscar.dc.gwm.node.communication.RepNode;
import br.ufscar.dc.gwm.node.communication.ReqNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class InvokeParser extends ActivityParser<Element, Graph> {

   private static final long serialVersionUID = -9202754953260737222L;

   public InvokeParser(Element activity) {

      super(activity);

      if (!activity.getNodeName().equals(INVOKE))
         throw new IllegalArgumentException();
   }

   @Override
   public Graph parse() {

      /*
       * Getting node name.
       */
      String name;

      try {

         name = activity.getAttributes().getNamedItem("name").getTextContent();
      } catch (NullPointerException exception) {

         name = activity.getAttributes().getNamedItem("operation")
               .getTextContent().concat(INVOKE);
      }

      /*
       * Graph instance.
       */
      Graph invokeGraph = new Graph();

      /*
       * Invoke request node instance
       */
      ReqNode req = new ReqNode(name.concat("Req"));
      req.addAttribute("partnerLink", activity.getAttribute("partnerLink"));

      req.addAttribute("operation", activity.getAttribute("operation"));
      
      if (activity.hasAttribute("portType"))
         req.addAttribute("portType", activity.getAttribute("portType"));
      
      if (activity.hasAttribute("inputVariable"))
            req.addAttribute("inputVariable", activity.getAttribute("inputVariable"));
      
      /*
       * Receive node instance
       */
      RecNode rec = new RecNode(name.concat("Rec"));

      /*
       * Communication edge between req and rec nodes.
       */
      CommunicationEdge requestCommEdge = new CommunicationEdge(req, rec);

      /*
       * Adding edges and nodes in the Graph.
       */
      invokeGraph.addNode(req);
      invokeGraph.setStartNode(req);
      invokeGraph.addNode(rec);
      invokeGraph.addEdge(requestCommEdge);

      /*
       * Activity node representing the operation
       */
      ActivityNode activityNode = new ActivityNode(
            activity.getAttribute("operation"));
      
      activityNode.addAttribute("type", "webservice");

      /*
       * Data edge between rec and req nodes.
       */
      if (activity.hasAttribute("inputVariable")) {

         DataEdge requestDataEdge = new DataEdge(req, rec);
         requestDataEdge.addData(new DataItem(activity.getAttribute("inputVariable")));

         DataEdge receiveDataEdge = new DataEdge(rec, activityNode);
         receiveDataEdge.addData(new DataItem(activity.getAttribute("inputVariable")));

         /*
          * Adding data edges in the Graph.
          */
         invokeGraph.addEdge(receiveDataEdge);
         invokeGraph.addEdge(requestDataEdge);
      }

      /*
       * Control edge between rec and activity nodes.
       */
      ControlEdge recActivityControlEdge = new ControlEdge(rec, activityNode);

      /*
       * Adding control edge and activity node in the Graph.
       */
      invokeGraph.addNode(activityNode);
      invokeGraph.addEdge(recActivityControlEdge);

      /*
       * Response for synchronous communication
       */
      if (activity.hasAttribute("outputVariable")) {

         /*
          * Response node instance
          */
         RepNode rep = new RepNode(name.concat("Rep"));

         /*
          * Data edge between activity and rep nodes.
          */
         DataEdge replyDataEdge = new DataEdge(activityNode, rep);
         replyDataEdge.addData(new DataItem(activity.getAttribute("outputVariable")));

         /*
          * Control edge between activity and rep nodes.
          */
         ControlEdge replyControlEdge = new ControlEdge(activityNode, rep);
         
         /*
          * Adding control and data edges between activity and rep nodes.
          */
         invokeGraph.addEdge(replyDataEdge);
         invokeGraph.addEdge(replyControlEdge);

         /*
          * Invoke response node instance
          */
         GetNode ires = new GetNode(name.concat("Ires"));

         /*
          * Communication edge between rep and ires nodes.
          */
         CommunicationEdge responseCommEdge = new CommunicationEdge(rep, ires);

         /*
          * Control edge between req and ires nodes.
          */
         ControlEdge iControlEdge = new ControlEdge(req, ires);

         /*
          * Data edge between ires and rep nodes.
          */
         DataEdge responseDataEdge = new DataEdge(rep, ires);
         
         ires.addAttribute("outputVariable", activity.getAttribute("outputVariable"));
         responseDataEdge.addData(new DataItem(activity.getAttribute("outputVariable")));

         invokeGraph.addEdge(iControlEdge);
         invokeGraph.addEdge(responseCommEdge);
         invokeGraph.addEdge(responseDataEdge);
         invokeGraph.addNode(rep);
         invokeGraph.addNode(ires);
      }

      return invokeGraph;
   }
}
