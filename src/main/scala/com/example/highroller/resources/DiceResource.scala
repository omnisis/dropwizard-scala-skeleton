package com.example.highroller.resources

import javax.validation.Valid
import javax.ws.rs._
import javax.ws.rs.core.MediaType

import com.codahale.metrics.annotation.Timed
import com.example.highroller.core.{DiceEngineSettings, Roll}
import com.example.highroller.services.DiceRollingEngine
import com.wordnik.swagger.annotations.{ApiParam, ApiOperation, Api}

/**
 * RESTful resource showing off:
 *   - JAX-RS Resource Method/Path binding
 *   - Automatic marshalling of Scala Case Classes to JSON results
 *   - Automatic binding of Scala Classes to JAX-RS Query Params
 */
@Path("/dice")
@Produces(Array(MediaType.APPLICATION_JSON))
@Api(value = "/dice",
  description = "A weighted Dice Rolling Service",
  produces = MediaType.APPLICATION_JSON,
  consumes = MediaType.APPLICATION_JSON
)
class DiceResource(diceEngine : DiceRollingEngine) {

  @GET()
  @Path("rollMany")
  @Timed
  @ApiOperation(value = "Roll a sequence of equally weighted (though not necessarily fair) dice at once",
    notes = "Each die is equally waited according to the current DiceEngineSettings")
  def rollMany(@ApiParam(value = "Number of dice to roll", required = false, defaultValue = "2") @QueryParam("numDice") numDice: Int = 2): Seq[Roll] =  {
    diceEngine.roll(numDice)
  }

  @GET()
  @Timed
  @ApiOperation(value = "Roll a single weighted die")
  def rollOne(): Roll =  {
    diceEngine.roll(1).head
  }

  @GET()
  @Path("settings")
  @ApiOperation(value = "Retrieves the current DiceEngine Settings")
  def currSettings(): DiceEngineSettings = {
    diceEngine.settings
  }

  @PUT()
  @Path("updateEngineConf")
  @ApiOperation(value = "Updates the DiceEngine Settings")
  def updateEngineConf(@ApiParam(value="New DiceEngineSettings to use", required = true) @Valid conf: DiceEngineSettings) : String = {
    diceEngine.settings = conf
    "Engine Config Updated"
  }


}
