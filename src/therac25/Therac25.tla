------------------------------ MODULE Therac25 ------------------------------
EXTENDS Naturals
VARIABLES count,pc,target,photon
CONSTANT Intensity, Time, Mode

(* X ray intensity is set to a constant 25 MeV *)
XRayIntensity == 25

(* In "Electron" mode the electron beam intensity in lesser than
   the current in "XRay" mode. *)
TypeInvariant == /\ Time \in Nat /\ Time # 0
                 /\ Mode \in {"Electron", "XRay", "Done"}
                 /\ Intensity <= XRayIntensity

(* The "count" variable is decreased at each step and it stops
    when it is zeroed. In the  "XRay" mode, the target must be 
    placed in the path of the beam to act as a filter and 
    collimator. Int the "Electron" mode, the target is not 
    needed due the low 'lightning' intensity. *)                                               
Init == /\ count = Time
        /\ pc = Mode
        /\ IF pc = "XRay" THEN /\ photon = XRayIntensity
                               /\ target = "On"
                          ELSE /\ photon = Intensity
                               /\ target = "Off"
                                                              
(* Electron mode *)
Treat1 == /\ pc = "Electron"
          /\ target = "Off"
          /\ IF count = 0 THEN  /\ pc' = "Done"
                                /\ photon' = 0
                                /\ UNCHANGED <<target>>
                           ELSE /\ count' = count - 1
                                /\ UNCHANGED <<pc,photon,target>>          
(* XRay mode *)
Treat2 == /\ pc = "XRay"
          /\ target = "On"  \* Confirm that the target is activated.
          /\ IF count = 0 THEN  /\ pc' = "Done"
                                /\ photon' = 0
                                /\ target' = "Off"
                           ELSE /\ count' = count - 1
                                /\ UNCHANGED <<pc,photon,target>>
                                
Next == Treat1 \/ Treat2

vars == <<count,pc,target,photon>>

Spec == Init /\ [][Next]_<<vars>>                                
=============================================================================

