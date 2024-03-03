package bada_proj.kibic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KibicDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public KibicDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Kibic> list(){
        String sql = "SELECT * FROM KIBICE ORDER BY Nr_kibica";
        List<Kibic> listKibic = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kibic.class));
        return listKibic;
    }
    public List<Kibic> findByLogin(String login) {
        String sql = "SELECT * FROM KIBICE WHERE login = ?";
        List<Kibic> listKibic = jdbcTemplate.query(sql, new Object[]{login}, BeanPropertyRowMapper.newInstance(Kibic.class));
        return listKibic;
    }
    public Kibic getKibicByLogin(String login) {
        Object[] args = {login};
        try {
            String sql = "SELECT * FROM KIBICE WHERE login = ?";
            Kibic kibic = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Kibic.class));
            return kibic;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public int isLoginAlreadyExists(String login) {
        String sql = "SELECT COUNT(*) FROM KIBICE WHERE login = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, login);
        return count;
    }
    public boolean isOldPasswordCorrect(String login, String insertedPassword){
        Object[] args = {login};
        String sql = "SELECT * FROM KIBICE WHERE login = ?";
        try {
            Kibic kibic = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Kibic.class));

            if (passwordEncoder.matches(insertedPassword, kibic.getHaslo())) {
                return true;
            } else {
                return false;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Kibic kibic) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("KIBICE").usingColumns("Nr_kibica", "Imie", "Nazwisko", "Data_urodzenia", "PESEL", "Nr_telefonu", "Email", "Plec", "Nr_adresu","Haslo","Login");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kibic);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Kibic get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM KIBICE WHERE Nr_kibica = ?";
        Kibic kibic = jdbcTemplate.queryForObject(sql, args,BeanPropertyRowMapper.newInstance(Kibic.class));
        return kibic;
    }
    /* Update – aktualizacja danych */
    public void update(Kibic kibic) {
        String sql = "UPDATE KIBICE SET Imie=:imie, Nazwisko=:nazwisko, Data_urodzenia=:dataUrodzenia, PESEL=:pesel, Nr_telefonu=:nrTelefonu, Email=:email, Plec=:plec, Nr_adresu=:nrAdresu,Login=:login WHERE Nr_kibica=:nrKibica";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kibic);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    public void updatePassword(Kibic kibic) {
        String sql = "UPDATE KIBICE SET Haslo=:haslo WHERE Nr_kibica=:nrKibica";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kibic);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM KIBICE WHERE NR_KIBICA = ?";
        jdbcTemplate.update(sql, id);
    }
}
