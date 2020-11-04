package main

// To run the program:
// Install the Go and execute
// `go run AlternateOneBitClock.go`
// on prompt, terminal or PowerShell.

import (
	"fmt"
	"math/rand"
	"strconv"
)

// Generate a random number between 0 and 1,
// both included.
func randomBit() int {
	min := 0
	max := 1
	return (rand.Intn(max-min) + min)
}

// AlternateOneBitClock structure emulates
// the alternate one bit clock.
type AlternateOneBitClock struct {
	clk int
}

//Init willl initialize randomly clock variable clk
//with a value equal to zero or one.
func (a *AlternateOneBitClock) Init() {
	a.clk = randomBit()
	a.Print()
	a.Invariant()
}

// Print the clock value with ' -> ' sign.
func (a *AlternateOneBitClock) Print() {
	fmt.Print(a.clk, " -> ")
}

// Invariant asserts that the clock value has the desired value.
func (a *AlternateOneBitClock) Invariant() {
	if a.clk < 0 || a.clk > 1 {
		msg := "invariant not being held: value " +
			strconv.Itoa(a.clk) + " not expected\n"
		panic(msg)
	}
}

// Next state at each computation.
func (a *AlternateOneBitClock) Next() {
	for {
		if a.clk == 0 {
			a.clk = 1
		} else {
			a.clk = 0
		}
		a.Invariant()
		a.Print()

	}
}

func main() {
	ac := &AlternateOneBitClock{clk: -1}
	ac.Init()
	ac.Next()
}
