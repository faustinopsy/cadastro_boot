package br.senai.sp.informatica.cadastro.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.senai.sp.informatica.cadastro.component.JwtTokenProvider;
import br.senai.sp.informatica.cadastro.model.Usuario;
import br.senai.sp.informatica.cadastro.service.UsuarioService;

public class JwtAutheticationFilter  extends OncePerRequestFilter{
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UsuarioService usuarioService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAutheticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			//retira do header HTTP o token de autenticação
			String jwt=getJwtFromRequest(request);
			//valida o token
			if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				//obtém o userid a partir do token
				String userId=tokenProvider.getUserIdFromJWT(jwt);
				//localiza os dados do usuario do banco de dados
				Usuario usuario = usuarioService.getUsuario(userId);
				//cria o token de autenticação para o ambiente de segurnaca
				UsernamePasswordAuthenticationToken autenticacao =
						new UsernamePasswordAuthenticationToken(usuario, Collections.singletonList(
								usuarioService.getAutorizacoes(userId) ));
				//registra a URL da requisição web no token
				autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//autentica o usuario no ambiente
				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		}catch (Exception erro) {
			logger.error("Não foi possível registrar a Autenticação ", erro);
		}
		
	}
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
	
}
