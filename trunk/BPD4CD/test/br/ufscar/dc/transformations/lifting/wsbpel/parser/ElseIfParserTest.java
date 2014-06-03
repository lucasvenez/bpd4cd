package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.ConditionalConstruct;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * 
 * Tests for else if parser.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 *
 */
public class ElseIfParserTest extends ParserTest {

   /**
    * Mock representing the follow WS-BPEL activity:
    * 
    * <code>
    * &lt;elseif><br/>
    * &nbsp;&nbsp;&nbsp;&lt;condition>&lt;![CDATA[true()]]>&lt;/condition><br/>
    * &nbsp;&nbsp;&nbsp;&lt;invoke name="invokeTest" partnerLink="linkTest" operation="test"/><br/>
    * &lt;/elseif>
    * 
    * </code>
    */
   private Element elseIfElement;
   
   public ElseIfParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {
      elseIfElement = createElement( ELSEIF );
      elseIfElement.setAttribute( "name", "elseIfTest" );
      
      Element condition = createElement( CONDITION );
      condition.appendChild( createCDATASection( "true()" ) );
      
      Element activity = createElement( INVOKE );
      activity.setAttribute( "name", "invokeTest" );
      activity.setAttribute( "partnerLink", "linkTest" );
      activity.setAttribute( "operation", "test" );
      
      elseIfElement.appendChild( condition );
      elseIfElement.appendChild( activity );
   }

   @Test
   public void test() throws UnsupportedDataTypeException {
      
      ConditionalConstruct elseIfConstruct = new ElseIfParser( elseIfElement ).parse();
      
      assertNull( elseIfConstruct.getBranch( false ) );
      
      assertNotNull( elseIfConstruct.getBranch( true ) );
      
      assertTrue( elseIfConstruct.getStartNode().getCondition().equals( "true()" ) );
      
      assertTrue( elseIfConstruct.getBranch( true ).getStartNode().getName().equals( "invokeTestIreq" ) );
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new ElseIfParser( createElement( SEQUENCE ) );
   }
}
