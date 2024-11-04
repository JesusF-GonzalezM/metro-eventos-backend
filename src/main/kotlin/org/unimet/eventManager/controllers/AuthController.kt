package org.unimet.eventManager.controllers

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.security.JwtUtil


@RestController
@RequestMapping("/api")
class AuthController (
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
){

    @PostMapping("/login")
    @Throws(Exception::class)
    fun loginUser(@RequestBody authenticationRequest: UserDTO): String {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
        return jwtUtil.generateToken(userDetails)
    }
}
