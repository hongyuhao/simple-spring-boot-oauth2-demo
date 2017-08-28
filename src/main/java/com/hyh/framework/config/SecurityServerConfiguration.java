package com.hyh.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置资源服务器和授权服务器
 * @author hyhsoftware@163.com
 * @version 0.0.2
 */
public class SecurityServerConfiguration {

    /**
     * 定义资源id
     */
    private static final String RESOURCE_ID = "rest-api";

    /**
     * 配置资源服务器
     */
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            /**
             * 配置待授权的资源链接，可使用antMatchers配置多个
             */
            http.authorizeRequests().antMatchers("/api/**").authenticated();
        }

    }

    /**
     * 配置授权服务器
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        private TokenStore tokenStore;
        @Resource
        private DataSource dataSource;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            /**
             * 配置jdbc令牌策略
             */
            tokenStore = new JdbcTokenStore(dataSource);
            endpoints.tokenStore(tokenStore);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            /**
             * 设置验证客户端的数据源，需要跟tokenservice一样
             */
            clients.jdbc(dataSource);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            /**
             * 开启客户端模式，默认客户端授权模式不开启，可根据个人习惯决定是否开启。
             * 如果不开启，出现的现象是请求oauth/token服务器返回了401，需要用http://client_id:client_secret@xxx.com/oauth/token?grant_type=client_credentials的方式获取token
             * 开启的效果是，访问url:http://xxx.com/oauth/token?grant_type=client_credentials&client_id=xxx&client_secret=xxx （这种方式比较符合常规的url规则）
             */
            security.allowFormAuthenticationForClients();
        }

        /**
         * 定义tokenService，注意这里的tokenStore需要跟endpoints.tokenStore使用同一个，否则会出现token对象不一致，获取不到access_token的问题
         * @return
         */
        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(this.tokenStore);
            return tokenServices;
        }

    }

}
