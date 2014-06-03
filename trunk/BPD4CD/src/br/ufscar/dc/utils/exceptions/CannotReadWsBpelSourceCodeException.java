package br.ufscar.dc.utils.exceptions;

import java.io.IOException;

public class CannotReadWsBpelSourceCodeException extends IOException {

   private static final long serialVersionUID = 3182746785551128747L;

   public CannotReadWsBpelSourceCodeException( String file ) {
      super(String.format( "Could not read the file %s", file ) );
   }
}
