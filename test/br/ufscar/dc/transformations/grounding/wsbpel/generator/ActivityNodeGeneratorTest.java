package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ASSIGN;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.THROW;
import static br.ufscar.dc.utils.XMLUtils.createCDATASection;
import static br.ufscar.dc.utils.XMLUtils.createElement;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.ActivityNode;

import org.junit.Test;
import org.w3c.dom.Element;

public class ActivityNodeGeneratorTest {

   @Test
   public void throwActivity() {
      ActivityNode node = new ActivityNode(THROW);
      node.addAttribute("type", THROW);
      node.addAttribute("faultName", "Exception");
      
      try {
         Element element = new ActivityNodeGenerator(node).generate();

         assertNotNull(element);
         assertEquals("Exception", element.getAttribute("faultName"));
         assertEquals(THROW, element.getNodeName());
         assertEquals(THROW, element.getAttribute("name"));
      } 
      catch (UnsupportedDataTypeException e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }
   
   @Test
   public void assignActivity() {
      ActivityNode node = new ActivityNode(ASSIGN);
      node.addAttribute("type", ASSIGN);
      
      Element copy = createElement("copy");
      Element from = createElement("from");
      Element to   = createElement("to");
      
      from.appendChild(createCDATASection("$i + 1"));
      to.setAttribute("variable", "i");
      
      copy.appendChild(from);
      copy.appendChild(to);
      
      Element tmp = createElement("TMP");
      tmp.appendChild(copy);
      
      node.addAttribute("children", tmp.getChildNodes());
      
      try {
         Element element = new ActivityNodeGenerator(node).generate();
         
         assertNotNull(element);
         assertEquals(ASSIGN, element.getAttribute("name"));
         assertEquals(1, element.getChildNodes().getLength());
         assertEquals("i", 
               element.getChildNodes()
               .item(0).getChildNodes()
               .item(1).getAttributes()
               .getNamedItem("variable").getTextContent());
      } 
      catch (UnsupportedDataTypeException e) {
         e.printStackTrace();
         fail(e.getMessage());
      } 
   }
}
