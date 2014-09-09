package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSEIF;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.construction.ConditionalBranch;
import br.ufscar.dc.gwm.node.control.EifNode;
import br.ufscar.dc.gwm.node.control.IfNode;
import br.ufscar.dc.transformations.lifting.ActivityParser;

public class ElseIfParser extends ActivityParser<Element,ConditionalBranch> {

   private static final long serialVersionUID = 1400776308471683402L;

   public ElseIfParser(Element activity) {
      super(activity);
      
      if ( !activity.getNodeName().equals( ELSEIF ) )
         throw new IllegalArgumentException();
   }

   @Override
   public ConditionalBranch parse() throws UnsupportedDataTypeException {
      
      String name;
      
      try {
         name = activity.getAttributes()
            .getNamedItem("name").getTextContent();
      }
      catch ( Exception exception ) {
         name = ELSEIF;
      }
         
      ConditionalBranch conditionalBranch 
         = new ConditionalBranch( name );

      /* getting all arbitrary attributes */
      for ( int index = 0; index < activity.getAttributes().getLength(); index++ )         
         if ( !activity.getAttributes().item(index).getNodeName().equals( "name" ) )
            conditionalBranch.addAttribute( 
               activity.getAttributes().item(index).getNodeName(),
               activity.getAttributes().item(index).getNodeValue() );
      
      /* setting startNode */
      IfNode startNode = 
            new IfNode( name.concat( "StartNode" ) );
      
      /* setting condition */
      startNode.setCondition( 
         activity.getElementsByTagName( CONDITION ).item( 0 ).getTextContent() );
      
      startNode.setParentConstruction( conditionalBranch );
      
      /* setting branch */
      conditionalBranch.addBranch(true,
    		  (Graph)new Parser( (Element)activity.getLastChild() ).parse() );
      
      /* setting endNode */
      EifNode endNode =
            new EifNode( name.concat( "EndNode" ) );
      
      endNode.setParentConstruction( conditionalBranch );

      /* setting child */
      conditionalBranch.setStartNode( startNode );
      conditionalBranch.setEndNode( endNode );
      
      return conditionalBranch;
   }
}
