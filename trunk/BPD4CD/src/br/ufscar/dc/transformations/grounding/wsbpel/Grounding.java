package br.ufscar.dc.transformations.grounding.wsbpel;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.PROCESS;

import java.util.HashMap;
import java.util.HashSet;

import javax.activation.UnsupportedDataTypeException;

import nl.utwente.eemcs.graph.Graph;
import nl.utwente.eemcs.graph.GraphCollaboration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufscar.dc.transformations.grounding.GroundingTransformation;
import br.ufscar.dc.transformations.grounding.wsbpel.generator.Generator;
import br.ufscar.dc.utils.XMLUtils;

public class Grounding implements GroundingTransformation<HashSet<Document>> {

   private static final long serialVersionUID = 1156326093375571137L;

   @Override
   public HashSet<Document> generate(GraphCollaboration graphCollaboration) throws UnsupportedDataTypeException {
      
      HashSet<Document> result = new HashSet<Document>();
      
      for (Graph g : graphCollaboration.getProcesses()) {

         /*
          * FIXME Check process name and verify data communication
          */
         Graph main = graphCollaboration.getMainProcess();
         
         Document d = XMLUtils.createDocument(false, "1.0");
         
         Element process = XMLUtils.createElement(PROCESS);

         if (main.getAttributes().containsKey("processAttributes"))
            for (String key : main.getAttributes().keySet())
               if (main.getAttributes().get(key) instanceof String)
                  process.setAttribute(key, (String)main.getAttributes().get(key));
         
         for(String key : main.getAttributes().keySet()) {
            if (main.getAttributes().get(key) instanceof Element)
               process.appendChild(XMLUtils.getDocument().importNode((Element)main.getAttributes().get(key), true));
            else if (main.getAttributes().get(key) instanceof HashMap<?, ?>)
               for (Object attr : ((HashMap<?, ?>)main.getAttributes().get(key)).keySet())
                  if (attr instanceof String)
                     process.setAttribute(
                        (String)attr,
                        (String)((HashMap<?, ?>)main.getAttributes().get(key)).get(attr)
                     );
         }

         process.appendChild(new Generator(g).generate());
         d.appendChild(d.importNode(process, true));
         
         result.add(d);
      }

      /*
       * FIXME Generate WSDL
       */
      Document wsdl = XMLUtils.createDocument(false, "1.0");
      result.add(wsdl);
      
      return result;
   }
}
