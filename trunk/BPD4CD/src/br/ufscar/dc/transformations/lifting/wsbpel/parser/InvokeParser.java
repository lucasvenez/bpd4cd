package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.INVOKE;
import nl.utwente.eemcs.graph.ActivityNode;
import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;
import nl.utwente.eemcs.graph.Edge;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;

import org.w3c.dom.Element;

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
      CommunicatorNode ireq = new CommunicatorNode(name.concat("Ireq"),
            CommunicatorType.InvokeRec);
      ireq.addAttribute("partnerLink", activity.getAttribute("partnerLink"));

      ireq.addAttribute("operation", activity.getAttribute("operation"));
      
      if (activity.hasAttribute("portType"))
         ireq.addAttribute("portType", activity.getAttribute("portType"));
      
      if (activity.hasAttribute("inputVariable"))
            ireq.addAttribute("inputVariable", activity.getAttribute("inputVariable"));
      
      /*
       * Receive node instance
       */
      CommunicatorNode rec =
         new CommunicatorNode(name.concat("Rec"), CommunicatorType.Receive);

      /*
       * Communication edge between ireq and rec nodes.
       */
      Edge requestCommEdge = new Edge(ireq, rec, EdgeType.Communication);

      /*
       * Adding edges and nodes in the Graph.
       */
      invokeGraph.addNode(ireq);
      invokeGraph.setStartNode(ireq);
      invokeGraph.addNode(rec);
      invokeGraph.addEdge(requestCommEdge);

      /*
       * Activity node representing the operation
       */
      ActivityNode activityNode = new ActivityNode(
            activity.getAttribute("operation"));
      
      activityNode.addAttribute("type", "webservice");

      /*
       * Data edge between rec and ireq nodes.
       */
      if (activity.hasAttribute("inputVariable")) {

         Edge requestDataEdge = new Edge(ireq, rec, EdgeType.Data);
         requestDataEdge.setLabel(activity.getAttribute("inputVariable"));

         Edge receiveDataEdge = new Edge(rec, activityNode, EdgeType.Data);
         receiveDataEdge.setLabel(activity.getAttribute("inputVariable"));

         /*
          * Adding data edges in the Graph.
          */
         invokeGraph.addEdge(receiveDataEdge);
         invokeGraph.addEdge(requestDataEdge);
      }

      /*
       * Control edge between rec and activity nodes.
       */
      Edge recActivityControlEdge = new Edge(rec, activityNode,
            EdgeType.Control);

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
         CommunicatorNode rep = new CommunicatorNode(name.concat("Rep"),
               CommunicatorType.Response);

         /*
          * Data edge between activity and rep nodes.
          */
         Edge replyDataEdge = new Edge(activityNode, rep, EdgeType.Data);
         replyDataEdge.setLabel(activity.getAttribute("outputVariable"));

         /*
          * Control edge between activity and rep nodes.
          */
         Edge replyControlEdge = new Edge(activityNode, rep, EdgeType.Control);

         /*
          * Adding control and data edges between activity and rep nodes.
          */
         invokeGraph.addEdge(replyDataEdge);
         invokeGraph.addEdge(replyControlEdge);

         /*
          * Invoke response node instance
          */
         CommunicatorNode ires = new CommunicatorNode(name.concat("Ires"),
               CommunicatorType.InvokeRes);

         /*
          * Communication edge between rep and ires nodes.
          */
         Edge responseCommEdge = new Edge(rep, ires, EdgeType.Communication);

         /*
          * Control edge between ireq and ires nodes.
          */
         Edge iControlEdge = new Edge(ireq, ires, EdgeType.Control);

         /*
          * Data edge between ires and rep nodes.
          */
         Edge responseDataEdge = new Edge(rep, ires, EdgeType.Data);
         
         ires.addAttribute("outputVariable", activity.getAttribute("outputVariable"));
         responseDataEdge.setLabel(activity.getAttribute("outputVariable"));

         invokeGraph.addEdge(iControlEdge);
         invokeGraph.addEdge(responseCommEdge);
         invokeGraph.addEdge(responseDataEdge);
         invokeGraph.addNode(rep);
         invokeGraph.addNode(ires);
      }

      return invokeGraph;
   }
}
