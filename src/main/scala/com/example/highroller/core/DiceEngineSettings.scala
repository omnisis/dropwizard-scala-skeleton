package com.example.highroller.core

import com.example.highroller.validation.DiceProbabilities
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.Objects

/**
 * A custom Scala case class that is used in the context of custom configuration, queryParams and validation.
 */
class DiceEngineSettings {

  @JsonProperty
  var numDice                                       :  Int = 2

  @DiceProbabilities() @JsonProperty()
  var weights                                       : Seq[Double] = _

  override def toString: String = {
    Objects.toStringHelper(this)
      .omitNullValues()
      .add("numDice", numDice)
      .add("weights", weights)
      .toString
  }
}


object DiceEngineSettings {
  def apply(numDice: Int, wts : Seq[Double]) : DiceEngineSettings = {
    val s = new DiceEngineSettings()
    s.numDice = numDice
    s.weights = wts
    s
  }
}

