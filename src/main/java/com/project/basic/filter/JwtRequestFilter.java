package com.project.basic.filter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mysql.cj.util.StringUtils;
import com.project.basic.service.SignService;
import com.project.basic.utils.JwtUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{

    private static final String BEARER_START = "Bearer ";

    private SignService signService;

    private static final List<String> EXCLUDE_URL =
    Collections.unmodifiableList(
            Arrays.asList("/api/sign"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        try {
            String bearer = request.getHeader("Authorization");
            if(StringUtils.startsWithIgnoreCase(bearer, BEARER_START)){
                String token = bearer.substring(BEARER_START.length());
                UserDetails userDetails = null;
                Map<String, Object> userData = JwtUtil.getData(token);
                
                if(userData == null){
                    request.setAttribute("unauthorization", "401 인증키 없음.");
                }else{
                    userDetails = signService.loadUserByUsername((String) userData.get("email"));
                    UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
                        
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);               
                }

              

             

            }else{
                request.setAttribute("authorization", "Please Check Auth Key");
            }
        } catch (Exception ex) {
            logger.error("Wrong Token ", ex);
        } 

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        boolean result = EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
        return result;
    }
}
