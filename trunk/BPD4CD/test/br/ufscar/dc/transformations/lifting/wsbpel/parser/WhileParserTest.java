package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import static br.ufscar.dc.utils.XMLUtils.*;

import static org.junit.Assert.*;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.parsers.ParserConfigurationException;

import nl.utwente.eemcs.graph.LoopConstruct;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class WhileParserTest extends ParserTest {

   /**
    * Mock representing the follow WSBPEL activity: <br/>
    * <br/>
    * <code>
    * &lt;while name="whileTest"><br/>
    * &nbsp;&nbsp;&nbsp;&lt;condition>&lt;![CDATA[true()]]>&lt;/condition><br/>
    * &nbsp;&nbsp;&nbsp;&lt;invoke name="AsynOperation"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;partnerLink="pacsServicesRequester"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;operation="asynOperation"/><br/>
    * &lt;/while>
    * </code>
    */
   Element whileElement;

   public WhileParserTest() throws ParserConfigurationException {
      super();
   }

   @Before
   public void setUp() throws Exception {

      whileElement = createElement(WHILE);
      whileElement.setAttribute("name", "whileTest");

      /* while condition configuration */{

         Element condition = createElement(CONDITION);
         condition.appendChild(createCDATASection("true()"));

         whileElement.appendChild(condition);
      }

      /* iterative activity configuration */{

         Element invokeActivity = createElement(INVOKE);

         invokeActivity.setAttribute("name", "AsynOperation");
         invokeActivity.setAttribute("partnerLink", "pacsServicesRequester");
         invokeActivity.setAttribute("operation", "asynOperation");

         whileElement.appendChild(invokeActivity);
      }
   }

   @Test
   public void test() throws UnsupportedDataTypeException {

      LoopConstruct loopConstruct = new WhileParser(whileElement).parse();

      assertNotNull(loopConstruct);

      assertEquals(true, loopConstruct.isEvaluateConditionBefore());

      assertEquals("true()", loopConstruct.getConditionNode().getCondition());

      assertEquals(3, loopConstruct.getInnerGraph().getNodes().size());

      assertEquals(2, loopConstruct.getInnerGraph().getEdges().size());
   }

   @Override
   @Test(expected = IllegalArgumentException.class)
   public void expectedIllegalArgumentExceptionTest() throws UnsupportedDataTypeException {
      new WhileParser(createElement(SEQUENCE)).parse();
   }

}
