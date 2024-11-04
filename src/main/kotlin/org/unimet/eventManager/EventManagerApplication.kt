package org.unimet.eventManager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
@EnableMongoRepositories
class EventManagerApplication{

	@Bean
	fun bCryptPasswordEncoder() = BCryptPasswordEncoder()

	@Bean
	fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
		config.authenticationManager

	@Bean
	fun authenticationProvider(userDetailsService: UserDetailsService): AuthenticationProvider {
		val authProvider = DaoAuthenticationProvider()

		authProvider.setUserDetailsService(userDetailsService)
		authProvider.setPasswordEncoder(bCryptPasswordEncoder())

		return authProvider
	}
}

fun main(args: Array<String>) {
	runApplication<EventManagerApplication>(*args)
}
