package com.example.highroller.util

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.Module.SetupContext
import com.fasterxml.jackson.databind.{AbstractTypeResolver, DeserializationConfig, JavaType, Module}

class MyAppJsonModule extends Module {
  override def getModuleName: String = "MyApp"

  override def setupModule(setupContext: SetupContext): Unit = {
    setupContext.addAbstractTypeResolver(new AbstractTypeResolver {
      override def resolveAbstractType(config: DeserializationConfig, `type`: JavaType): JavaType = super.resolveAbstractType(config, `type`)
    })

  }

  override def version(): Version = Version.unknownVersion()

}
