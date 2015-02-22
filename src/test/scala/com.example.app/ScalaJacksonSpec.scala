package com.example.app

import com.example.highroller.core.SwaggerSettings
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.scalatest.{FunSpec, Matchers}

class ScalaJacksonSpec extends FunSpec with Matchers {

  describe("Collection Behavior") {
    val confList = List(SwaggerSettings("localhost", 1234), SwaggerSettings("localhost", 5432))
    val om = new ObjectMapper() with ScalaObjectMapper
    om.registerModule(new DefaultScalaModule)

    describe("Collection <-> JSON") {
      it("should marshall lists of pojos") {
        val json = om.writeValueAsString(confList)
        json shouldBe "[{\"host\":\"localhost\",\"port\":1234},{\"host\":\"localhost\",\"port\":5432}]"
        val fromJson : List[SwaggerSettings] = om.readValue(json, new TypeReference[List[SwaggerSettings]]{})
        fromJson shouldEqual confList
      }
      it("should marshall lists of scala case classes") {
        val json = om.writeValueAsString(List[SwaggerSettings](SwaggerSettings("localhost", 1234)))
        // must use type ref to avoid getting back List[Map[_,_]]
        val fromJson : List[SwaggerSettings] = om.readValue(json, new TypeReference[List[SwaggerSettings]]{})
        fromJson shouldBe List(SwaggerSettings("localhost",1234))
      }
      it("should marshall scala maps of objects") {
        val m = Map[String, SwaggerSettings]("c1" -> confList(0), "c2" -> confList(1))
        val json = om.writeValueAsString(m)
        json shouldBe """{"c1":{"host":"localhost","port":1234},"c2":{"host":"localhost","port":5432}}"""
      }
      it("should marshall java maps of objects") {
        val m = new java.util.HashMap[String,SwaggerSettings]()
        m.put("c1", confList(0))
        m.put("c2", confList(1))
        val json = om.writeValueAsString(m)
        json shouldBe """{"c1":{"host":"localhost","port":1234},"c2":{"host":"localhost","port":5432}}"""
      }
      it("should marshall lists as seqs") {
        val confSeq : Seq[SwaggerSettings] = confList
        val json = om.writeValueAsString(confSeq)
        json shouldBe """[{"host":"localhost","port":1234},{"host":"localhost","port":5432}]"""
      }
    }

  }
}
