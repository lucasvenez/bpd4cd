package br.ufscar.dc.transformations.grounding;

import br.ufscar.dc.transformations.Transformation;

public interface GroundingTransformation<Target> extends Transformation<GraphCollaboration, Target> {
   
   public Target generate(GraphCollaboration graphCollaboration) throws Exception;
      
}
