package com.example.highroller.core

import javax.validation.constraints.{Max, Min}

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.NotEmpty

/**
 * TODO: case class parameters don't seem to work properly with this but vars do?
 */

case class SwaggerSettings( ) {
  @JsonProperty @NotEmpty(message = "Missing Host in settings") var host: String = _

  @Min(1025) @Max(1<<16) var port: Int = _

}

object SwaggerSettings {
  def apply(h: String, p: Int) : SwaggerSettings = { val s = new SwaggerSettings(); s.host=h; s.port=p; s}
}
