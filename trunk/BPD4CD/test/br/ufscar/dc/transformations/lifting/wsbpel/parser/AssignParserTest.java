package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.assertTrue;

import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.ActivityNode;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AssignParserTest extends ParserTest {

   /**
    * A Mock representing the follow WS-BPEL activity:
    * <br/>
    * <br/>
    * &lt;assign name="increment" validate="no"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;copy><br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;from>&lt;![CDATA[$i + 1]]>&lt/from><br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;to variable="i"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;/copy><br/>
    * &lt;/assign>
    */
   Element assignElement;
   
   public AssignParserTest() 
         throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {
      
      assignElement = createElement( ASSIGN );
      assignElement.setAttribute( "name",     "increment" );
      assignElement.setAttribute( "validate", "no" );
      
      Element fromElement = createElement( FROM );
      fromElement.appendChild( createCDATASection( "$i + 1" ) );
      
      Element toElement = createElement( TO );
      toElement.setAttribute( "variable", "i" );
      
      Element copyElement = createElement( COPY );
      
      copyElement.appendChild( fromElement );
      copyElement.appendChild( toElement );
      
      assignElement.appendChild( copyElement );
   }

   @Test
   public void test() {
      
      ActivityNode assignNode = new AssignParser( assignElement ).parse();
      
      assertTrue(
         assignNode.getAttribute( "name" ).equals( "increment" )
         &&
         assignNode.getAttribute( "validate" ).equals( "no" )
         &&
         ((NodeList)assignNode.getAttribute( "children" )).getLength() == 1
         &&
         ((NodeList)assignNode.getAttribute( "children" ) )
            .item( 0 ).getNodeName().equals( "copy" )
      );
      
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new AssignParser( createElement( SEQUENCE ) );
   }
}
