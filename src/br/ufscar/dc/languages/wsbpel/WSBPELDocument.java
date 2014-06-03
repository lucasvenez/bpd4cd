package br.ufscar.dc.languages.wsbpel;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.*;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * All WSBPEL language components are handlers. Workflow, Global Data, Imports, Partner Links, etc.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 05/11/2012
 *
 */
public class WSBPELDocument {

   Document document; 
   
   public WSBPELDocument(Document document) {
      this.document = document;
   }
   
   public Node getGlobalData() {
      return getGlobalActivity( VARIABLES );
   }
   
   public Node getGlobalPartnerLinks() {
      return getGlobalActivity( PARTNER_LINKS );
   }
   
   public List<Node> getGlobalImports() {
      
      Node process = this.document.getDocumentElement();
      
      List<Node> imports = new ArrayList<Node>();
      
      for( int index = process.getChildNodes().getLength() - 1; index >= 0; index-- ) {
         if ( process.getChildNodes().item(index).getNodeName().equals( IMPORT ) ) {
            imports.add( process.getChildNodes().item(index) );
         }
      }
      
      return imports;
   }
   
   public Node getGlobalFaultHandlers() {
      return getGlobalActivity( FAULT_HANDLERS );
   }
   
   public Node getGlobalExtensions() {
      return getGlobalActivity( EXTENSIONS );
   }
   
   public Node getGlobalMessagesExchanges() {
      return getGlobalActivity( MESSAGE_EXCHANGES );
   }
   
   public Node getGlobalCorrelationSets() {
      return getGlobalActivity( CORRELATION_SETS );
   }
   
   public Node getGlobalEventHandlers() {
     
      return getGlobalActivity( EVENT_HANDLERS );
   }

   private Node getGlobalActivity( String activity ) {
      Node result = null;
      
      Node process = this.document.getDocumentElement();
      
      for( int index = 0; index < process.getChildNodes().getLength(); index++ ) {
         if ( process.getChildNodes().item(index).getNodeName().equals( activity ) ) {
            result = process.getChildNodes().item(index);
            break;
         }
      }
      
      return result;
   }
   
   /**
    * Method used to get the full workflow from the WSBPEL source code.
    * @return A node containing the root element with its children from the workflow of a business process.
    */
   public Node getWorkflow() {

      Node result = null;
      
      Node process = this.document.getDocumentElement();
      
      for( int index = process.getChildNodes().getLength() - 1; index >= 0; index-- ) {
         if ( WORKFLOW_ACTIVITIES.contains( process.getChildNodes().item(index).getNodeName() ) ) {
            result = process.getChildNodes().item(index);
            break;
         }
      }
      
      return result;
  }
   
   /* Getters and Setters */
   
   public Document getDocument() {
      return document;
   }

   public void setDocument(Document document) {
      this.document = document;
   }
}
