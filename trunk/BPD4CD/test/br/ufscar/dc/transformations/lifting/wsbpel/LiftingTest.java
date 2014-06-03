package br.ufscar.dc.transformations.lifting.wsbpel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.LoopConstruct;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import br.ufscar.dc.languages.wsbpel.exceptions.WSBPELSyntaxInvalidException;
import br.ufscar.dc.utils.WSBPELSourceCode;

public class LiftingTest {

   private Document document;

   @Before
   public void setUp() throws Exception {

      document = new WSBPELSourceCode()
            .loadSourceFile("./examples/pacs/PACSBusinessProcess.bpel");
   }

   @SuppressWarnings("unchecked")
   @Test
   public void test() {

      Lifting lifting = new Lifting();

      try {
         Graph result = lifting.parse(document);

         assertNotNull(result);
         assertEquals(3,result.getNodes().size());
         assertNotNull(result.getNodeByName("StartRequest"));
         assertNotNull(((LoopConstruct)result.getNodeByName("WhileThereAreImagesDO")).getInnerGraph().getNodeByName("DataManipulation"));
         assertNotNull(result.getNodeByName("Response"));
         assertEquals("PACSBusinessProcess", ((HashMap<String,String>)result.getAttributes().get("processAttributes")).get("name"));
         
      } catch (WSBPELSyntaxInvalidException | UnsupportedDataTypeException e) {
         fail(e.getMessage());
      }

   }

}
