package com.trading.monolith.journal.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trading.monolith.journal.service.AuthenticationUserService;
import com.trading.monolith.journal.utility.JWTUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
    @Autowired
    private JWTUtility jwtutility;
    @Autowired
    private AuthenticationUserService authenticationUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authorization = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if(authorization != null && authorization.startsWith("Bearer")){
            token = authorization.substring(7);
            username = jwtutility.getUsernameFromToken(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = authenticationUserService.loadUserByUsername(username);

            if(jwtutility.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities() 
                        );

                usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                request.setAttribute("email", username);
            }
        }
        filterChain.doFilter(request, response);
        
    }

}
