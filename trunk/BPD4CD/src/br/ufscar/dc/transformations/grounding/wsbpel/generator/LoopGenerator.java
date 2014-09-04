package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPEAT_UNTIL;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WHILE;
import static br.ufscar.dc.utils.XMLUtils.createCDATASection;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.construction.Loop;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class LoopGenerator extends ActivityGenerator<Loop, Element> {

   private static final long serialVersionUID = -3584064560681377289L;

   public LoopGenerator(Loop activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = activity.isConditionEvaluatedBefore() ? 
            createElement(WHILE) : createElement(REPEAT_UNTIL);
            
      Element condition = createElement(CONDITION);
      
      for(String a : activity.getLoopNode().getKeys())
         if(activity.getLoopNode().getAttribute(a) instanceof String)
            condition.setAttribute(a, (String)activity.getLoopNode().getAttribute(a));
      
      condition.appendChild(createCDATASection(activity.getLoopNode().getCondition()));
      result.appendChild(condition);
      
      Element iterativeActivity = new GraphGenerator(activity.getIterativeBranch()).generate();
      
      if (activity.isConditionEvaluatedBefore())
         result.appendChild(iterativeActivity);
      
      else
         result.insertBefore(iterativeActivity, condition);
      
      return result;
   }
}
