package com.example.conf

import io.dropwizard.Configuration

case class AppConfig(title: String,
                     numWidgets: Int) extends Configuration {
}
