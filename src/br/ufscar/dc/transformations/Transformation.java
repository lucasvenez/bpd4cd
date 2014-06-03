package br.ufscar.dc.transformations;

import java.io.Serializable;

/**
 * Generalization of the transformation activity.
 * 
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 *
 * @since 03/11/2012
 *
 * @param <Source> is the data type that will be used as the input of the transformation.
 * @param <Result> is the data type that will be used as the output of the transformation.
 */
public interface Transformation<Source,Result> extends Serializable {

}
