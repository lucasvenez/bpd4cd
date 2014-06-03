package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import javax.activation.UnsupportedDataTypeException;

import static br.ufscar.dc.utils.XMLUtils.*;

import org.w3c.dom.Element;

import nl.utwente.eemcs.graph.LoopConstruct;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class LoopConstructGenerator extends ActivityGenerator<LoopConstruct, Element> {

   private static final long serialVersionUID = -3584064560681377289L;

   public LoopConstructGenerator(LoopConstruct activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = activity.isEvaluateConditionBefore() ? 
            createElement(WHILE) : createElement(REPEAT_UNTIL);
            
      Element condition = createElement(CONDITION);
      
      for(String a : activity.getConditionNode().getAttributes().keySet())
         if(activity.getConditionNode().getAttribute(a) instanceof String)
            condition.setAttribute(a, (String)activity.getConditionNode().getAttribute(a));
      
      condition.appendChild(createCDATASection(activity.getConditionNode().getCondition()));
      result.appendChild(condition);
      
      Element iterativeActivity = new GraphGenerator(activity.getInnerGraph()).generate();
      
      if (activity.isEvaluateConditionBefore())
         result.appendChild(iterativeActivity);
      
      else
         result.insertBefore(iterativeActivity, condition);
      
      return result;
   }
}
