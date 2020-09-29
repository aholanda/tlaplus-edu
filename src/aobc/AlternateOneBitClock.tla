
----------------------------- MODULE AlternateOneBitClock -----------------------------
VARIABLE clk \* clock variable

Init == clk \in {0,1} \* Initial predicate

Invariant == clk \in {0,1}

(* Next states *)
Next == \/ /\ clk  = 0
           /\ clk' = 1
        \/ /\ clk  = 1
           /\ clk' = 0

Spec == Init /\ [][Next]_<<clk>>
=============================================================================          