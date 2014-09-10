package br.ufscar.dc.transformations.lifting.wsbpel;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CORRELATION_SETS;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.DOCUMENTATION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.EVENT_HANDLERS;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.EXTENSION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.FAULT_HANDLERS;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.IMPORT;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.MESSAGE_EXCHANGES;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.PARTNER_LINKS;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.VARIABLES;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.WORKFLOW_ACTIVITIES;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.HashSet;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.Node;
import br.ufscar.dc.languages.wsbpel.exceptions.WSBPELSyntaxInvalidException;
import br.ufscar.dc.transformations.lifting.LiftingTransformation;
import br.ufscar.dc.transformations.lifting.wsbpel.parser.Parser;

/**
 * Implementation of the lifting transformation to WS-BPEL language.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 */
public class Lifting implements LiftingTransformation<Document> {
 
   private static final long serialVersionUID = 1511738605587295421L;

   /**
    * Execute the parse from a Document with WS-BPEL code to a Graph.
    * @param source is a document contains a WS-BPEL source code.
    * @param activityRoot 
    * @return a Graph with the workflow from WS-BPEL source code.
    * @throws WSBPELSyntaxInvalidException 
    * @throws UnsupportedDataTypeException 
    * @see Document, Graph
    */
   @Override
   public Graph parse(Document source) throws WSBPELSyntaxInvalidException, UnsupportedDataTypeException {
      
      Graph graph = new Graph();
      
      Element activityRoot = null;
      
      Element processElement = source.getDocumentElement();
      
      if (!processElement.getNodeName().equals("process"))
         throw new InvalidParameterException("The root element do not have the tag name process");
      
      if ( processElement.getAttributes().getLength() > 0 ) {
         
         HashMap<String,String> processAttributes = new HashMap<String,String>();
         
         for (int i = 0; i < processElement.getAttributes().getLength(); i++)
            processAttributes.put(
               processElement.getAttributes().item(i).getNodeName(),
               processElement.getAttributes().item(i).getTextContent());
         
         graph.addAttribute( "processAttributes", processAttributes);
      }

      HashSet<Element> imports = new HashSet<Element>();
      
      for (int i = 0; i < processElement.getChildNodes().getLength(); i++) {
         
         switch(processElement.getChildNodes().item(i).getNodeName()) {
         
            case PARTNER_LINKS    : graph.addAttribute("partnerLinks", (Element)processElement.getChildNodes().item(i) ); break;
            case EXTENSION        : graph.addAttribute("extensions", (Element)processElement.getChildNodes().item(i) ); break;
            case IMPORT           : imports.add( (Element)processElement.getChildNodes().item(i) ); break;
            case MESSAGE_EXCHANGES: graph.addAttribute("messageExchanges", (Element)processElement.getChildNodes().item(i)); break;
            case VARIABLES        : graph.addAttribute("variables", (Element)processElement.getChildNodes().item(i)); break;
            case CORRELATION_SETS : graph.addAttribute("correlationsSets", (Element)processElement.getChildNodes().item(i)); break;
            case FAULT_HANDLERS   : graph.addAttribute("faultHandlers", (Element)processElement.getChildNodes().item(i)); break;
            case EVENT_HANDLERS   : graph.addAttribute("eventHandlers", (Element)processElement.getChildNodes().item(i)); break;
            case DOCUMENTATION    : graph.addAttribute("documentations", (Element)processElement.getChildNodes().item(i)); break;
            
            default: 
               if ( WORKFLOW_ACTIVITIES.contains( processElement.getChildNodes().item(i).getNodeName() ) )
                  activityRoot = (Element)processElement.getChildNodes().item(i);
         }
      }
      
      if (imports.size() > 0)
         graph.addAttribute("imports", imports);

      if ( activityRoot == null )
         throw new WSBPELSyntaxInvalidException();
      
      Object parserOutput = new Parser( activityRoot ).parse();
      
      if ( parserOutput instanceof Graph )
         graph.merge( (Graph)parserOutput );
      
      else if ( parserOutput instanceof Node )
         graph.addNode( (Node)parserOutput );
      
      else
         throw new UnsupportedDataTypeException(parserOutput.getClass().getName());
      
      return graph;
   }
}
