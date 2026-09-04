package com.rahim.syntopicalnotes.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rahim.syntopicalnotes.domains.dto.auth.UserPrincipal;
import com.rahim.syntopicalnotes.services.auth.JWTService;
import com.rahim.syntopicalnotes.services.auth.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final MyUserDetailsService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
        System.out.println("Running jwt filter");
        
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
           filterChain.doFilter(request, response); 
           return;
        }

        String token = authHeader.substring(7);

        try {
            Long userId = jwtService.extractSubjectFromJwtToken(token);

            if(userId == null || SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserPrincipal principal = (UserPrincipal) userDetailService.loadUserById(userId);
            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
            System.out.println(principal.getUsername());

        }catch(Exception e) {
            System.err.println(e.getMessage());
            SecurityContextHolder.clearContext();
        }

        System.out.println("test");

        filterChain.doFilter(request, response);
	}

    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }

}

