package br.ufscar.dc.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.ufscar.dc.utils.exceptions.CannotReadWsBpelSourceCodeException;
import br.ufscar.dc.utils.exceptions.WsBpelSourceFileNotFoundException;

/**
 * This class contains resources to load and validate a WS-BPEL source code.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 *
 */
public class WSBPELSourceCode {

   /**
    * Method used to load and validate a WsBpel source code.
    * @param pathOfSourceFile - A file contains WsBpel source code. 
    * @throws WsBpelSourceFileNotFoundException - if the WsBpel file is not found.
    * @throws CannotReadWsBpelSourceCodeException if the WsBpel cannot be read.
    * @throws NullPointerException - if the pathOfSourceFile argument is null
    * 
    * @return A Document that contains a WsBpel source code.
    */
   public Document loadSourceFile( String pathOfSourceFile ) 
         throws WsBpelSourceFileNotFoundException, CannotReadWsBpelSourceCodeException, NullPointerException {
      
      Document document = null;
      
      File file = new File( pathOfSourceFile );

      if ( !file.exists() )
         throw new  WsBpelSourceFileNotFoundException( pathOfSourceFile );
      
      if ( !file.canRead() )
         throw new CannotReadWsBpelSourceCodeException( pathOfSourceFile );
      
      try {
         
         DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      
         DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
         
         document = documentBuilder.parse( file );
      }
      catch ( ParserConfigurationException | SAXException | IOException exception ) {
         
         exception.printStackTrace();
      }
      
      return document;
   }
   
   public void validateSourceCode( Document document ) {
      //TODO Generate the WSBPEL validate
   }
}
