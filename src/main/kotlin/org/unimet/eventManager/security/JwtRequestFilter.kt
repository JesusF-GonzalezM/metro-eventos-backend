package org.unimet.eventManager.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtRequestFilter(
    private val jwtUtil: JwtUtil,
    private val customUserDetailsService: CustomDetailsService
) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        var username: String? = null
        var jwt: String? = null

        if (authorizationHeader.isNullOrEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response)
            return
        }

        try{
            val jwt = authorizationHeader.substring(7)
            val username = jwtUtil.extractUsername(jwt)

            val authentication = SecurityContextHolder.getContext().authentication

            if (authentication == null) {
                val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(username)

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }
            chain.doFilter(request, response)
        } catch (exception: Exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }



    }
}