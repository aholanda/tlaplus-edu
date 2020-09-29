-------------------------------- MODULE GCD --------------------------------
(* Specification for the algorithm to find the greatest common divisor 
   between two integer numbers greater than zero, aka Euclid algorithm. *)
EXTENDS Integers
CONSTANTS M,N
VARIABLES x,y
PositiveInteger(n) == n \in Nat /\ n # 0
-----------------------------------------------------------------------------
TypeInvariant == /\ PositiveInteger(x)
                 /\ PositiveInteger(y)

(* Initial state *)
Init == (x=M) /\ (y=N)

(* Next state of computation *)
Next ==   ((x<y) /\ (x'=x) /\ (y'=y-x))
           \/  ((y<x) /\ (y'=y) /\ (x'=x-y))

Spec == Init /\ [][Next]_<<x,y>>
-----------------------------------------------------------------------------
THEOREM Spec => TypeInvariant
=============================================================================
