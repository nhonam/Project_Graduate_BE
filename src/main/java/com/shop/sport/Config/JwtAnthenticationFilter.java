package com.shop.sport.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.sport.Service.JwtService;
import com.shop.sport.auth.JwtAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.python.jline.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor

public class JwtAnthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtService jwtService;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        filterChain.doFilter(request, response);

    }



        /*

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "false");

//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "1800");
//        response.setHeader("Access-Control-Allow-Headers", "content-type");
//        response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
//        response.setHeader("Access-Control-Allow-Credentials", "false");
        // res.setHeader("Content-Type", "application/json;charset=utf-8"); // Opening this comment will cause problems


        if (request.getServletPath().contains("/api/v1/auth") ||
                request.getServletPath().contains("/api/v1/product/allProduct") ||
                request.getServletPath().contains("api/v1/product/one/") ||
                request.getServletPath().contains("/api/v1/test") ||
                request.getServletPath().contains("forget-password") ||
                request.getServletPath().contains("category/getAllCategory") ||
                request.getServletPath().contains("product/byCategory") ||
                request.getServletPath().contains("special-management/specials") ||
                request.getServletPath().contains("api/v1/evaluate-management/evaluates/") ||
                request.getServletPath().contains("/api/v1/banner/five") ||
                request.getServletPath().contains("/update")) {
            try {

                filterChain.doFilter(request, response);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // Set the content type to JSON
                response.setContentType("application/json");

                // Create the response body
                String jsonResponse = "{\"message\": \"Unauthorized 1 - Invalid JWT\", \"code\": 401, \"data\": null}";

                // Write the response body
                response.getWriter().write(jsonResponse);

                // Since the response is complete, do not call filterChain.doFilter
//            filterChain.doFilter(request, response);
                return;

                // Since the response is complete, do not call filterChain.doFilter
//            filterChain.doFilter(request, response);

            }

            return;
        }
        System.out.println("-----------------------------");
        System.out.println(request.getHeader("Authorization"));
//        System.out.println(request.getHeader("Authorization"));
        System.out.println("-----------------------------");
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // Set the content type to JSON
            response.setContentType("application/json");

            // Create the response body
            String jsonResponse = "{\"message\": \"Unauthorized - Invalid JWT\", \"code\": 401, \"data\": null}";

            // Write the response body
            response.getWriter().write(jsonResponse);

            // Since the response is complete, do not call filterChain.doFilter
//            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String userEmail = null;
        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // Set the content type to JSON
            response.setContentType("application/json");

            // Create the response body
            String errorMessage = String.format("ERROR: %s", e.getMessage());
            errorMessage = errorMessage.replace("\"", "\\\""); // Escape double quotes for JSON

            // Create the response body with the error message included
            String jsonResponse = String.format("{\"message\": \"%s\", \"code\": 401, \"data\": null}", errorMessage);

            // Write the response body

            // Write the response body
            response.getWriter().write(jsonResponse);

//            authenticationEntryPoint.commence(request, response, new BadCredentialsException("Invalid JWT"));
            return;
        }


        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    // Handle authentication failure with the AuthenticationEntryPoint
                    authenticationEntryPoint.commence(request, response, new BadCredentialsException("Invalid JWT"));
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                // Set the content type to JSON
                response.setContentType("application/json");

                // Create the response body
                String errorMessage = String.format("ERROR: %s", e.getMessage());
                errorMessage = errorMessage.replace("\"", "\\\""); // Escape double quotes for JSON

                // Create the response body with the error message included
                String jsonResponse = String.format("{\"message\": \"%s\", \"code\": 401, \"data\": null}", errorMessage);

                // Write the response body

                // Write the response body
                response.getWriter().write(jsonResponse);
                return;

            }

        }

        try {
            filterChain.doFilter(request, response);
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // Set the content type to JSON
            response.setContentType("application/json");

            // Create the response body
            String errorMessage = String.format("ERROR: %s", e.getMessage());
            errorMessage = errorMessage.replace("\"", "\\\""); // Escape double quotes for JSON

            // Create the response body with the error message included
            String jsonResponse = String.format("{\"message\": \"%s\", \"code\": 401, \"data\": null}", errorMessage);

            // Write the response body

            // Write the response body
            response.getWriter().write(jsonResponse);
            return;
        }

    }


         */

}