package bada_proj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$AR9VjlMBLbyaa0c0TCStyebkpu3LwKeqVQjmjE.lIDPk3o/9Hf1dG")
                .roles("ADMIN");

        auth.jdbcAuthentication()
                .dataSource(jdbcTemplate.getDataSource())
                .usersByUsernameQuery("SELECT login, haslo, 1 FROM KIBICE WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login, 'ROLE_USER' FROM KIBICE WHERE login = ?")
                .passwordEncoder(passwordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").authenticated()


                //main
                .antMatchers("/main_admin").access("hasRole('ADMIN')")
                .antMatchers("/main_user").access("hasRole('USER')")

                //kibic
                .antMatchers("/kibice").hasAnyRole("ADMIN", "USER")
                .antMatchers("/newKibic").hasRole("ADMIN")
                .antMatchers("/editKibic/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/password_change_user/{id}").hasAnyRole("ADMIN", "USER")

                //adres
                .antMatchers("/adresy").hasAnyRole("ADMIN", "USER")
                .antMatchers("/editAdres/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers("/newAddress").access("hasRole('ADMIN')")

                //obiekty
                .antMatchers("/obiekty").hasRole("ADMIN")
                .antMatchers("/newObiekt").hasRole("ADMIN")
                .antMatchers("/editObiekt/{id}").hasRole("ADMIN")


                //wydarzenia
                .antMatchers("/newWydarzenie").hasRole("ADMIN")
                .antMatchers("/editWydarzenie/{id}").hasRole("ADMIN")
                .antMatchers("/wydarzenia").hasAnyRole("ADMIN", "USER")

                //dyscypliny
                .antMatchers("/dyscypliny").hasRole("ADMIN")
                .antMatchers("/newDysycyplina").hasRole("ADMIN")
                .antMatchers("/editDyscyplina/{id}").hasRole("ADMIN")

                //klub
                .antMatchers("/kluby").hasRole("ADMIN")
                .antMatchers("/newKlub").hasRole("ADMIN")
                .antMatchers("/editKlub/{id}").hasRole("ADMIN")

                //kibicewydarzenia
                .antMatchers("/kibiceWydarzenia").hasAnyRole("ADMIN", "USER")
                .antMatchers("/newKibicWydarzenie").access("hasRole('ADMIN')")
                .antMatchers("/editKibicWydarzenie/{nrWydarzenia}/{nrKibica}").access("hasRole('ADMIN')")


                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();

    }
}