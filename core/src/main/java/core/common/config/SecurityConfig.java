package core.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String[] PERMIT_URL_ARRAY = {"/api/v1/*"};

	// 암호화에 필요한 PasswordEncoder Bean 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// CSRF 비활성화
			.csrf().disable()
			// 로그인폼 비활성화
			.formLogin().disable()
			// 기본 로그인 창 비활성화
			.httpBasic().disable()
			//허용 url 설정
			.authorizeRequests().antMatchers(PERMIT_URL_ARRAY).permitAll();
	}
}
