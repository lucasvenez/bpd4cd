package br.ufscar.dc.transformations.grounding;

import br.ufscar.dc.decomposition.Coreography;
import br.ufscar.dc.transformations.Transformation;

public interface GroundingTransformation<Target> extends Transformation<Coreography, Target> {
   
   public Target generate(Coreography graphCollaboration) throws Exception;
      
}
