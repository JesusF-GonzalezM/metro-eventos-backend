package org.unimet.eventManager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class EventManagerApplication

fun main(args: Array<String>) {
	runApplication<EventManagerApplication>(*args)
}
