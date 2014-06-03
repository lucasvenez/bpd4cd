package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.ActivityNode;
import nl.utwente.eemcs.graph.CommunicatorNode;
import nl.utwente.eemcs.graph.CommunicatorType;
import nl.utwente.eemcs.graph.ConditionalConstruct;
import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.LoopConstruct;
import nl.utwente.eemcs.graph.ParallelConstruct;
import nl.utwente.eemcs.graph.Partition;

import org.w3c.dom.Element;

import br.ufscar.dc.graph.Abstraction;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class Generator extends ActivityGenerator<Abstraction, Element> {

   private static final long serialVersionUID = 5273940858395330913L;
   
   public Generator(Abstraction activity) {
      super(activity);
   }

   public Element generate() throws UnsupportedDataTypeException {
      
      ActivityGenerator<?, Element> generator = null;
      
      if (activity instanceof ParallelConstruct) 
         generator = new ParallelConstructGenerator((ParallelConstruct) activity);         
      
      else if (activity instanceof ConditionalConstruct) 
         generator = new ConditionalConstructGenerator((ConditionalConstruct) activity);
      
      else if (activity instanceof Partition) 
         generator = new GraphGenerator(((Partition) activity).getInnerGraph());
      
      else if (activity instanceof LoopConstruct) 
         generator = new LoopConstructGenerator((LoopConstruct) activity);
      
      else if (activity instanceof CommunicatorNode) {
         if (!((CommunicatorNode)activity).getType().equals(CommunicatorType.InvokeRes)) 
            generator = new CommunicatorNodeGenerator((CommunicatorNode) activity);
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
