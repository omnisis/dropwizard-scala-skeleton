package com.example.highroller.validation

import javax.validation.{ConstraintValidatorContext, ConstraintValidator}

/**
 * A custom JSR-303 constraint validator that checks a scala type (List[Double]) for validity
 */
class DiceProbabilityValidator extends ConstraintValidator[DiceProbabilities, Seq[Double]] {
  override def initialize(constraintAnnotation: DiceProbabilities): Unit = {
    //no-op
  }
  override def isValid(value: Seq[Double], context: ConstraintValidatorContext): Boolean = {
    if(null == value || value.length != 6) {
      context.buildConstraintViolationWithTemplate("value must be an array of length 6")
      false
    }
    else if(Math.abs(1.0-value.sum) >= 0.001) {
      context.buildConstraintViolationWithTemplate("sum(probabilities) must be 1")
      false
    }
    else {
      true
    }
  }
}
