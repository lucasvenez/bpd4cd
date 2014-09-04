package br.ufscar.dc.transformations.grounding.wsbpel;

import static br.ufscar.dc.languages.wsbpel.components.ActivitiesName.PROCESS;

import java.util.HashMap;
import java.util.HashSet;

import javax.activation.UnsupportedDataTypeException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufscar.dc.decomposition.Coreography;
import br.ufscar.dc.gwm.Graph;
import br.ufscar.dc.transformations.grounding.GroundingTransformation;
import br.ufscar.dc.transformations.grounding.wsbpel.generator.Generator;
import br.ufscar.dc.utils.XMLUtils;

public class Grounding implements GroundingTransformation<HashSet<Document>> {

   private static final long serialVersionUID = 1156326093375571137L;

   @Override
   public HashSet<Document> generate(Coreography graphCollaboration) throws UnsupportedDataTypeException {
      
      HashSet<Document> result = new HashSet<Document>();
      
      for (Graph g : graphCollaboration.getSubprocesses()) {

         /*
          * FIXME Check process name and verify data communication
          */
         Graph main = graphCollaboration.getMainProcess();
         
         Document d = XMLUtils.createDocument(false, "1.0");
         
         Element process = XMLUtils.createElement(PROCESS);

         if (main.hasAttribute("processAttributes"))
            for (String key : main.getKeys())
               if (main.getAttribute(key) instanceof String)
                  process.setAttribute(key, (String)main.getAttribute(key));
         
         for(String key : main.getKeys()) {
            if (main.getAttribute(key) instanceof Element)
               process.appendChild(XMLUtils.getDocument().importNode((Element)main.getAttribute(key), true));
            else if (main.getAttribute(key) instanceof HashMap<?, ?>)
               for (Object attr : ((HashMap<?, ?>)main.getAttribute(key)).keySet())
                  if (attr instanceof String)
                     process.setAttribute(
                        (String)attr,
                        (String)((HashMap<?, ?>)main.getAttribute(key)).get(attr)
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
