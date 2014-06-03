package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.RECEIVE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPLY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.Graph;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import br.ufscar.dc.utils.TestUtils;

public class CommunicatorNodeGeneratorTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void receiveTest() throws UnsupportedDataTypeException {
     CommunicatorNode n = 
        TestUtils.createReceiveActivity(
           "StartRequest",
           "client",
           "tns:PACSBusinessProcessPT",
           "initiate",
           "input",
           "yes"
           );
      
      Element e = new CommunicatorNodeGenerator(n).generate();
      
      assertNotNull(e);
      
      assertEquals("client",                    e.getAttribute("partnerLink"));
      assertEquals("tns:PACSBusinessProcessPT", e.getAttribute("portType"));
      assertEquals("initiate",                  e.getAttribute("operation"));
      assertEquals("input",                     e.getAttribute("variable"));
      assertEquals("yes",                       e.getAttribute("createInstance"));
      
      assertEquals(RECEIVE, e.getNodeName());
   }
   
   @Test
   public void invokeTest() throws UnsupportedDataTypeException {
      
      Graph g = TestUtils.createInvokeActivity(
            "AutomaticNoduleDetection",
            "pacsServicesRequester",
            "automaticNoduleIdentification",
            "automaticNoduleIdentificationVar",
            "automaticNoduleIdentificationResult");
      
      Element e = 
         new CommunicatorNodeGenerator(
            (CommunicatorNode)g.getStartNode()).generate();
      
      assertNotNull(e);
      
      assertEquals("pacsServicesRequester",               e.getAttribute("partnerLink"));
      assertEquals("automaticNoduleIdentification",       e.getAttribute("operation"));
      assertEquals("automaticNoduleIdentificationVar",    e.getAttribute("inputVariable"));
      assertEquals("automaticNoduleIdentificationResult", e.getAttribute("outputVariable"));
   }
   
   @Test
   public void replyTest() throws UnsupportedDataTypeException {
      CommunicatorNode n = 
         TestUtils.createReplyActivity("Response", "client", "initiate", "output");
            
      Element e = new CommunicatorNodeGenerator(n).generate();
      
      assertNotNull(e);
      
      assertEquals("client",   e.getAttribute("partnerLink"));
      assertEquals("initiate", e.getAttribute("operation"));
      assertEquals("output",   e.getAttribute("variable"));
      
      assertEquals(REPLY, e.getNodeName());
   }
}
