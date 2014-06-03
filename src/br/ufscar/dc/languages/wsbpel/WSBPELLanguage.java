package br.ufscar.dc.languages.wsbpel;

import java.util.Arrays;
import java.util.HashSet;

import br.ufscar.dc.languages.WorkflowLanguage;
import br.ufscar.dc.languages.wsbpel.components.ActivitiesAttributes;
import br.ufscar.dc.languages.wsbpel.components.ActivitiesChildren;
import br.ufscar.dc.languages.wsbpel.components.ActivitiesName;

/**
 * This class contains informations of WS-BPEL language, like activities name, activities attributes
 * and activities children.
 * @author <a href="mailto:lucasvenez@gmail.com">Lucas Venezian Povoa</a>
 * @since 03/11/2012
 */
public interface WSBPELLanguage extends WorkflowLanguage, 
      ActivitiesName, ActivitiesChildren, ActivitiesAttributes {

   public static final HashSet<String> ACTIVITIES = new HashSet<String>(
      Arrays.asList(
         ActivitiesName.ASSIGN,
         ActivitiesName.CATCH,
         ActivitiesName.CATCH_ALL,
         ActivitiesName.CONDITION,
         ActivitiesName.COPY,
         ActivitiesName.ELSE,
         ActivitiesName.ELSEIF,
         ActivitiesName.EXIT,
         ActivitiesName.EXTENSION,
         ActivitiesName.EXTENSION_ACTIVITY,
         ActivitiesName.FLOW,
         ActivitiesName.FOR,
         ActivitiesName.FOREACH,
         ActivitiesName.FROM,
         ActivitiesName.FROM_PART,
         ActivitiesName.IF,
         ActivitiesName.INVOKE,
         ActivitiesName.LITERAL,
         ActivitiesName.ON_ALARM,
         ActivitiesName.PICK,
         ActivitiesName.RECEIVE,
         ActivitiesName.REPEAT_EVERY,
         ActivitiesName.REPEAT_UNTIL,
         ActivitiesName.REPLY,
         ActivitiesName.RETHROW,
         ActivitiesName.SEQUENCE,
         ActivitiesName.THROW,
         ActivitiesName.TO,
         ActivitiesName.WHILE,
         ActivitiesName.WAIT,
         ActivitiesName.VALIDATE,
         ActivitiesName.UNTIL
      ));
}
