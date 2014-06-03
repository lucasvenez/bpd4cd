package br.ufscar.dc.transformations.grounding;

import javax.activation.UnsupportedDataTypeException;

import br.ufscar.dc.transformations.Transformation;

public abstract class ActivityGenerator<T1, T2> implements Transformation<T1, T2>{
   
   private static final long serialVersionUID = -9019571241312922260L;
   
   protected T1 activity;
   
   public ActivityGenerator(T1 activity) {
      this.activity = activity;
   }
   
   public abstract T2 generate() throws UnsupportedDataTypeException;
}
