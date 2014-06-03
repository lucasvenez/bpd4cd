package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.*;

import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.CommunicatorNode;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ReplyParserTest extends ParserTest {

   /**
    * Mock representing the flow WS-BPEL element:
    * <br/>
    * <br/>
    * &lt;reply name="Response" partnerLink="client"
            operation="initiate" variable="output" />
    */
   private Element replyElement;

   public ReplyParserTest() throws ParserConfigurationException {
      super();
   }
   
   @Before
   public void setUp() throws Exception {
      
      replyElement = createElement( REPLY );
      replyElement.setAttribute( "name", "Response" );
      replyElement.setAttribute( "partnerLink","client" );
      replyElement.setAttribute( "operation", "initiate" );
      replyElement.setAttribute( "variable", "output" );
   }

   @Test
   public void test() {
      
      CommunicatorNode replyNode = new ReplyParser( replyElement ).parse();
      
      assertTrue(
         replyNode.getName().equals( "Response" ) );
      
      assertTrue(
         replyNode.getAttribute( "partnerLink").equals( "client" ) );
      
      assertTrue(
         replyNode.getAttribute( "operation" ).equals( "initiate" ) );
      
      assertTrue(
         replyNode.getAttribute( "variable").equals( "output" ) );
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new ReplyParser( createElement( SEQUENCE ) );
   }
}
