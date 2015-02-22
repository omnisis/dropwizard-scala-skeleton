package com.example.highroller.core

/**
 * Trivial example of a domain object that happens to be a Scala case class.  Models
 * a single roll of a weighted die.
 */
case class Roll(diceVal: Int, weight: Double = 1/6.0) { }
