#!/usr/bin/python3
import sys
from threading import Thread

# @license: public domain
# See ExperimentControl.tla for description
class ExperimentControl:
        # Init ==
        def __init__(self, init_temp=20, low_temp=0, high_temp=50, delta=5):
                self.pc = "HEAT"
                self.power = "ON"
                self.freeze = "NO"
                self.temperature = int(init_temp)
                self.Low = int(low_temp)
                self.High = int(high_temp)
                self.delta = int(delta)
                self.phase = 1

                assert(self.Low < self.High)
                assert(self.temperature < self.High)

                # run Heat and Freeze simultaneously
                thread1 = Thread(target=self.Heat, args=())
                thread2 = Thread(target=self.Freeze, args=())
                thread2.start()
                thread1.start()
                thread2.join()
                thread1.join()
                
        def Heat(self):
                while True:
                        if self.pc == "DONE":
                                break
                        
                        if self.pc == "HEAT":
                                if self.temperature >= self.High:
                                        self.freeze = "YES"
                                        self.power = "OFF"
                                        self.pc = "FREEZE"
                                        self.phase += 1
                                else:
                                        self.temperature += self.delta

                                print(self.pc, self.temperature)
                        
        def Freeze(self):
                #assert(self.pc == "FREEZE")
                while True:
                        if self.pc == "DONE":
                                break
                        
                        if self.pc == "FREEZE":
                                if self.temperature <= self.Low:
                                        self.freeze = "YES"
                                        
                                        if self.phase != 4:
                                                self.power = "ON"
                                                self.pc = "HEAT"
                                                self.phase +=1
                                        else:
                                                self.pc = "DONE"
                                else:
                                        self.temperature -= self.delta

                                print(self.pc, self.temperature)

# Run with default parameters, without ask
# for initial values
if len(sys.argv) == 2 and sys.argv[1] == '-0':
        ec = ExperimentControl()
else:   
        init_temp = input("Enter the initial temperature: ")
        low_temp = input("Enter the lower temperature: ")
        higher_temp = input("Enter the higher temperature: ")
        delta = input("Enter the amount temperature to increase or decrease the temperature: ")
        ec = ExperimentControl(init_temp, low_temp, higher_temp, delta)
        
