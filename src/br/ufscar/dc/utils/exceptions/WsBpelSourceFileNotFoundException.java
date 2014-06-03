package br.ufscar.dc.utils.exceptions;

import java.io.FileNotFoundException;

/**
 * Exception to WS-BPEL files not found.
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 *
 */
public class WsBpelSourceFileNotFoundException extends FileNotFoundException {

   private static final long serialVersionUID = -5333133389599470373L;

   public WsBpelSourceFileNotFoundException(String file) {
      super(String.format("WS-BPEL file %s with the source code was not found.", file ) );
   }
}
