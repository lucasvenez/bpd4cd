package br.ufscar.dc.languages.wsbpel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static br.ufscar.dc.languages.wsbpel.WSBPELLanguage.*;

import br.ufscar.dc.utils.WSBPELSourceCode;

public class WSBPELDocumentTest {

   WSBPELDocument wsbpelDocument;
   
   @Before
   public void setUp() throws Exception {
      WSBPELSourceCode source = new WSBPELSourceCode();
      wsbpelDocument = new WSBPELDocument( source.loadSourceFile( "./examples/pacs/PACSBusinessProcess.bpel" ) );
   }

   @Test
   public void test() {      
      assertTrue( wsbpelDocument.getWorkflow().getNodeName().equals( SEQUENCE ) );
   }

}
