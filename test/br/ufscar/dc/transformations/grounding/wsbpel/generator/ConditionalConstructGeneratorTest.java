package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import br.ufscar.dc.utils.TestUtils;

public class ConditionalConstructGeneratorTest {

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void test() {
      
      try {
         
         Element e = 
            new ConditionalConstructGenerator(
               TestUtils.createConditionConstruct(IF)).generate();
         
         assertNotNull(e);
         
         assertEquals(3,e.getChildNodes().getLength());
         assertEquals("true()", e.getElementsByTagName("condition").item(0).getTextContent());
         assertEquals(1,e.getElementsByTagName("else").item(0).getChildNodes().getLength());
      }
      catch(Exception e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }
}
