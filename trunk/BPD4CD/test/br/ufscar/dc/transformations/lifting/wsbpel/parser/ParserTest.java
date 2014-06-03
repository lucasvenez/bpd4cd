package br.ufscar.dc.transformations.lifting.wsbpel.parser;

import javax.activation.UnsupportedDataTypeException;

import org.junit.Test;

public abstract class ParserTest {

   /**
    * Unit test used to verify whether a new instance
    * of an ActivityParser triggered the IllegalArgumentException
    * when an invalid parameters is used on constructor method.
    * @throws UnsupportedDataTypeException 
    */
   @Test(expected=IllegalArgumentException.class)
   public abstract void expectedIllegalArgumentExceptionTest() throws UnsupportedDataTypeException;
}
