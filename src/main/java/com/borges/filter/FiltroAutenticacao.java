package com.borges.filter;

import com.borges.models.Usuario;
import com.borges.services.AutenticacaoService;
import com.borges.services.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {


    private final AutenticacaoService autenticacaoService;
    private final UsuarioService usuarioService;

    public FiltroAutenticacao(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    private final static String HEADER_ATRIBUTO = "Authorization";
    private final static String ATRIBUTO_PREFIXO = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_ATRIBUTO);
        String token = null;
        if(header != null && header.startsWith(ATRIBUTO_PREFIXO)) {
            token = header.substring(7, header.length());
        }

        if(autenticacaoService.verificarToken(token)) {
            Long idUsuario = autenticacaoService.retornarIdUsuario(token);
            Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
        }

        filterChain.doFilter(request, response);
    }
}
