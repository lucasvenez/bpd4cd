package br.ufscar.dc.transformations.lifting;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.transformations.Transformation;

/**
 * Generalization of the lifting transformation that will get a <Source> and generate a Graph.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 *
 * @param <Source> is the data type that will be used as the input of the lifting transformation.
 * 
 * @see Graph
 */
public interface LiftingTransformation<Source> extends Transformation<Source, Graph> {

   /**
    * Method used to process the transformation from a <Source> to a <Result>.
    * @param source is the input of the transformation.
    * @return an output typed as <Result>.
    * @throws Exception 
    */
   public Graph parse(Source source) throws Exception;
}
