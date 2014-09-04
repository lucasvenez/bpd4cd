package br.ufscar.dc.utils;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import java.util.Arrays;

import static br.ufscar.dc.utils.XMLUtils.createCDATASection;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import nl.utwente.eemcs.graph.ActivityNode;
import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;
import nl.utwente.eemcs.graph.ConditionalBranch;
import nl.utwente.eemcs.graph.EifNode;
import nl.utwente.eemcs.graph.IfNode;
import nl.utwente.eemcs.graph.Edge;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.grounding.wsbpel.generator.ActivityNodeGenerator;

public class TestUtils {

   /**
    * Creates a simple assign activity in intermediate representation to be used in unit tests.
    * @param name - The name of the assign activity.
    * @return An assign activity described as<br/><br/>
    * <code>
    * &lt;assign name="assign"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;copy><br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;from>&lt;![CDATA[$i + 1]]>&lt;/from><br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;to variable="i"/><br/>
    * &nbsp;&nbsp;&nbsp;&lt;/copy><br/>
    * &lt;/assign><br/>
    * </code>
    * in form of the intermediate representation. i.e. ActivityNode
    * 
    * @see {@link ActivityNode}, {@link ActivityNodeGenerator}
    */
   public static final ActivityNode createAssignActivity(String name) {
      ActivityNode assign = new ActivityNode(ASSIGN);
      
      assign.addAttribute("type", ASSIGN);
      
      Element copy = createElement("copy");
      Element from = createElement("from");
      Element to   = createElement("to");
      
      from.appendChild(createCDATASection("$i + 1"));
      to.setAttribute("variable", "i");
      
      copy.appendChild(from);
      copy.appendChild(to);
      
      assign.addAttribute("children", copy);
      
      return assign;
   }
   
   public static final ConditionalBranch createConditionConstruct(String name) {
      ConditionalBranch conditional = new ConditionalBranch(name);
      
      IfNode startNode = new IfNode(name.concat("StartNode"));
      startNode.setCondition("true()");
      
      conditional.setStartNode(startNode);
      conditional.setEndNode(new EifNode(name.concat("EndNode")));
      
      Graph trueBranch = new Graph();
      trueBranch.addNode(createAssignActivity(ASSIGN.concat("1")));
      
      Graph falseBranch = new Graph();
      falseBranch.addNode(createAssignActivity(ASSIGN.concat("2")));
      
      conditional.setBranch( trueBranch,  true);
      conditional.setBranch( falseBranch, false);
      
      return conditional;
   }
   
   public static final Graph createInvokeActivity(
         String name, 
         String partnerLink, 
         String operation, 
         String inputVariable, 
         String outputVariable) {
      
      Graph g = new Graph();
      
      /*
       * <invoke name="AutomaticNoduleDetection" partnerLink="pacsServicesRequester" 
       * operation="automaticNoduleIdentification" inputVariable="automaticNoduleIdentificationVar" 
       * outputVariable="automaticNoduleIdentificationResult" />
       */
      CommunicatorNode invokeRequest = 
         new CommunicatorNode(name.concat("Ireq"), CommunicatorType.InvokeRec);
      invokeRequest.addAttribute("partnerLink",    partnerLink);
      invokeRequest.addAttribute("operation",      operation);
      invokeRequest.addAttribute("inputVariable",  inputVariable);
      invokeRequest.addAttribute("outputVariable", outputVariable);
      
      CommunicatorNode receive = 
         new CommunicatorNode("AutomaticNoduleDetectionRec", CommunicatorType.Receive);
      
      Edge comm1 = new Edge(invokeRequest, receive, EdgeType.Communication);

      Edge data1 = new Edge(invokeRequest, receive, EdgeType.Data);
      data1.setLabel("automaticNoduleIdentificationVar");
      
      ActivityNode operationNode = new ActivityNode(operation);
      
      Edge ctrl1 = new Edge(receive, operationNode, EdgeType.Control);
      
      Edge data2 = new Edge(receive, operationNode, EdgeType.Data);
      data2.setLabel(operation);
      
      CommunicatorNode reply =
         new CommunicatorNode("AutomaticNoduleDetectionRes", CommunicatorType.Response);
      
      Edge ctrl2 = new Edge(operationNode, reply, EdgeType.Control);
      
      Edge data3 = new Edge(operationNode, reply, EdgeType.Data);
      data3.setLabel("automaticNoduleIdentificationResult");
      
      CommunicatorNode invokeResponse =
         new CommunicatorNode("AutomaticNoduleDetectionIrep", CommunicatorType.InvokeRes);
      
      Edge comm2 = new Edge(reply, invokeResponse, EdgeType.Communication);
      
      Edge data4 = new Edge(reply, invokeResponse, EdgeType.Data);
      data4.setLabel("automaticNoduleIdentificationResult");
      
      g.addAllNodes(Arrays.asList(invokeRequest, receive, operationNode, reply, invokeResponse));
      g.addAllEdges(Arrays.asList(comm1, comm2, data1, data2, data3, data4, ctrl1, ctrl2));
      
      return g;
   }

   public static final CommunicatorNode createReceiveActivity(
      String name, 
      String partnerLink,
      String portType,
      String operation,
      String variable,
      String createInstance) {
      
      CommunicatorNode n = 
            new CommunicatorNode(name, CommunicatorType.Receive);
         
         n.addAttribute("partnerLink", partnerLink);
         n.addAttribute("portType", portType);
         n.addAttribute("operation", operation);
         n.addAttribute("variable", variable);
         n.addAttribute("createInstance", createInstance);
         
      return n;
   }

   public static final CommunicatorNode createReplyActivity(
      String name, String partnerLink, String operation, String variable) {
      
      CommunicatorNode n = 
            new CommunicatorNode(name, CommunicatorType.Response);
         
      n.addAttribute("partnerLink", partnerLink);
      n.addAttribute("operation",   operation);
      n.addAttribute("variable",    variable);
      
      return n;
   }
}
