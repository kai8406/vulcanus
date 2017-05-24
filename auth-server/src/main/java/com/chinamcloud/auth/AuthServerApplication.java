package com.chinamcloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.chinamcloud.auth.service.security.AuthUserDetailsService;

@SpringBootApplication
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Configuration
	@EnableResourceServer
	class PrincipalRestController extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/", "/home", "/register", "/login.html", "/login").permitAll()
					.anyRequest().authenticated();
		}

	}

	@Configuration
	@EnableWebSecurity
	protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private AuthUserDetailsService authUserDetailsService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();

			http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and()
					.formLogin().loginPage("/login").permitAll().and().logout().permitAll().and().csrf().disable();

			// @formatter:off
			// http.antMatcher("/uaa/**").authorizeRequests().anyRequest().permitAll().and().csrf().disable();
			// @formatter:on
		}

		@Autowired
		protected void registerAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(authUserDetailsService);
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

	}

	/**
	 * 
	 * 授权服务器配置
	 * 
	 * @author Sobey
	 *
	 */
	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

		private TokenStore tokenStore = new InMemoryTokenStore();

		// http://stackoverflow.com/questions/26208087/spring-boot-oauth-2-0-userdetails-user-not-found
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private AuthUserDetailsService authUserDetailsService;

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

			security.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			// 定义了客户端细节服务,客户详细信息可以被初始化.
			clients.inMemory().withClient("vulcanus").secret("vulcanus")
					.authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token")
					.scopes("server");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
					.userDetailsService(authUserDetailsService);
		}

	}

}
