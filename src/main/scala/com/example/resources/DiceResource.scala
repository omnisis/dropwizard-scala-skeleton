package com.example.resources

import javax.ws.rs.core.MediaType
import javax.ws.rs.{QueryParam, Produces, Path, GET}

import com.codahale.metrics.annotation.Timed
import com.example.models.Roll

import scala.util.Random

/**
 * RESTful resource representing dice
 */
@Path("/dice")
@Produces(Array(MediaType.APPLICATION_JSON))
class DiceResource {

  @GET()
  @Path("rollMany")
  @Timed
  def rollMany(@QueryParam("numDice") numDice: Int): Seq[Roll] =  {
    List(Roll(1),Roll(2),Roll(3))
  }

  @GET()
  def rollOne(): Roll =  {
    new Roll(Random.nextInt(6))
  }


}
