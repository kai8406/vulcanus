package com.chinamcloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.chinamcloud.auth.service.security.AuthUserDetailsService;

@SpringCloudApplication
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@Configuration
	@EnableWebSecurity
	protected static class webSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private AuthUserDetailsService userDetailsService;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
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

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Autowired
		private AuthUserDetailsService authUserDetailsService;

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

			// 在令牌端点上定义了安全约束
			security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		}

		/**
		 * 
		 * 
		 * clientId：（必须）客户端id。
		 * 
		 * secret：（对于可信任的客户端是必须的）客户端的私密信息。
		 * 
		 * scope：客户端的作用域。如果scope未定义或者为空（默认值），则客户端作用域不受限制。
		 * 
		 * authorizedGrantTypes：授权给客户端使用的权限类型。默认值为空。
		 * 
		 * authorities：授权给客户端的权限（Spring普通的安全权限）。
		 * 
		 */
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			// 定义了客户端细节服务,客户详细信息可以被初始化.
			clients.inMemory().withClient("browser").authorizedGrantTypes("refresh_token", "password").scopes("ui")
					.and().withClient("account-service").secret("account-service")
					.authorizedGrantTypes("client_credentials", "refresh_token").scopes("server");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

			// 定义了授权和令牌端点和令牌服务
			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
					.userDetailsService(authUserDetailsService);

		}

	}

}
