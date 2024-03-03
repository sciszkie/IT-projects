package bada_proj.dyscyplina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DyscyplinaDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DyscyplinaDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Dyscyplina> list(){
        String sql = "SELECT * FROM DYSCYPLINY";

        List<Dyscyplina> listDyscyplina = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dyscyplina.class));
        return listDyscyplina;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Dyscyplina dyscyplina) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("DYSCYPLINY").usingColumns("Nr_dyscypliny", "Nazwa_dyscypliny");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dyscyplina);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Dyscyplina get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM DYSCYPLINY WHERE nr_dyscypliny = " + args[0];
        Dyscyplina dyscyplina = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Dyscyplina.class));
        return dyscyplina;
    }

    /* Update – aktualizacja danych */
    public void update(Dyscyplina dyscyplina) {
        String sql = "UPDATE DYSCYPLINY SET Nazwa_dyscypliny=:NazwaDyscypliny WHERE Nr_dyscypliny=:NrDyscypliny";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dyscyplina);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM DYSCYPLINY WHERE NR_DYSCYPLINY = ?";
        jdbcTemplate.update(sql, id);
    }
}
