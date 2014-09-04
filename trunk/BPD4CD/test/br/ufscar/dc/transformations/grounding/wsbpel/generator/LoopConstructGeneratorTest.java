package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.LoopConditionalNode;
import nl.utwente.eemcs.graph.LoopConstruct;

import org.junit.Test;
import org.w3c.dom.Element;

import br.ufscar.dc.utils.TestUtils;

public class LoopConstructGeneratorTest {

   @Test
   public void test() {
      
      try {
         LoopConstruct loop = new LoopConstruct(WHILE);
         
         LoopConditionalNode conditionNode = new LoopConditionalNode(WHILE.concat("Condition"));
         conditionNode.setCondition("true()");
         
         loop.setConditionNode(conditionNode);
         
         Graph innerGraph = new Graph();
         
         /* Assign activity configuration */ {
                  
            innerGraph.addNode(TestUtils.createAssignActivity(ASSIGN));
         }
         
         loop.setInnerGraph(innerGraph);
         
         Element result = new LoopGenerator(loop).generate();
         
         assertNotNull(result);
         
         assertEquals(2, result.getChildNodes().getLength());
         assertEquals("true()", result.getElementsByTagName("condition").item(0).getTextContent());
         assertEquals("$i + 1", result.getElementsByTagName("from").item(0).getTextContent());
         assertNotNull(result.getElementsByTagName("assign").item(0));
      } 
      catch (UnsupportedDataTypeException e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }

}
