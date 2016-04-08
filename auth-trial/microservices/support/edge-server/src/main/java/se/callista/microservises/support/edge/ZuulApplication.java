package se.callista.microservises.support.edge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.callista.microservises.support.configuration.CustomOAuth2RestTemplate;

@SpringBootApplication
@Controller
@EnableZuulProxy
@EnableOAuth2Sso
public class ZuulApplication extends WebSecurityConfigurerAdapter {

	UserInfoRestTemplateCustomizer customOauth2Template = new CustomOAuth2RestTemplate();
	
  public static void main(String[] args) {
      new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
  }

  @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.
            authorizeRequests()
                .antMatchers(HttpMethod.GET, "/","/api/**", "/login/**").permitAll()
                .antMatchers("/google_oauth2_login").permitAll()
                .anyRequest().authenticated();
                
                
        // @formatter:on
    }

  @RequestMapping("/login")
    @ResponseBody
    String home() {
        return "Hello World";
    }
}
