package bada_proj.address;

import bada_proj.address.Adress;
import bada_proj.kibic.Kibic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdressDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdressDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Adress> findByNrAdresu(int nrAdresu) {
        String sql = "SELECT * FROM ADRESY WHERE nr_adresu = ?";
        List<Adress> listAdress = jdbcTemplate.query(sql, new Object[]{nrAdresu}, BeanPropertyRowMapper.newInstance(Adress.class));
        return listAdress;
    }
    public List<Adress> list(){
        String sql = "SELECT * FROM ADRESY ORDER BY Nr_adresu";
        List<Adress> listAdress = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Adress.class));
        return listAdress;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Adress adress) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("ADRESY").usingColumns("Nr_adresu", "Nr_budynku", "Nr_lokalu", "Ulica", "Miasto", "Kod_pocztowy");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adress);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public Adress get(int id){
        Object[] args = {id};
        String sql = "SELECT * FROM ADRESY WHERE nr_adresu = " + args[0];
        Adress adress = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Adress.class));
        return adress;
    }

    /* Update – aktualizacja danych */
    public void update(Adress adress) {
        String sql = "UPDATE ADRESY SET Nr_budynku=:NrBudynku, Nr_lokalu=:NrLokalu, Ulica=:Ulica, Miasto=:Miasto, Kod_pocztowy=:KodPocztowy WHERE Nr_adresu=:NrAdresu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adress);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM ADRESY WHERE NR_ADRESU = ?";
        jdbcTemplate.update(sql, id);
    }
    public Adress findLatestAdress() {
        String sql = "SELECT * FROM (SELECT * FROM ADRESY ORDER BY nr_Adresu DESC) WHERE ROWNUM <= 1";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Adress.class))
                .stream()
                .findFirst()
                .orElse(null);
    }

}