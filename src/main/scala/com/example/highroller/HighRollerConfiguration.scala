package com.example.highroller

import javax.validation.Valid

import com.example.highroller.core.{DiceEngineSettings, SwaggerSettings}
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.Objects
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty


class HighRollerConfiguration extends Configuration {

  @JsonProperty("appName") @NotEmpty  var appName: String = _ //  config propName differs from actual name

  @Valid @JsonProperty("diceEngine")  var diceEngine : DiceEngineSettings = _ // nested complex configs

  @Valid @JsonProperty("swagger")  var swagger : SwaggerSettings = _

  override def toString: String = {
    Objects.toStringHelper(this)
      .add("appName", appName)
      .add("server", getServerFactory)
      .add("diceEngine", diceEngine)
      .add("swagger", swagger)
      .toString
  }
}
