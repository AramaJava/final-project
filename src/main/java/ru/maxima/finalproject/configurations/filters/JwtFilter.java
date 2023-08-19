package ru.maxima.finalproject.configurations.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.maxima.finalproject.configurations.details.PersonDetails;
import ru.maxima.finalproject.models.Person;
import ru.maxima.finalproject.services.impl.JwtServiceImpl;

import java.io.IOException;

/**
 * @author AramaJava 13.08.2023
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.contains("Bearer ")) {
            String token = authHeader.substring(7);
            if(!token.isBlank()) {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtServiceImpl.SECRET))
                        .build()
                        .verify(token);

                Person person = Person.builder()
                        .email(decodedJWT.getClaim("Email").asString())
                        .role(decodedJWT.getClaim("Role").asString())
                        .name(decodedJWT.getClaim("Name").asString())

                        .build();
                PersonDetails personDetails = new PersonDetails(person);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(person, null, personDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
