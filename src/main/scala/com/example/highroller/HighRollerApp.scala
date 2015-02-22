package com.example.highroller

import com.example.highroller.health.DummyHealthCheck
import com.example.highroller.resources.DiceResource
import com.example.highroller.services.DiceRollingEngine
import com.example.highroller.util.CORSBundle
import com.massrelevance.dropwizard.ScalaApplication
import com.massrelevance.dropwizard.bundles.ScalaBundle
import io.dropwizard.setup.{Bootstrap, Environment}
import io.federecio.dropwizard.swagger.{SwaggerBundle, SwaggerBundleConfiguration}


object HighRollerApp extends ScalaApplication[HighRollerConfiguration] {
  private var appName = "High Roller"

  override def getName: String = appName

  override def initialize(bootstrap: Bootstrap[HighRollerConfiguration]): Unit = {

    bootstrap.addBundle(new CORSBundle)
    bootstrap.addBundle(new ScalaBundle)
    bootstrap.addBundle(new SwaggerBundle[HighRollerConfiguration]() {
      override def getSwaggerBundleConfiguration(configuration: HighRollerConfiguration): SwaggerBundleConfiguration = {
        new SwaggerBundleConfiguration(configuration.swagger.host, configuration.swagger.port)
      }
    })

  }

  override def run(conf: HighRollerConfiguration, env: Environment): Unit = {
    this.appName = conf.appName

    //println(conf)
    env.getValidator.validate(conf)

    // register some resources
    val diceEngine = new DiceRollingEngine(conf.diceEngine)
    env.jersey().register(new DiceResource(diceEngine))

    // healthchecks
    env.healthChecks().register("diceEngine", new DummyHealthCheck)
  }
}
