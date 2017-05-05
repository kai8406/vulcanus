package com.chinamcloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
		private AuthenticationManager authenticationManager;

		@Autowired
		private AuthUserDetailsService authUserDetailsService;

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

			// 在令牌端点上定义了安全约束
			// security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
			security.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

			// 定义了客户端细节服务,客户详细信息可以被初始化.
			clients.inMemory().withClient("vulcanus").secret("vulcanus")
					.authorizedGrantTypes("client_credentials", "password", "refresh_token").scopes("server");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

			// 定义了授权和令牌端点和令牌服务
			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
					.userDetailsService(authUserDetailsService);
		}

	}

}
