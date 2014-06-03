package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.assertTrue;

import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.ActivityNode;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class ThrowParserTest extends ParserTest {

   /**
    * Mock representing the flow WS-BPEL activity:
    * <br/>
    * <br/>
    * <code>
    * &lt;throw faultName="Exception"/>
    * </code>
    */
   private Element throwElement;
   
   public ThrowParserTest() throws ParserConfigurationException {
      super();
   }
   
   @Before
   public void setUp() throws Exception {
      
      throwElement = createElement( THROW );
      throwElement.setAttribute( "faultName", "Exception" );
   }

   @Test
   public void test() {
      
      ActivityNode throwNode = new ThrowParser( throwElement ).parse();
      
      assertTrue( throwNode.getAttribute( "faultName" ).equals( "Exception" ) );
      assertTrue( throwNode.getName().equals( THROW ) );
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new ThrowParser( createElement( SEQUENCE ) );
      
   }
}
