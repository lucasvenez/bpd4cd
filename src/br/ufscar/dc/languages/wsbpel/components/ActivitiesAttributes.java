package br.ufscar.dc.languages.wsbpel.components;

import java.util.Arrays;
import java.util.HashSet;

public interface ActivitiesAttributes {

   public static final HashSet<String> STANDARD_ATTRIBUTES = new HashSet<String>( Arrays.asList(
      "name",
      "suppressJoinFailure"
   ));
   
   public static final HashSet<String> WHILE_ATTRIBUTES = STANDARD_ATTRIBUTES;
   
   public static final HashSet<String> IF_ATTRIBUTES    = STANDARD_ATTRIBUTES;
}
