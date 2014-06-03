package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ASSIGN;

import nl.utwente.eemcs.graph.ActivityNode;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.lifting.ActivityParser;

public class AssignParser extends ActivityParser<Element, ActivityNode> {

   private static final long serialVersionUID = 4075717606220172680L;

   public AssignParser(Element activity) {
      super(activity);

      if (!activity.getTagName().equals(ASSIGN))
         throw new IllegalArgumentException(activity.getTagName());
   }

   @Override
   public ActivityNode parse() {

      String name;

      try {

         name = activity.getAttributeNode("name").getTextContent();
      } catch (Exception exception) {

         name = ASSIGN;
      }

      ActivityNode activityNode = new ActivityNode(name);

      activityNode.addAttribute("type", ASSIGN);

      for (int i = 0; i < activity.getAttributes().getLength(); i++) {
         activityNode.addAttribute(super.activity.getAttributes().item(i)
               .getNodeName(), super.activity.getAttributes().item(i)
               .getTextContent());
      }

      activityNode.addAttribute("children", activity.getChildNodes());

      return activityNode;
   }

}
