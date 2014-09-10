package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ASSIGN;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FLOW;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FOREACH;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.IF;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.INVOKE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.RECEIVE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.REPLY;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.RETHROW;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.SEQUENCE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.THROW;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.VALIDATE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WHILE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WORKFLOW_ACTIVITIES;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.transformations.lifting.ActivityParser;

/**
 * This class converts an WS-BPEL activity described in Element into a graph
 * that has all children elements from the activity.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 08/11/2012
 * 
 */
public class Parser extends ActivityParser<Element, Object> {

   private static final long serialVersionUID = -7327472183440971844L;

   public Parser(Element activity) {
      super(activity);

      if (!WORKFLOW_ACTIVITIES.contains(activity.getNodeName()))
         throw new IllegalArgumentException(activity.getNodeName());
   }

   @Override
   public Object parse() throws UnsupportedDataTypeException {

      Graph result = null;

      ActivityParser<?, ?> parser = null;

      switch( activity.getNodeName() ) {
         
         case SEQUENCE: parser = new SequenceParser(super.activity); break;
         case INVOKE  : parser = new InvokeParser(super.activity);   break;
         case RECEIVE : parser = new ReceiveParser(super.activity);  break; 
         case REPLY   : parser = new ReplyParser(super.activity);    break; 
         case THROW   : parser = new ThrowParser(super.activity);    break;
         case RETHROW : throw new UnsupportedDataTypeException();
         case IF      : parser = new IfParser(super.activity);       break;
         case FLOW    : parser = new FlowParser(super.activity);     break;
         case ASSIGN  : parser = new AssignParser(super.activity);   break;
         case VALIDATE: throw new UnsupportedDataTypeException();
         case WHILE   : parser = new WhileParser(super.activity);    break;
         case FOREACH : throw new UnsupportedDataTypeException();
         
         default: throw new UnsupportedDataTypeException();
      }

      if (parser.parse() instanceof Graph)
         result = (Graph) parser.parse();

      else if (parser.parse() instanceof Node ) {
         result = new Graph();
         result.addNode((Node)parser.parse());
      }

      return result;
   }
}