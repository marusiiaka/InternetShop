package com.example.buysell.configurations;

//импортирование классов и библиотек для работы с безопасностью веб-приложения
import com.example.buysell.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //включ поддержку безопасности веб-приложения
@RequiredArgsConstructor //генерирует конструктор с аргументами для полей с аннотацией @NonNull
public class SecurityConfig extends WebSecurityConfigurerAdapter { //настройка безопасности приложения
    private final CustomUserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception { //настройка доступа к URL-адресам
        http.csrf().disable() //отключ защиты от CSRF-атак
                .authorizeRequests()
                .antMatchers("/","**/product/**","/images/**","/registration")
                .permitAll() //разреш доступа всем пользователям к URL-адресам, объявленным в методе antMatchers
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //определяет как прозводится аутентификация пользователей
        auth.userDetailsService(userDetailsService) //загружает информацию о пользователе из бд
                .passwordEncoder(passwordEncoder()); //для шифрования пароля перед сохр в бд
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new  BCryptPasswordEncoder(8); //алгоритм шифрования с коэфф прочности 8
    } //


}
