package com.example.highroller.services

import com.example.highroller.core.{DiceEngineSettings, Roll}

import scala.util.Random

class DiceRollingEngine(@volatile var settings: DiceEngineSettings) {

  private[this] val breakPoints ={
    var sum = 0.0
    for(p <- settings.weights) yield { sum += p; sum }
  }

  private[this] def simulateRoll() : Int = {
    val rnd = Random.nextDouble()-0.0001
    val res = breakPoints.zipWithIndex.find( _._1 >= rnd).get._2
    assert(res >= 0 && res <= 5)
    res
  }

  /**
   * Rolls a weighted die according to current weights for either the default
   * number of dice or the value of times if given.
   */
  def roll(n : Int = settings.numDice) : Seq[Roll] = {
    for(i <- 1.to(n)) yield {
      val r = simulateRoll()
      Roll(r+1, settings.weights(r))
    }
  }

  def isFair : Boolean = {
    settings.weights.forall(w => Math.abs(w-1/6.0) <= 0.001 )
  }

}
