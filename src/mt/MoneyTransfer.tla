--------------------------- MODULE MoneyTransfer ---------------------------
EXTENDS Integers
VARIABLES accountA, accountB, money, pc
CONSTANTS A,B,M

PositiveInteger(n) == n \in Nat /\ n # 0

(* The "money" is transferred from account A to account B. The "pc" 
   variable is used to control the program flow. Remember, there 
   is no order of execution between "Subtract" and "Add" *)

TypeInvariant == /\ pc \in {"SUB", "ADD", "DONE"}
                 /\ accountA \in Nat
                 /\ accountB \in Nat
                 /\ PositiveInteger(money) \* no zero transfer

Init == /\ accountA = A
        /\ accountB = B
        /\ money = M
        /\ pc = "SUB"

(* The "money" is subtracted from account A if it has enough funds. 
   After that the flow is passed to "Add" by changing "pc" values to
   "ADD". If the balance is not enough, there is no next state, 
   preventing from adding "money" to account B. *)
Subtract == /\ pc = "SUB"
            /\ IF accountA >= money 
                      THEN  accountA' = accountA - money /\ pc' = "ADD"
                      ELSE UNCHANGED <<accountA>> /\ pc' = "DONE"  
            /\ UNCHANGED <<accountB,money>>

(* The "money" is added to account B and after that the flow is 
   changing to "DONE", meaning that there is no next state. *)        
Add == /\ pc = "ADD"        
       /\ accountB' = accountB + money
       /\ pc' = "DONE"
       /\ UNCHANGED <<accountA,money>>

Next == Subtract \/ Add

vars == <<accountA,accountB,money,pc>>

Spec == Init /\ [][Next]_<<vars>>         

=============================================================================

