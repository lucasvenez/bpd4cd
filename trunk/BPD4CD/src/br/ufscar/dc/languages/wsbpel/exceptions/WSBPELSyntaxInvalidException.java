package br.ufscar.dc.languages.wsbpel.exceptions;


/**
 * Is throw when an invalid WSBPEL syntax is processed.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 10/11/2012
 *
 */
public class WSBPELSyntaxInvalidException extends RuntimeException {

   private static final long serialVersionUID = -6869860837323786877L;

   public WSBPELSyntaxInvalidException() {
      super();
   }

   public WSBPELSyntaxInvalidException(String message, Throwable cause,
         boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public WSBPELSyntaxInvalidException(String message, Throwable cause) {
      super(message, cause);
   }

   public WSBPELSyntaxInvalidException(String message) {
      super(message);
   }

   public WSBPELSyntaxInvalidException(Throwable cause) {
      super(cause);
   }
}
