package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FLOW;

import javax.activation.UnsupportedDataTypeException;

import static org.junit.Assert.*;

import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.ParallelConstruct;

import org.junit.Test;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import br.ufscar.dc.utils.TestUtils;

public class ParallelConstructGeneratorTest {

   @Test
   public void test() {
      
      try {
      
         ParallelConstruct par = new ParallelConstruct(FLOW);
         
         Graph b1 = new Graph(), b2 = new Graph();
         
         b1.addNode(TestUtils.createAssignActivity("assign1"));
         b2.addNode(TestUtils.createAssignActivity("assign2"));
         
         par.addBranch(b1);
         par.addBranch(b2);
         
         Element parElement;
      
         parElement = new ParallelConstructGenerator(par).generate();
         
         assertNotNull(parElement);
         assertEquals(2,parElement.getChildNodes().getLength());
      } 
      catch (DOMException | UnsupportedDataTypeException e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }

}
