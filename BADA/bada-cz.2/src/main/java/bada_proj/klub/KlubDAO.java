package bada_proj.klub;

import bada_proj.klub.Klub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KlubDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KlubDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Klub> list(){
        String sql = "SELECT * FROM KLUBY";

        List<Klub> listKlub = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Klub.class));
        return listKlub;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Klub klub) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("KLUBY").usingColumns("Nr_klubu", "Nazwa", "Data_zalozenia", "Nr_adresu");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klub);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Klub get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM KLUBY WHERE nr_klubu = " + args[0];
        Klub klub = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Klub.class));
        return klub;
    }

    /* Update – aktualizacja danych */
    public void update(Klub klub) {
        String sql = "UPDATE KLUBY SET Nazwa=:Nazwa, Data_zalozenia=:DataZalozenia, Nr_adresu=:NrAdresu WHERE Nr_klubu=:NrKlubu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klub);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM KLUBY WHERE NR_KLUBU = ?";
        jdbcTemplate.update(sql, id);
    }

}
