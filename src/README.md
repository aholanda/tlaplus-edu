# Formal Methods

This directory contains specifications written using formal language
[TLA+](https://lamport.azurewebsites.net/tla/tla.html) and their
implementation using some programming language of choice. The content
is described as follows:

- [aobc](aobc/): specification of an alternate one bit clock that alternates
  its value between 0 and 1.

- [ec](ec/): an physical conductivity experiment controller is
  specified in a higher level of abstraction to not concern with
  instrumentation details.

- [gcd](gcd/): specification of Euclid algorithm to find the greatest common
  divisor of two integers greater than zero.

- [hc](hc/): specification of a clock that is concerned only with hour.

- [hmc](hmc/): specification of a clock that is concerned with hour and
  minute.

- [mt](mt/): specification of a module to transfer money from an
  account A to an account B, not allowing negative balance and
  handling only integer values to simplify the abstraction.

- [therac25](therac25/): simple view of a solution for the
  [Therac-25](https://en.wikipedia.org/wiki/Therac-25) software
  problem that injury at least six patients. The specification
  is simplified but gives a good insight how the formal method
  can help in the reasoning.

PS: There is a pretty-print in pdf format of the TLA+ specification in
each directory. The TLA Toolbox can be dowloaded
[here](https://tla.msr-inria.inria.fr/tlatoolbox/products/). Sugestions
and errors found are very welcome, please open an
[issue](https://github.com/ajholanda/edu-softeng/issues) to allow
broader communication.