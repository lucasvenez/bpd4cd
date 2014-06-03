package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

/**
 * Tests of the invoke parser.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 * 
 * @see InvokeParser
 *
 */
public class InvokeParserTest extends ParserTest {

   /**
    * Mock representing the follow activity from the WS-BPEL:
    * <br/>
    * <br/>
    * <code>
    * &lt;invoke name="ImagePersistence" partnerLink="pacsServicesRequester" 
    * &nbsp;&nbsp;&nbsp;&nbsp;operation="persistImage" inputVariable="imageVar" 
    * &nbsp;&nbsp;&nbsp;&nbsp;outputVariable="imagePersistenceResult" />
    * </code>
    */
   private Element synchronousInvokeElement;
   
   /**
    * Mock representing the follow activity from the WS-BPEL:
    * <br/>
    * <br/>
    * <code>
    * &lt;invoke name="asynCommunication" partnerLink="services"
    * &nbsp;&nbsp;&nbsp;operation="asynOperation" inputVariable="inputVar"/>
    * </code>
    */
   private Element asynchronousInvokeElement;
   
   public InvokeParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {
      
      /*
       * Synchronous invoke instance
       */
      
      synchronousInvokeElement = createElement( INVOKE );
      
      synchronousInvokeElement.setAttribute( "name",           "ImagePersistence" );
      synchronousInvokeElement.setAttribute( "partnerLink",    "pacsServicesRequester" );
      synchronousInvokeElement.setAttribute( "operation",      "persistImage" );
      synchronousInvokeElement.setAttribute( "inputVariable",  "imageVar" );
      synchronousInvokeElement.setAttribute( "outputVariable", "imagePersistenceResult" );
      
      /*
       * Asynchronous invoke instance
       */
      
      asynchronousInvokeElement = createElement( INVOKE );
      
      asynchronousInvokeElement.setAttribute( "name",          "asynCommunication" );
      asynchronousInvokeElement.setAttribute( "partnerLink",   "services" );
      asynchronousInvokeElement.setAttribute( "operation",     "asynOperation" );
   }

   /**
    * Unit test used to verify the response of the invoke parser of a synchronous request.
    */
   @Test
   public void synchronousCommunicationTest() {
      Graph synchronousInvokeGraph =
            new InvokeParser( synchronousInvokeElement ).parse();
      
      assertNotNull( synchronousInvokeGraph.getNodeByName( "ImagePersistenceIreq" ) );
      assertNotNull( synchronousInvokeGraph.getNodeByName( "ImagePersistenceRec" ) );
      assertNotNull( synchronousInvokeGraph.getNodeByName( "persistImage" ) );
      assertNotNull( synchronousInvokeGraph.getNodeByName( "ImagePersistenceRep" ) );
      assertNotNull( synchronousInvokeGraph.getNodeByName( "ImagePersistenceIres" ) );
      
      assertTrue( synchronousInvokeGraph.getEdges( EdgeType.Data ).size() == 4 );
      assertTrue( synchronousInvokeGraph.getEdges( EdgeType.Communication ).size() == 2 );
      assertTrue( synchronousInvokeGraph.getEdges( EdgeType.Control ).size() == 3 );
   }

   /**
    * Unit test used to verify the response of the invoke parser of an asynchronous request.
    */
   @Test
   public void asynchronousCommunicationTest() {
      Graph asynchronousInvokeGraph =
            new InvokeParser( asynchronousInvokeElement ).parse();
      
      assertNotNull( asynchronousInvokeGraph.getNodeByName( "asynCommunicationIreq" ) );
      assertNotNull( asynchronousInvokeGraph.getNodeByName( "asynCommunicationRec" ) );
      assertNotNull( asynchronousInvokeGraph.getNodeByName( "asynOperation" ) );
      
      assertTrue( asynchronousInvokeGraph.getEdges( EdgeType.Data ).size() == 0 );
      assertTrue( asynchronousInvokeGraph.getEdges( EdgeType.Communication ).size() == 1 );
      assertTrue( asynchronousInvokeGraph.getEdges( EdgeType.Control ).size() == 1 );
   }
   
   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new InvokeParser( createElement( SEQUENCE ) );
   }
}
