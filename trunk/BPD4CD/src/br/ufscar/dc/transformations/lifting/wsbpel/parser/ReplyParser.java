package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPLY;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.node.communication.RepNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

/**
 * Parser from WS-BPEL <reply> to CommunicatorNode of the type Response.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 * @see CommunicatorNode, CommunicatorType
 * 
 */
public class ReplyParser extends ActivityParser<Element, RepNode> {

   private static final long serialVersionUID = -8295236815436170046L;

   public ReplyParser(Element activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( REPLY ) )
         throw new IllegalArgumentException();
   }

   @Override
   public RepNode parse() {

      String name;

      try {
         
         name = activity.getAttributes()
                  .getNamedItem("name").getTextContent();
      } 
      catch (NullPointerException exception) {
         
         name = REPLY;
      }

      RepNode replyNode = new RepNode(name);

      for (int i = 0; i < super.activity.getAttributes().getLength(); i++)
         if (!activity.getAttributes().item(i).getNodeName().equals("name"))
            replyNode.addAttribute(
                  activity.getAttributes().item(i).getNodeName(), 
                  activity.getAttributes().item(i).getTextContent());
      
      return replyNode;
   }
}
