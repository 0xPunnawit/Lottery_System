package com.punnawit.Lottery_System.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.punnawit.Lottery_System.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                DecodedJWT decodedJWT = tokenUtil.verify(token);

                if (decodedJWT != null) {
                    String userId = decodedJWT.getSubject();
                    String role = decodedJWT.getClaim("role").asString();

                    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userId, null, Collections.singletonList(authority));

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (JWTVerificationException e) {
                // ถ้า token หมดอายุ หรือไม่ถูกต้อง ส่ง HTTP Status 401 Unauthorized
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired or is invalid");
            } catch (Exception e) {
                // ถ้า token ไม่ถูกต้อง ส่ง HTTP Status 401 Unauthorized
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            }
        }

        filterChain.doFilter(request, response);
    }
}
