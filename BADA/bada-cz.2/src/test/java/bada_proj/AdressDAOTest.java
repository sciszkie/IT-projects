package bada_proj;

import bada_proj.address.Adress;
import bada_proj.address.AdressDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdressDAOTest {

    private AdressDAO dao;
    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@192.168.56.1:1521:xe");
        dataSource.setUsername("APLIKACJA");
        dataSource.setPassword("APLIKACJA");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new AdressDAO(new JdbcTemplate(dataSource));
    }
    @Test
    void list() {
        List<Adress> listAdress = dao.list();

        assertFalse(listAdress.isEmpty());
    }

    @Test
    void save() {
        Adress adress = new Adress(30, "1","0",  "Dzicza", "Małkinia", "12-421");
        dao.save(adress);
    }

    @Test
    void get() {
        int id = 2;
        Adress adress = dao.get(id);

        assertNotNull(adress);
    }

    @Test
    void update() {
        Adress adress = new Adress();
        adress.setNrAdresu(2);
        adress.setNrBudynku("15");
        adress.setNrLokalu("34");
        adress.setUlica("Gruba");
        adress.setMiasto("Chude");
        adress.setKodPocztowy("11-111");

        dao.update(adress);
    }

    @Test
    void delete() {
        int id = 5;
        dao.delete(id);
    }
}