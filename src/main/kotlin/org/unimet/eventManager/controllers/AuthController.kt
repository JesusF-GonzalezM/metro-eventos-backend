package org.unimet.eventManager.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.AuthenticationRequest
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
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
){

    @CrossOrigin(origins = ["*"])
    @PostMapping("/login")
    fun loginUser(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<Any> {
        return try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authenticationRequest.email,
                    authenticationRequest.password
                )
            )
            val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
            val token = jwtUtil.generateToken(userDetails)

            val email = userDetails.username
            val authority = userDetails.authorities.firstOrNull()?.authority ?: "No Authority"

            val response = mapOf(
            "token" to token,
            "email" to email,
            "role" to authority
            )
            //val response = JwtResponse(token)
            ResponseEntity.ok(response)
        } catch (ex: BadCredentialsException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password")
        }
    }

    @CrossOrigin(origins = ["*"])
   @PostMapping("/signup")
    fun createUser(
        @RequestBody userDTO: UserDTO): ResponseEntity<User> {

//        if (userRepository.existByEmail(userDTO.email)) {
//            throw RuntimeException("User already exists")
//        }

        val user = User(
            email=userDTO.email,
            password=passwordEncoder.encode(userDTO.password),
            name=userDTO.name,
            lastName=userDTO.lastName,
            role=userDTO.role,
            gender=userDTO.gender ?: null,
            profilePhoto = userDTO.profilePhoto ?: null,
            birthDate = userDTO.birthDate ?: null
        )

        val savedUser = userRepository.save(user)
        return ResponseEntity.ok(savedUser)// probando responseEntity return
    }
}
