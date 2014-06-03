package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.RECEIVE;
import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;

import org.w3c.dom.Node;

import br.ufscar.dc.transformations.lifting.ActivityParser;

public class ReceiveParser extends ActivityParser<Node,CommunicatorNode> {

   private static final long serialVersionUID = -4486121154012858631L;

   public ReceiveParser(Node activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( RECEIVE ) )
         throw new IllegalArgumentException();
   }

   @Override
   public CommunicatorNode parse() {
      String name;

      try {
         
         name = activity.getAttributes()
                  .getNamedItem("name").getTextContent();
         
      } 
      catch (NullPointerException exception) {
         
         name = RECEIVE;
      }

      CommunicatorNode receiveNode = new CommunicatorNode(name,
            CommunicatorType.Response);

      for (int i = 0; i < super.activity.getAttributes().getLength(); i++)
         if (!activity.getAttributes().item(i).getNodeName().equals("name"))
            receiveNode.addAttribute(
                  activity.getAttributes().item(i).getNodeName(), 
                  activity.getAttributes().item(i).getTextContent());
      
      return receiveNode;
   }
}
