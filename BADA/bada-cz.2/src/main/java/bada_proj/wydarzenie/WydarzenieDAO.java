package bada_proj.wydarzenie;

import bada_proj.kibic.Kibic;
import bada_proj.klub.Klub;
import bada_proj.obiekt.Obiekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class WydarzenieDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WydarzenieDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Wydarzenie> list(){
        String sql = "SELECT * FROM WYDARZENIA ORDER BY Nr_wydarzenia";

        List<Wydarzenie> listWydarzenie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        if(!listWydarzenie.isEmpty()) {
            String sql2 = generateSqlFromList(listWydarzenie);
            List<Wydarzenie> listWydarzenieSK = jdbcTemplate.query(sql2, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
            return listWydarzenieSK;
        }
        return listWydarzenie;
    }
    public List<Wydarzenie> listUser(){
        String sql = "SELECT * FROM WYDARZENIA WHERE Data>= SYSDATE ORDER BY Nr_wydarzenia";

        List<Wydarzenie> listWydarzenie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        if(!listWydarzenie.isEmpty()){
            String sql2 = generateSqlFromList(listWydarzenie);
            List<Wydarzenie> listWydarzenieSK = jdbcTemplate.query(sql2, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
            return listWydarzenieSK;
        }

        return listWydarzenie;
    }

    public static String generateSqlFromList(List<Wydarzenie> listWydarzenie) {
        StringBuilder sqlBuilder = new StringBuilder();
        String sqlTemplate = "SELECT e.*, k.nazwa AS nazwaKlubu, o.nazwa AS nazwaObiektu, d.nazwa_dyscypliny AS nazwaDyscypliny FROM wydarzenia e JOIN kluby k ON e.nr_klubu = k.nr_klubu JOIN obiekty o ON e.nr_obiektu = o.nr_obiektu JOIN dyscypliny d ON e.nr_dyscypliny = d.nr_dyscypliny WHERE e.nr_klubu = %d AND e.nr_obiektu = %d AND e.nazwa = '%s' AND e.nr_dyscypliny = %d";

        for (Wydarzenie wydarzenie : listWydarzenie) {
            String sqlQuery = String.format(sqlTemplate, wydarzenie.getNrKlubu(), wydarzenie.getNrObiektu(), wydarzenie.getNazwa(), wydarzenie.getNrDyscypliny());
            sqlBuilder.append(sqlQuery).append("\nUNION ALL\n");
        }

        // Usuń ostatni "UNION ALL" - nie jest potrzebny po ostatnim zapytaniu
        if (!listWydarzenie.isEmpty()) {
            sqlBuilder.setLength(sqlBuilder.length() - "UNION ALL\n".length());
        }

        return sqlBuilder.toString();
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Wydarzenie wydarzenie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("WYDARZENIA").usingColumns("Nr_wydarzenia", "Nazwa", "Data", "Nr_klubu", "Nr_obiektu", "Nr_dyscypliny");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Wydarzenie get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM WYDARZENIA WHERE nr_wydarzenia = " + args[0];
        Wydarzenie wydarzenie = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        return wydarzenie;
    }

    /* Update – aktualizacja danych */
    public void update(Wydarzenie wydarzenie) {
        String sql = "UPDATE WYDARZENIA SET Nazwa=:Nazwa, Data=:Data, Nr_klubu=:NrKlubu, Nr_obiektu=:NrObiektu, Nr_dyscypliny=:NrDyscypliny WHERE Nr_wydarzenia=:NrWydarzenia";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM WYDARZENIA WHERE NR_WYDARZENIA = ?";
        jdbcTemplate.update(sql, id);
    }
}
