package br.ufscar.dc.languages.wsbpel.components;

import java.util.Arrays;
import java.util.HashSet;

public interface ActivitiesName {

   /* Documentation activity */
   public static final String DOCUMENTATION      = "documentation";
   
   /* Main activity */
   public static final String PROCESS            = "process";
   
   /* Extension activity */
   public static final String EXTENSIONS         = "extensions";
   public static final String EXTENSION          = "exntesion";

   /* Imports */
   public static final String IMPORT             = "import";
   
   /* Communication channels (partnerLinks) */
   public static final String PARTNER_LINKS      = "parternLinks";
   public static final String PARTNER_LINK       = "partnerLink";

   /* message exchanges */
   public static final String MESSAGE_EXCHANGES  = "messageExchanges";
   public static final String MESSAGE_EXCHANGE   = "messageExchange";

   /* Variables declaration */
   public static final String VARIABLES          = "variables";
   public static final String VARIABLE           = "variable";
   
   /* Correlation sets */
   public static final String CORRELATION_SETS   = "correlationSets";
   public static final String CORRELATION_SET    = "correlationSet";
   
   /* Fault Handlers */
   public static final String FAULT_HANDLERS     = "faultHandlers";
   public static final String CATCH              = "catch";
   public static final String CATCH_ALL          = "catchAll";
   
   
   /* Events handlers */
   public static final String EVENT_HANDLERS     = "eventHandlers";
   public static final String ON_EVENT           = "onEvent";
   public static final String FROM_PARTS         = "fromParts";
   public static final String FROM_PART          = "fromPart";
   
   public static final String ON_ALARM           = "onAlarm";
   public static final String FOR                = "for";
   public static final String UNTIL              = "until";
   public static final String REPEAT_EVERY       = "repeatEvery";
   
   /* Sequential activity */
   public static final String SEQUENCE           = "sequence";
   
   /* Conditional activities */
   public static final String IF                 = "if";
   public static final String ELSEIF             = "elseif";
   public static final String ELSE               = "else";
   public static final String CONDITION          = "condition";
   
   /* Loop activities */
   public static final String WHILE              = "while";
   public static final String REPEAT_UNTIL       = "repeatUntil";
   public static final String FOREACH            = "forEach";
   
   /* Parallel activity */
   public static final String FLOW               = "flow";
   
   /* Communication activities */
   public static final String INVOKE             = "invoke";
   public static final String RECEIVE            = "receive";
   public static final String REPLY              = "reply";
   public static final String PICK               = "PICK";
   
   /* Exception handle activities */
   public static final String THROW              = "throw";
   public static final String RETHROW            = "rethrow";
   
   /* Data handler activities */
   public static final String ASSIGN             = "assign";
   public static final String COPY               = "copy";
   public static final String FROM               = "from";
   public static final String TO                 = "to";
   public static final String QUERY              = "query";
   public static final String LITERAL            = "literal";

   public static final String VALIDATE           = "validate";
   
   /* Delay activity */
   public static final String WAIT               = "wait";
   
   /* End activity */
   public static final String EXIT               = "exit";

   /* Scope delimiter */
   public static final String SCOPE              = "scope";
   
   /* Compensate activities */
   public static final String COMPENSATE         = "compensate";
   public static final String COMPENSATE_SCOPE   = "compensateScope";
   
   /* Extension activity */
   public static final String EXTENSION_ACTIVITY = "extensionActivity";
   
   /* Activities List */
   public static final HashSet<String> WORKFLOW_ACTIVITIES = 
      new HashSet<String>(Arrays.asList(
            SEQUENCE,
            IF,
            WHILE,
            REPEAT_UNTIL,
            FLOW,
            FOREACH,
            RECEIVE,
            REPLY,
            INVOKE,
            THROW,
            RETHROW,
            WAIT,
            EXIT,
            VALIDATE,
            SCOPE,
            ASSIGN
      )
   );
}
