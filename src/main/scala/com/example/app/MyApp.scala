package com.example.app

import com.example.conf.AppConfig
import com.example.resources.DiceResource
import com.massrelevance.dropwizard.ScalaApplication
import com.massrelevance.dropwizard.bundles.ScalaBundle
import io.dropwizard.setup.{Bootstrap, Environment}

object MyApp extends ScalaApplication[AppConfig] {
  override def initialize(bootstrap: Bootstrap[AppConfig]): Unit = {
    bootstrap.addBundle(new ScalaBundle)

  }

  override def run(t: AppConfig, env: Environment): Unit = {
    env.jersey().register(new DiceResource)
  }
}
