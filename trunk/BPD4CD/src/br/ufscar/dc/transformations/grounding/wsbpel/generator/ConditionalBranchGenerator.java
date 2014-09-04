package br.ufscar.dc.transformations.grounding.wsbpel.generator;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.CONDITION;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSE;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.ELSEIF;
import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.IF;
import static br.ufscar.dc.utils.XMLUtils.createCDATASection;
import static br.ufscar.dc.utils.XMLUtils.createElement;

import java.util.HashSet;
import java.util.Set;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Element;

import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.gwm.construction.ConditionalBranch;
import br.ufscar.dc.transformations.grounding.ActivityGenerator;

public class ConditionalBranchGenerator extends ActivityGenerator<ConditionalBranch, Element> {

   private static final long serialVersionUID = 6360613821482559196L;

   public ConditionalBranchGenerator(ConditionalBranch activity) {
      super(activity);
   }

   @Override
   public Element generate() throws UnsupportedDataTypeException {
      Element result = createElement(IF);

      /* Setting condition */
      Element condition = createElement(CONDITION);
      condition.appendChild(createCDATASection(activity.getStartNode().getCondition()));
      result.appendChild(condition);
      
      /* setting true branch */
      Element trueBranch = new GraphGenerator(activity.getBranch(true)).generate();
      result.appendChild(trueBranch);
      
      /* Setting false branch */
      for(Element e : generateFalseBranch(activity.getBranch(false)) )
         result.appendChild(e);
      
      return result;
   }
   
   private Set<Element> generateFalseBranch(Graph graph) throws UnsupportedDataTypeException {
      Set<Element> result = new HashSet<Element>();
      
      Graph current = graph;
      
      while (current != null) {
         
         Element branch = null;
         
         if (
         current.getStartNode() instanceof ConditionalBranch
         &&
         current.getNodes().size() == 1) {

            ConditionalBranch elseifBranch = (ConditionalBranch)current.getStartNode();
            
            Element condition = createElement(CONDITION);
            condition.appendChild(createCDATASection(elseifBranch.getStartNode().getCondition()));

            branch = createElement(ELSEIF);
            
            branch.appendChild(condition);
            branch.appendChild(new GraphGenerator(elseifBranch.getBranch(true)).generate());
            
            current = elseifBranch.getBranch(false);
         }
         else if (current.getNodes().size() >= 1) {
            branch = createElement(ELSE);
            branch.appendChild(new GraphGenerator(current).generate());
            current = null;
         }
         
         if (branch != null)
            result.add(branch);
      }
      
      return result;
   }

}
