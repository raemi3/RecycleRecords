package FinalProject.RecycleRecords.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import FinalProject.RecycleRecords.Services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityCongifuration extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private UsersService usersService;
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        
        return auth;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	//allowing access to folders & specfying login & logout pages
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(
				"/**",
				"/about**",
				"/checkout**",
				"/joinin**",
				"/register**", 
				//"/shoppingcart**",
				"/vinyl**",
					"/static**",
	                "/js**",
	                "/css**",
	                "/assets**"
	                ).permitAll()
		.antMatchers(
				"/viewvinyl/**",
				"/shoppingcart**"
				).hasAuthority("buyer")
		.antMatchers(
				"/addvinyl/**",
				"/editvinyl/**",
				"/deletevinyl/**"
				).hasAuthority("seller")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.permitAll()
		.loginPage("/joinin")
		//.loginProcessingUrl("/perform_login")
		.usernameParameter("email")
		.passwordParameter("password")
		.loginProcessingUrl("/joinin")
		.defaultSuccessUrl("/welcome")
		.failureUrl("/login_error")
		//.failureUrl("/joinin.html?error=true")
		//.permitAll()
		.and()
		.logout()
		.permitAll();
	}

}//class