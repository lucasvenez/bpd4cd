package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ASSIGN;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.THROW;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.ActivityNode;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.ufscar.dc.transformations.grounding.ActivityGenerator;
import br.ufscar.dc.utils.XMLUtils;

public class ActivityNodeGenerator extends ActivityGenerator<ActivityNode, Element> {

   private static final long serialVersionUID = -5158916289980479211L;

   public ActivityNodeGenerator(ActivityNode activity) {
      super(activity);
   }
   
   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = null;
      
      if (!activity.hasAttribute("type"))
         throw new UnsupportedDataTypeException(
            String.format(
               "The activity does not have the attribute named \"type\". " +
               "Type: %s. Name: %s.", activity.getClass().getName(), activity.getName()
            ));
   
      String type = activity.getAttribute("type").toString(); 
      activity.getAttributes().remove("type");
      
      switch(type) {
         case "webservice":
            break;
         case THROW:
         case ASSIGN:
            result = createElement(type, activity.getAttributes());
            result.setAttribute("name", activity.getName());
            
            if (type.equals(ASSIGN) && activity.hasAttribute("children")) {
               NodeList nl = (NodeList)activity.getAttribute("children");
               
               for(int i = 0; i < nl.getLength(); i++)
                  result.appendChild(XMLUtils.copyElement(nl.item(i)));
            }
         break;
        
         default: 
            throw new UnsupportedDataTypeException(type);
      }
      
      return result;
   }
}
