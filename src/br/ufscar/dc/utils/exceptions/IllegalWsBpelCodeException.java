package br.ufscar.dc.utils.exceptions;

/**
 * Exception to illegal WSBPEL code.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 */
public class IllegalWsBpelCodeException extends Exception {

   private static final long serialVersionUID = -225835405784140860L;

   public IllegalWsBpelCodeException() {
      super();
   }

   public IllegalWsBpelCodeException(String message, Throwable cause,
         boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

   public IllegalWsBpelCodeException(String message, Throwable cause) {
      super(message, cause);
   }

   public IllegalWsBpelCodeException(String message) {
      super(message);
   }

   public IllegalWsBpelCodeException(Throwable cause) {
      super(cause);
   }
}
