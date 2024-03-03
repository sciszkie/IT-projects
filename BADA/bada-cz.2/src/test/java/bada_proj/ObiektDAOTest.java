package bada_proj;

import bada_proj.obiekt.Obiekt;
import bada_proj.obiekt.ObiektDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObiektDAOTest {
    private ObiektDAO dao;
    @BeforeEach
    void setUp() throws Exception{
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@192.168.56.1:1521:xe");
        dataSource.setUsername("APLIKACJA");
        dataSource.setPassword("APLIKACJA");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new ObiektDAO(new JdbcTemplate(dataSource));
    }
    @Test
    void list() {
        List<Obiekt> listObiekt = dao.list();

        assertFalse(listObiekt.isEmpty());
    }

    @Test
    void save() {
        Obiekt obiekt = new Obiekt(11, "abc", 10000, 4, 21);
        dao.save(obiekt);
    }

    @Test
    void get() {
        int id = 8;
        Obiekt obiekt = dao.get(id);

        assertNotNull(obiekt);
    }

    @Test
    void update() {
        Obiekt obiekt = new Obiekt();
        obiekt.setNrObiektu(2);
        obiekt.setNazwa("lkjhg");
        obiekt.setPojemnosc(4000);
        obiekt.setNrKlubu(1);
        obiekt.setNrAdresu(1);

        dao.update(obiekt);
    }

    @Test
    void delete() {
        int id = 2;
        dao.delete(id);
    }
}
