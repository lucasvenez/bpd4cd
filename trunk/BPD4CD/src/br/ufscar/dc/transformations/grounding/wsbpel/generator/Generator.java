package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.construction.ConditionalBranch;
import br.ufscar.dc.gwm.construction.Loop;
import br.ufscar.dc.gwm.construction.ParallelBranches;
import br.ufscar.dc.gwm.interfaces.IConcern;
import br.ufscar.dc.gwm.node.ActivityNode;
import br.ufscar.dc.gwm.node.communication.CommunicationNode;
import br.ufscar.dc.gwm.node.communication.GetNode;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class Generator extends ActivityGenerator<IConcern, Element> {

   private static final long serialVersionUID = 5273940858395330913L;
   
   public Generator(IConcern activity) {
      super(activity);
   }

   public Element generate() throws UnsupportedDataTypeException {
      
      ActivityGenerator<?, Element> generator = null;
      
      if (activity instanceof ParallelBranches) 
         generator = new ParallelBranchesGenerator((ParallelBranches) activity);         
      
      else if (activity instanceof ConditionalBranch) 
         generator = new ConditionalBranchGenerator((ConditionalBranch) activity);
      
//      else if (activity instanceof Partition) 
//         generator = new GraphGenerator(((Partition) activity).getInnerGraph());
      
      else if (activity instanceof Loop) 
         generator = new LoopGenerator((Loop) activity);
      
      else if (activity instanceof CommunicationNode) {
         if (!(activity instanceof GetNode)) 
            generator = new CommunicationNodeGenerator((CommunicationNode) activity);
         else
            return null;
      }
      else if (activity instanceof ActivityNode) 
         generator = new ActivityNodeGenerator((ActivityNode) activity);
      
      else if (activity instanceof Graph) 
         generator = new GraphGenerator((Graph) activity);
      
      return generator.generate();
   }
}
