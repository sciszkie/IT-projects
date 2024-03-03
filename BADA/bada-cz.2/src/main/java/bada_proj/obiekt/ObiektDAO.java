package bada_proj.obiekt;

import bada_proj.obiekt.Obiekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ObiektDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ObiektDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Obiekt> list(){
        String sql = "SELECT * FROM OBIEKTY";

        List<Obiekt> listObiekt = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Obiekt.class));
        return listObiekt;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Obiekt obiekt) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("OBIEKTY").usingColumns("Nr_obiektu", "Nazwa", "Pojemnosc", "Nr_klubu", "Nr_adresu");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(obiekt);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Obiekt get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM OBIEKTY WHERE nr_obiektu = " + args[0];
        Obiekt obiekt = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Obiekt.class));
        return obiekt;
    }

    /* Update – aktualizacja danych */
    public void update(Obiekt obiekt) {
        String sql = "UPDATE OBIEKTY SET Nazwa=:Nazwa, Pojemnosc=:Pojemnosc, Nr_klubu=:NrKlubu, Nr_adresu=:NrAdresu WHERE Nr_obiektu=:NrObiektu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(obiekt);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM OBIEKTY WHERE NR_OBIEKTU = ?";
        jdbcTemplate.update(sql, id);
    }
}
