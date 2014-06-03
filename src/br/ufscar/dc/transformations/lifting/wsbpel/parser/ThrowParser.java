package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.THROW;

import nl.utwente.eemcs.graph.ActivityNode;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.lifting.ActivityParser;

/**
 * This class converts a Throw Activity from WS-BPEL described in Element format
 * to a ActivityNode.
 *  
 * TODO generate a especification of ActivityNode to Throw WB-PEL.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 08/11/2012
 *
 */
public class ThrowParser extends ActivityParser<Element, ActivityNode> {

   private static final long serialVersionUID = 3009037285424109600L;

   public ThrowParser(Element activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( THROW ) )
         throw new IllegalArgumentException();
   }

   @Override
   public ActivityNode parse() {
      
      String name; 
      
      try {
         name = activity.getAttributes().getNamedItem( "name" ).getTextContent();
      }
      catch( Exception exception ) {
         name = THROW;
      }
      
      
      ActivityNode activityNode = new ActivityNode( name );

      activityNode.addAttribute("type", THROW);
      
      /* setting arbitrary attributes */
      for (  int index = 0; index < activity.getAttributes().getLength(); index++ )
         
         if ( !activity.getAttributes().item(index).getTextContent().equals( "name" ) )
            
            activityNode.addAttribute( 
               activity.getAttributes().item(index).getNodeName(),
               activity.getAttributes().item(index).getNodeValue()
            );
      
      return activityNode;
   }
}
