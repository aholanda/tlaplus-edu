-------------------------- MODULE HourMinuteClock --------------------------
(* Specification of a clock that handles hours and minutes. *)
EXTENDS Naturals
VARIABLE hr, min
Init == /\ hr \in (1..12)
        /\ min \in (0..59)

(* Hour hr is unchanged if minute does not reach 60 and is zeroed. *)
Next == /\ min' = (min + 1) % 60 
        /\ IF min = 0 THEN hr' = (hr % 12) + 1
                      ELSE UNCHANGED <<hr>>
                      
Spec == Init /\ [][Next]_<<hr,min>>
----------------------------------------------------------------------------
THEOREM Spec => []Init
=============================================================================

