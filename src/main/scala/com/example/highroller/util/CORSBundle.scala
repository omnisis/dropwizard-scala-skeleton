package com.example.highroller.util

import java.util
import javax.servlet.DispatcherType

import io.dropwizard.Bundle
import io.dropwizard.setup.{Bootstrap, Environment}
import org.eclipse.jetty.servlets.CrossOriginFilter

class CORSBundle extends Bundle {
  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(environment: Environment): Unit = {
    val filter  = environment.servlets().addFilter("CORS", classOf[CrossOriginFilter])
    // TODO: Could Make urlPattern/allow origin list configurable
    filter.addMappingForUrlPatterns(util.EnumSet.allOf(classOf[DispatcherType]), true, "/*")
    filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS,HEAD")
    filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*")
    filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*")
    filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin")
    filter.setInitParameter("allowCredentials", "true")
  }
}
