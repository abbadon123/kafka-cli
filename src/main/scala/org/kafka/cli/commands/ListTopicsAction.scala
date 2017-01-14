package org.kafka.cli.commands

import org.kafka.cli.{CommandLineActionFactory, CommandLineAction}
import scopt.OptionParser

/**
 * @author Natalia Gorchakova
 * @since  08.01.2017
 */
class ListTopicsAction(val config: ListTopicsActionConfig) extends CommandLineAction {
  override def perform() = println(s"list topics for ${config.bootstrapServer}")
}

private[commands] case class ListTopicsActionConfig(bootstrapServer: String = null)

object ListTopicsAction extends CommandLineActionFactory {

  val Parser: OptionParser[ListTopicsActionConfig] = new OptionParser[ListTopicsActionConfig]("list all topics") {
    opt[String]('b', "bootstrap").required().action((s, c) =>
      c.copy(bootstrapServer = s)).text("bootstrap service list")
  }

  override def createAction(args: Seq[String]): Option[CommandLineAction] = {

    Parser.parse(args, new ListTopicsActionConfig()) match {
      case Some(config) => Some(new ListTopicsAction(config))
      case None => None
    }
  }
}