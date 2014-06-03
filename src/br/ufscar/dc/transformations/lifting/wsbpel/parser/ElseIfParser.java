package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSEIF;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.ConditionalConstruct;
import nl.utwente.eemcs.graph.ConditionalEndNode;
import nl.utwente.eemcs.graph.ConditionalStartNode;
import nl.utwente.eemcs.graph.Graph;

import org.w3c.dom.Element;

import br.ufscar.dc.transformations.lifting.ActivityParser;

public class ElseIfParser extends ActivityParser<Element,ConditionalConstruct> {

   private static final long serialVersionUID = 1400776308471683402L;

   public ElseIfParser(Element activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( ELSEIF ) )
         throw new IllegalArgumentException();
   }

   @Override
   public ConditionalConstruct parse() throws UnsupportedDataTypeException {
      
      String name;
      
      try {
         name = activity.getAttributes()
            .getNamedItem("name").getTextContent();
      }
      catch ( Exception exception ) {
         name = ELSEIF;
      }
         
      ConditionalConstruct conditionalConstruct 
         = new ConditionalConstruct( name );

      /* getting all arbitrary attributes */
      for ( int index = 0; index < activity.getAttributes().getLength(); index++ )         
         if ( !activity.getAttributes().item(index).getNodeName().equals( "name" ) )
            conditionalConstruct.addAttribute( 
               activity.getAttributes().item(index).getNodeName(),
               activity.getAttributes().item(index).getNodeValue() );
      
      /* setting startNode */
      ConditionalStartNode startNode = 
            new ConditionalStartNode( name.concat( "StartNode" ) );
      
      /* setting condition */
      startNode.setCondition( 
         activity.getElementsByTagName( CONDITION ).item( 0 ).getTextContent() );
      
      startNode.setParentConstruct( conditionalConstruct );
      
      /* setting branch */
      conditionalConstruct.setBranch(
         (Graph)new Parser( (Element)activity.getLastChild() ).parse(), true );
      
      /* setting endNode */
      ConditionalEndNode endNode =
            new ConditionalEndNode( name.concat( "EndNode" ) );
      
      endNode.setParentConstruct( conditionalConstruct );

      /* setting child */
      conditionalConstruct.setStartNode( startNode );
      conditionalConstruct.setEndNode( endNode );
      
      return conditionalConstruct;
   }
}
