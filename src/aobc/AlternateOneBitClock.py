#!/usr/bin/env python3

# To run the program:
# Install the Python3 and execute
# `python3 AlternateOneBitClock.py`
# on prompt, terminal or PowerShell.

import random

class AlternateOneBitClock():
	'''Class to emulate the alternate one bit clock.
	'''	
	def __init__(self):
		'''Initialize randomly clock variable clk
		with a value equal to zero or one.
		'''
		self.clk = random.randint(0,1)
		self.Print()
		self.Invariant()

	def Print(self):
		'''Print the clock value with ' -> ' sign.
		'''
		print(self.clk, end=' -> ')		

	def Invariant(self):
		'''Assert the clock value has the desired value.
		'''
		assert self.clk == 0 or self.clk == 1

	def Next(self):
		'''Next state at each computation.
		'''
		while True:
			if self.clk == 0:
				self.clk = 1
			else:
				self.clk = 0
			
			self.Invariant()

			self.Print()

if __name__ == '__main__':
		ac = AlternateOneBitClock()
		ac.Next()
