package br.ufscar.dc.utils;

import java.util.HashMap;
import java.util.Set;

/**
 * This implementation contains methods used to process the input parameters.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 05/11/2012
 *
 */
public class ParameterHelper implements Helper {

   private static final long serialVersionUID = 4062439801607969623L;
   
   private HashMap<String,String> parameters;

   public ParameterHelper( String ... args ) throws IllegalArgumentException {
      this.parameters = normalizeParameters( args );
   }
   
   /**
    * Generates a HashSet with pairs of strings in form <parameter_name, paramter_value>.
    * e.g, <-f,/path/of/file.txt> or <-o,null> when the parameter is just a flag.
    * 
    * @param args - An array os string contains the parameters name and its values.
    * @return a Hash set with pairs of strings in form <parameter_name, paramter_value>.
    */
   public HashMap<String,String> normalizeParameters( String[] args ) throws IllegalArgumentException {
      
      HashMap<String,String> result = new HashMap<String,String>();
      
      for ( String arg : args  ) {
         
         boolean lastIsName = false;
         
         String parameterName  = null;
         
         if ( arg.matches( "^-[-]?.+$" ) ) {
            
            if ( lastIsName )
               result.put( parameterName, null );
               
            parameterName = arg;
            lastIsName    = true;
         }
         else {
            
            lastIsName = false;
            
            if ( lastIsName )
               result.put( parameterName, arg );
            
            else
               throw new IllegalArgumentException();
         }
      }
      
      return result;
   }

   public void checkRequiredParameters( String... requiredParamters ) throws IllegalArgumentException {
      //FIXME Modify this method to accept a set of possible names of a parameter.
      Set<String> set = parameters.keySet();
      
      for ( String arg : requiredParamters ) {
         
         if ( !set.contains( arg ) )
            throw new IllegalArgumentException();
      }
   }
   
   public String getValue( String ... possibleNamesOfTheParameter ) {
      
      for ( String name : possibleNamesOfTheParameter )
         if ( this.parameters.containsKey( name ) )
            return this.parameters.get( name );
      
      return null;
   }

   public HashMap<String,String> getParameters() {
      return parameters;
   }

   public void setParameters(HashMap<String,String> parameters) {
      this.parameters = parameters;
   }
}