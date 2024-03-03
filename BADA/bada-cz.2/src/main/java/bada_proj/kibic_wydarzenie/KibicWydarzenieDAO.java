package bada_proj.kibic_wydarzenie;

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
public class KibicWydarzenieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public KibicWydarzenieDAO(JdbcTemplate jdbcTemplate) {
        //super();
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<KibicWydarzenie> list(){
        String sql = "SELECT Kibice.Nr_kibica AS nrKibica, Kibice.Imie AS imie, Kibice.Nazwisko AS nazwisko, Kibice.PESEL AS pesel, Wydarzenia.Nr_wydarzenia AS nrWydarzenia, Wydarzenia.Nazwa AS nazwa, Wydarzenia.Data AS data FROM Kibice_Wydarzenia JOIN Kibice ON Kibice_Wydarzenia.Nr_kibica = Kibice.Nr_kibica JOIN Wydarzenia ON Kibice_Wydarzenia.Nr_wydarzenia = Wydarzenia.Nr_wydarzenia";

        List<KibicWydarzenie> listKibicWydarzenie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(KibicWydarzenie.class));
        return listKibicWydarzenie;
    }
    public List<KibicWydarzenie> findByNrKibica(int NrKibica) {
        String sql = "SELECT Kibice.Nr_kibica AS nrKibica, Kibice.Imie AS imie, Kibice.Nazwisko AS nazwisko, Kibice.PESEL AS pesel, Wydarzenia.Nr_wydarzenia AS nrWydarzenia, Wydarzenia.Nazwa AS nazwa, Wydarzenia.Data AS data FROM Kibice_Wydarzenia JOIN Kibice ON Kibice_Wydarzenia.Nr_kibica = Kibice.Nr_kibica JOIN Wydarzenia ON Kibice_Wydarzenia.Nr_wydarzenia = Wydarzenia.Nr_wydarzenia WHERE Kibice.Nr_kibica = ? AND Wydarzenia.Data > SYSDATE";
        List<KibicWydarzenie> listKibicWydarzenie = jdbcTemplate.query(sql, new Object[]{NrKibica}, BeanPropertyRowMapper.newInstance(KibicWydarzenie.class));
        return listKibicWydarzenie;
    }

    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(KibicWydarzenie kibicWydarzenie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("KIBICE_WYDARZENIA").usingColumns("Nr_wydarzenia", "Nr_kibica");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kibicWydarzenie);
        insertActor.execute(param);
    }

    /* Read – odczytywanie danych z bazy */
    public KibicWydarzenie get(int nrWydarzenia, int nrKibica){
        String sql = "SELECT * FROM KIBICE_WYDARZENIA WHERE nr_wydarzenia =? AND nr_kibica = ?" ;
        KibicWydarzenie kibicWydarzenie = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(KibicWydarzenie.class), nrWydarzenia, nrKibica);
        return kibicWydarzenie;
    }

    /* Update – aktualizacja danych */
    public void update(KibicWydarzenie kibicWydarzenie) {
        String sql = "UPDATE KIBICE_WYDARZENIA SET Nr_kibica=:NrKibica WHERE Nr_wydarzenia=:NrWydarzenia";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kibicWydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
    /* Delete – wybrany rekord z danym id */
    public void delete(int nrWydarzenia, int nrKibica) {
        String sql = "DELETE FROM KIBICE_WYDARZENIA WHERE NR_WYDARZENIA = ? AND NR_KIBICA = ?";
        jdbcTemplate.update(sql, nrWydarzenia,nrKibica);
    }
    public int isUserSignedUp(int nrKibica, int nrWydarzenia) {
        String sql = "SELECT COUNT(*) FROM KIBICE_WYDARZENIA WHERE NR_KIBICA = ? AND NR_WYDARZENIA = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, nrKibica, nrWydarzenia);
        int result;
        if(count>0){
            result=1;
        }
        else{
            result=0;
        }
        return result;
    }
    public int getHowManySignedUpCountForWydarzenie(int nrWydarzenia) {
        String sql = "SELECT COUNT(*) FROM KIBICE_WYDARZENIA WHERE NR_WYDARZENIA = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, nrWydarzenia);
    }

}
