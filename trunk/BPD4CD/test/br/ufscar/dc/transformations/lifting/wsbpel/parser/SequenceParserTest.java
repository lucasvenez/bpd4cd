package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.EdgeType;
import nl.utwente.eemcs.graph.Graph;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class SequenceParserTest extends ParserTest {

   /*
    * <sequence name=”sequenceTest”> <receive name=”receiveTest”
    * partnerLink="pacsBusinessProcessPT" operation="initiate"/> <invoke
    * name=”invokeTest” partnerLink="pacsServicesPT" operation="operationTest"/>
    * <reply
    * name=”replyTest" partnerLink="pacsBusinessProcessPT" operation="initiate"
    * /> </sequence>
    */
   private Element sequenceElement;

   public SequenceParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {

      sequenceElement = createElement(SEQUENCE);

      /* configuration of the first nested activity */{
         Element receiveElement = createElement(RECEIVE);

         receiveElement.setAttribute("name", "receiveTest");
         receiveElement.setAttribute("partnerLink", "pacsServicesPT");
         receiveElement.setAttribute("operation", "operationTest");

         sequenceElement.appendChild(receiveElement);
      }

      /* configuration of the second nested activity */{

         Element invokeElement = createElement(INVOKE);

         invokeElement.setAttribute("name", "invokeTest");
         invokeElement.setAttribute("partnerLink", "pacsBusinessProcessPT");
         invokeElement.setAttribute("operation", "initiate");

         sequenceElement.appendChild(invokeElement);
      }

      /* configuration of the third nested activity */{

         Element replyElement = createElement(REPLY);

         replyElement.setAttribute("name", "replyTest");
         replyElement.setAttribute("partnerLink", "pacsBusinessProcessPT");
         replyElement.setAttribute("operation", "initiate");
         
         sequenceElement.appendChild(replyElement);
      }
   }

   @Test
   public void test() throws UnsupportedDataTypeException {

      Graph sequenceGraph = new SequenceParser(this.sequenceElement).parse();

      assertEquals(3, sequenceGraph.getEdges(EdgeType.Control).size());
      
      assertEquals(1, sequenceGraph.getEdges(EdgeType.Communication).size());
      
      assertEquals(0, sequenceGraph.getEdges(EdgeType.Data).size());

      assertEquals(5, sequenceGraph.getNodes().size());

      assertTrue(sequenceGraph.getNodes().get(0) instanceof CommunicatorNode);
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new SequenceParser(createElement(INVOKE));
   }

}
