package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.ParallelConstruct;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class FlowParserTest extends ParserTest {

   /**
    * Mock representing the follow WS-BPEL activity: <br/>
    * <code>
    * &lt;flow name="ImageDataFlow"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;invoke name="ImagePersistence" partnerLink="pacsServicesRequester" operation="persistImage" inputVariable="imageVar" outputVariable="imagePersistenceResult" /><br/>
    * &nbsp;&nbsp;&nbsp;&lt;invoke name="ImageDiagnosticPersistence" partnerLink="pacsServicesRequester" operation="persistImageDiagnostic" inputVariable="diagnosticVar" outputVariable="diagnosticPersistenceResult" /><br/>
    * &lt;/flow>
    * </code>
    */
   private Element flowElement;

   public FlowParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {

      flowElement = createElement(FLOW);
      flowElement.setAttribute("name", "ImageDataFlow");

      /* first branch configuration */{

         Element firstBranch = createElement(INVOKE);

         firstBranch.setAttribute("name", "ImagePersistence");
         firstBranch.setAttribute("partnerLink", "pacsServicesRequester");
         firstBranch.setAttribute("operation", "persistImage");
         firstBranch.setAttribute("inputVariable", "imageVar");
         firstBranch.setAttribute("outputVariable", "imagePersistenceResult");

         flowElement.appendChild(firstBranch);
      }

      /* second branch configuration */{

         Element secondBranch = createElement(INVOKE);

         secondBranch.setAttribute("name", "ImageDiagnosticPersistence");
         secondBranch.setAttribute("partnerLink", "pacsServicesRequester");
         secondBranch.setAttribute("operation", "persistImageDiagnostic");
         secondBranch.setAttribute("inputVariable", "diagnosticVar");
         secondBranch.setAttribute("outputVariable",
               "diagnosticPersistenceResult");

         flowElement.appendChild(secondBranch);
      }
   }

   @Test
   public void test() throws UnsupportedDataTypeException {

      ParallelConstruct parallelConstruct = new FlowParser(flowElement).parse();

      assertEquals(2, parallelConstruct.getBranches().size());

      assertEquals("ImagePersistenceIreq",
            parallelConstruct.getBranches().get(0).getStartNode().getName());

      assertEquals("ImageDiagnosticPersistenceIreq", parallelConstruct
            .getBranches().get(1).getStartNode().getName());

      assertTrue(parallelConstruct.getBranches().get(0).getStartNode()
            .hasAttribute("partnerLink"));

      assertTrue(parallelConstruct.getBranches().get(1).getStartNode()
            .hasAttribute("partnerLink"));

      assertNotNull(parallelConstruct
            .getBranches()
            .get(0)
            .getNodeByName(
                  flowElement.getChildNodes().item(0).getAttributes()
                        .getNamedItem("operation").getTextContent()));

      assertNotNull(parallelConstruct
            .getBranches()
            .get(1)
            .getNodeByName(
                  flowElement.getChildNodes().item(1).getAttributes()
                        .getNamedItem("operation").getTextContent()));
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() {
      new FlowParser(createElement(SEQUENCE));

   }

}
