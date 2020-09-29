# Money Transfer

[MoneyTransfer.java](MoneyTransfer.java) is the simulation of a money
transfer module not using the specification. In this program, the
control of the program execution flow by using variable `pc` is not
applied and the sequence of operations `Subtract` and `Add` is
non-deterministic because the threads that execute, them wait for some
time randomly assigned.

[MoneyTransferTLA.java](MoneyTransferTLA.java) follows the specification
[MoneyTransfer.tla](MoneyTransfer.tla) and the sequence of execution of
threads is controlled by `pc` variable that must be global (`static`).