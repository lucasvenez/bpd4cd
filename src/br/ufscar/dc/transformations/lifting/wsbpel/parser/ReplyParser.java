package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPLY;

import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.lifting.ActivityParser;

/**
 * Parser from WS-BPEL <reply> to CommunicatorNode of the type Response.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 * @see CommunicatorNode, CommunicatorType
 * 
 */
public class ReplyParser extends ActivityParser<Element, CommunicatorNode> {

   private static final long serialVersionUID = -8295236815436170046L;

   public ReplyParser(Element activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( REPLY ) )
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
         
         name = REPLY;
      }

      CommunicatorNode replyNode = new CommunicatorNode(name,
            CommunicatorType.Response);

      for (int i = 0; i < super.activity.getAttributes().getLength(); i++)
         if (!activity.getAttributes().item(i).getNodeName().equals("name"))
            replyNode.addAttribute(
                  activity.getAttributes().item(i).getNodeName(), 
                  activity.getAttributes().item(i).getTextContent());
      
      return replyNode;
   }
}
