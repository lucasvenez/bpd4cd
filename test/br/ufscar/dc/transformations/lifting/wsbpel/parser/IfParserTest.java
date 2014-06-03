package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.ActivityNode;
import nl.utwente.eemcs.graph.ConditionalConstruct;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * Unit test for the IfParser class.
 * <br/>
 * <br/>
 * TODO We need to create a test with if.. elseif.. elseif.. else activities.
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 08/11/2012
 * @see IfParser
 */
public class IfParserTest extends ParserTest {

   /**
    * Mock representing the follow WS-BPEL activity:
    * <br/>
    * <br/>
    * <code>
    * &lt;if name="persistImageCondition"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;condition>true()&lt;/condition><br/>
    * &nbsp;&nbsp;&nbsp;&lt;throw faultName="Exception"/><br/>
    * &lt;/if>
    * </code>
    */
   private Element ifNode;
   
   /**
    * Mock representing the follow WS-BPEL activity:
    * <br/>
    * <br/>
    * <code>
    * &lt;if name="persistImageCondition"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;condition>true()&lt;/condition><br/>
    * &nbsp;&nbsp;&nbsp;&lt;throw faultName="Exception"/><br/>
    * &nbsp;&nbsp;&nbsp;&lt;else><br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;throw faultName="Exception"/><br/>
    * &nbsp;&nbsp;&nbsp;&lt;/else><br/>
    * &lt;/if>
    * </code>
    */
   private Element ifElseNode;

   public IfParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {
      
      /*
       * Setting if activity to test
       */
      
      ifNode = createElement( IF );
      ifNode.setAttribute( "name", "persistImageCondition" );
      
      Element throwNode = createElement( THROW );
      throwNode.setAttribute( "faultName", "Exception" );
            
      Element condition =  createElement( CONDITION );
      condition.appendChild( createCDATASection("true()") );
      
      ifNode.appendChild( condition );
      ifNode.appendChild( throwNode );
      
      /*
       * Setting if else activity to test
       */
      ifElseNode = (Element)ifNode.cloneNode(true);
      
      Element elseElement = createElement( ELSE );
      elseElement.appendChild( throwNode.cloneNode(true) );
      
      ifElseNode.appendChild( elseElement );
   }

   @Test
   public void ifActivityTest() throws UnsupportedDataTypeException {
      
      ConditionalConstruct conditionalConstruct =
         new IfParser( ifNode ).parse();
      
      assertTrue(conditionalConstruct.getName().equals( "persistImageCondition" ) );
      assertEquals(1, conditionalConstruct.getBranch( true ).getNodes().size());
      assertNull( conditionalConstruct.getBranch( false ) );
      
      assertEquals(
            ActivityNode.class, 
            conditionalConstruct.getBranch( true ).getNodeByName( THROW ).getClass());
   }
   
   @Test
   public void ifElseActivityTest() throws UnsupportedDataTypeException {
      ConditionalConstruct conditionalConstruct =
            new IfParser( ifElseNode ).parse();
      
      assertTrue(conditionalConstruct.getName().equals( "persistImageCondition" ));
      
      assertEquals(1, conditionalConstruct.getBranch( true ).getNodes().size());
      
      assertEquals(ActivityNode.class,
         conditionalConstruct.getBranch( true ).getNodeByName( THROW ).getClass());                            

      assertNotNull(conditionalConstruct.getBranch( false ));
      
      assertEquals(1, conditionalConstruct.getBranch( false ).getNodes().size());

      assertEquals(ActivityNode.class,
         conditionalConstruct.getBranch( false ).getNodeByName( THROW ).getClass());
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new IfParser( createElement( SEQUENCE ) );
   }
}
