package org.unimet.eventManager.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.JwtResponse
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.User
import org.unimet.eventManager.repositories.UserRepository
import org.unimet.eventManager.security.JwtUtil


@RestController
@RequestMapping("/auth")
class AuthController (
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JwtUtil
){

    @PostMapping("/login")
    fun loginUser(@RequestBody authenticationRequest: UserDTO): ResponseEntity<JwtResponse> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val token = jwtUtil.generateToken(userDetails)
        val response = JwtResponse(token)
        return ResponseEntity.ok(response)
    }
}
