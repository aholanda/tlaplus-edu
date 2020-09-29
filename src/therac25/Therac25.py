#!/usr/bin/python3

# @author: Adriano J. Holanda
# @license: public domain

class Therac25:
        XRayIntensity = 25
        # Init ==
        def __init__(self, mode, count, photon = 0):
                self.mode = mode
                self.photon = photon
                self.count = count
                self.target = "Off"
                
                if self.mode == "XRay":
                        self.target = "On"
                        self.photon = Therac25.XRayIntensity
                else:
                        self.target = "Off"

        def Treat(self):
                if self.mode == "Electron":
                        assert(self.target == "Off")
                        assert(self.photon < Therac25.XRayIntensity)
                        
                        self.Count()

                if self.mode == "XRay":
                        assert(self.target == "On")
                        assert(self.photon == Therac25.XRayIntensity)
                        
                        self.Count()

                self.mode = "Done"
                if self.mode == "XRay":
                        self.target = "Off"
                self.photon = 0
                self.Print()

        def Count(self):
                while self.count >= 0:
                        self.Print()
                        self.count -= 1

        def Print(self):
                print("mode={} target={} intensity={} time={} ".
                      format(self.mode, self.target, self.photon, self.count))

modes = ["Electron", "XRay"]               
imode = int(input('Enter with the mode, 0 (Electron) and 1 (X Ray): '))
assert(imode == 0 or imode == 1)

time = int(input('Enter with exposition time: '))
assert(time >0)

if imode == 1:
        therac25 = Therac25(modes[imode], time)
        therac25.Treat()
        exit()

photon = int(input('Enter with the intensity: '))
assert(photon >0 and photon < 25)

therac25 = Therac25(modes[imode], time, photon)
therac25.Treat()
