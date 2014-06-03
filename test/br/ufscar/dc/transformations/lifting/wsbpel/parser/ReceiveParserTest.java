package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static org.junit.Assert.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.CommunicatorNode;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ReceiveParserTest extends ParserTest {

   /**
    * Mock representing the follow WS-BPEL activity:
    * <br/>
    * <br/>
    * <code>
    * &lt;receive name="StartRequest"
    * partnerLink="client" portType="tns:PACSBusinessProcess" operation="initiate"
    *       variable="input" createInstance="yes" />
    * </code>
    */
   private Element receiveElement;
   
   
   public ReceiveParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {
      
      receiveElement = createElement( RECEIVE );
      
      receiveElement.setAttribute( "name", "StartRequest" );
      receiveElement.setAttribute( "partnerLink", "client" );
      receiveElement.setAttribute( "portType", "tns:PACSBusinessProcess" );
      receiveElement.setAttribute( "operation", "initiate" );
      receiveElement.setAttribute( "variable", "input" );
   }

   @Test
   public void test() {
      CommunicatorNode receiveNode = new ReceiveParser( receiveElement ).parse();
      
      assertTrue(
         receiveNode.getName().equals( "StartRequest" ) );
      
      assertTrue( 
         receiveNode.getAttribute( "partnerLink" ).equals( "client" ) );
      
      assertTrue( 
         receiveNode.getAttribute( "portType" ).equals( "tns:PACSBusinessProcess" ) );
      
      assertTrue( 
         receiveNode.getAttribute( "operation" ).equals( "initiate" ) );
      
      assertTrue( 
         receiveNode.getAttribute( "variable" ).equals( "input" ) );
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new ReceiveParser( createElement( SEQUENCE ) );
   }

}
