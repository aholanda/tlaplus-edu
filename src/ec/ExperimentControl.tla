------------------------- MODULE ExperimentControl -------------------------
(* Specification of a system to control an experiment to characterize a 
   material based on a curve of heating response. The material is put in 
   a compartment with a resistance attached and a power supply to send voltage
   to resistance to heat the material. When it reachs a High value, the material 
   is freezed having a Low temperature as a parameter to reverse again to 
   heating phase. Then, when High temperature is reached, it is frezeed again and 
   then reaching Low temperature, the experiment halts. *)
EXTENDS Integers
VARIABLES delta,freeze,pc,power,temperature,phase
CONSTANTS High, Low, Delta

Invariant == /\ pc \in {"HEAT", "FREEZE", "DONE"}
             /\ power \in {"ON", "OFF"}
             /\ freeze \in {"YES", "NO"}
             /\ temperature \in Int
             /\ phase \in {1,2,3,4}
             /\ Low < High

Init == /\ pc = "HEAT"
        /\ power = "ON"
        /\ freeze = "NO"
        /\ temperature = Low
        /\ phase = 1
        /\ delta = Delta

Heat == /\ pc = "HEAT"
        /\ IF temperature >= High THEN /\ freeze' = "YES"
                                       /\ power' = "OFF"                                         
                                       /\ pc' = "FREEZE"
                                       /\ phase' = phase + 1
                                       /\ UNCHANGED <<delta,temperature>>
                                  ELSE /\ temperature' = temperature + delta
                                       /\ UNCHANGED <<delta,power,freeze,pc,phase>>
                                  
Freeze == /\ pc = "FREEZE"
          /\ IF temperature <= Low THEN /\ freeze' = "NO" 
                                        /\ IF phase # 4 THEN /\ power' = "ON"
                                                             /\ pc' = "HEAT"
                                                             /\ phase' = phase + 1
                                                        ELSE /\ power' = "OFF"
                                                             /\ pc' = "DONE"
                                                             /\ UNCHANGED phase
                                        /\ UNCHANGED <<delta,temperature>>                                                        
                                    ELSE /\ temperature' = temperature - delta
                                         /\ UNCHANGED <<delta,power,freeze,pc,phase>>
Next == Heat \/ Freeze

vars == <<delta,freeze,pc,power,temperature,phase>>

Spec == Init /\ [][Next]_<<vars>>
=============================================================================

