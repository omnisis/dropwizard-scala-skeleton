package com.example.highroller.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

class DummyHealthCheck extends HealthCheck {

  override def check(): Result = {
    Result.healthy("Let's Roll!")
  }

}
