package br.ufscar.dc.transformations.wsbpel;

import java.util.HashSet;

import org.w3c.dom.Document;

import br.ufscar.dc.decomposition.Decomposition;
import br.ufscar.dc.selection.Selection;
import br.ufscar.dc.transformations.Transformation;
import br.ufscar.dc.transformations.grounding.wsbpel.Grounding;
import br.ufscar.dc.transformations.lifting.wsbpel.Lifting;

public class TransformationChain implements
      Transformation<Document, HashSet<Document>> {

   private static final long serialVersionUID = -5377072869302348732L;
   
   Lifting lifting = new Lifting();
   Selection selection = new Selection();
   Decomposition decomposition = new Decomposition();
   Grounding grounding = new Grounding();

   public HashSet<Document> parse(Document source) throws Exception {

      HashSet<Document> result = null;

      result =
         grounding.generate(
            decomposition
               .transform(
                  lifting.parse(source),
                  null));

      return result;
   }

}
