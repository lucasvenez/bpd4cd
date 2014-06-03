package br.ufscar.dc.transformations.lifting;

import javax.activation.UnsupportedDataTypeException;

import br.ufscar.dc.transformations.Transformation;

/**
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 05/11/2012
 *
 * @param <T>
 */
public abstract class ActivityParser<T1,T2> implements Transformation<T1, T2> {

   private static final long serialVersionUID = -3746769169968597205L;
   
   protected T1 activity;
   
   public ActivityParser(T1 activity) {
      this.activity = activity;
   }
   
   public abstract T2 parse() throws UnsupportedDataTypeException;

   public T1 getActivity() {
      return activity;
   }

   public void setActivity(T1 activity) {
      this.activity = activity;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((activity == null) ? 0 : activity.hashCode());
      return result;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof ActivityParser)) {
         return false;
      }
      ActivityParser<?,?> other = (ActivityParser<?,?>) obj;
      if (activity == null) {
         if (other.activity != null) {
            return false;
         }
      } else if (!activity.equals(other.activity)) {
         return false;
      }
      return true;
   }
}
