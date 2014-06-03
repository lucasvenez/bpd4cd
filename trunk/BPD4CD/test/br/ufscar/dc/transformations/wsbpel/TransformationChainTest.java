package br.ufscar.dc.transformations.wsbpel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashSet;

import nl.utwente.eemcs.decomposition.Decomposition;
import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.GraphCollaboration;
import nl.utwente.eemcs.graph.Partition;
import nl.utwente.eemcs.graph.distribution.DistributionList;
import nl.utwente.eemcs.graph.distribution.DistributionLocation;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import br.ufscar.dc.transformations.grounding.wsbpel.Grounding;
import br.ufscar.dc.transformations.lifting.wsbpel.Lifting;
import br.ufscar.dc.utils.WSBPELSourceCode;

public class TransformationChainTest {

   Document document;

   @Before
   public void setUp() throws Exception {
      document = new WSBPELSourceCode()
            .loadSourceFile("./examples/pacs/PACSBusinessProcess.bpel");
   }

   @Test
   public void liftingAndDecomposition() {

      try {

         Graph lifting = new Lifting().parse(this.document);

         /*
          * Setting activity to be executed on the cloud
          */
         DistributionList distributionList = new DistributionList();
         
         distributionList.addNodeDistribution(
            lifting.getNodeByNameRecursively("AutomaticNoduleDetectionIreq"), 
            DistributionLocation.Cloud);
         
         distributionList.addNodeDistribution(
            lifting.getNodeByNameRecursively("AutomaticNoduleDetectionRec"), 
            DistributionLocation.Cloud);
         
         distributionList.addNodeDistribution(
            lifting.getNodeByNameRecursively("automaticNoduleIdentification"), 
            DistributionLocation.Cloud);
         
         distributionList.addNodeDistribution(
            lifting.getNodeByNameRecursively("AutomaticNoduleDetectionRep"), 
            DistributionLocation.Cloud);
         
         distributionList.addNodeDistribution(
            lifting.getNodeByNameRecursively("AutomaticNoduleDetectionIres"), 
            DistributionLocation.Cloud);
         
         /* Lifting tests */
         assertNotNull(lifting.getNodeByNameRecursively("AutomaticNoduleDetectionIreq").getParentGraph());
         
         GraphCollaboration decomposition = new Decomposition().transform(lifting, distributionList);

         /* Decomposition tests */
         assertNotNull(decomposition);
         assertEquals(3, ((Partition)decomposition.getMainProcess().getNodes().get(0)).getInnerGraph().getNodes().size());
         
         Grounding grounding = new Grounding();
         HashSet<Document> result = grounding.generate(decomposition);
         
         /* Grounding tests */
         assertNotNull(result);
         assertEquals(3,result.size());

      } 
      catch (Exception e) {
         e.printStackTrace();
         fail(e.getMessage());
      }
   }

   @Test
   public void fullChain() {
   }
}
