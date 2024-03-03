package bada_proj.controllers;

import bada_proj.address.Adress;
import bada_proj.address.AdressDAO;
import bada_proj.kibic.Kibic;
import bada_proj.kibic.KibicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping(path = "/")
public class AdresController {
    @Autowired
    private KibicDAO daoKibic;
    @Autowired
    private AdressDAO daoAdres;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(path = "/adresy")
    public String viewAdresPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLogin = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        List<Adress> listAdress;
        if (isAdmin) {
            listAdress = daoAdres.list();
            model.addAttribute("listAdress", listAdress);
            return "admin/adresy_admin";
        } else {
            Kibic kibic=daoKibic.getKibicByLogin(currentLogin);
            listAdress = daoAdres.findByNrAdresu(kibic.getNrAdresu());
            model.addAttribute("listAdress", listAdress);
            return "user/adresy_user";
        }
    }

    @RequestMapping(path = "/newAddress")
    public String showNewAdresForm(Model model) {
        Adress adress = new Adress();
        model.addAttribute("adress", adress);
        return "newAddress";
    }

    @RequestMapping(value = "/saveAdres", method = RequestMethod.POST)
    public String saveAdres(@ModelAttribute("adress") Adress adress,Model model) {
        daoAdres.save(adress);

        return "redirect:/adresy";
    }

    @RequestMapping("/editAdres/{id}")
    public ModelAndView showAdresEditForm(@PathVariable(name = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin){
            ModelAndView mav = new ModelAndView("user/edit_form_adresy_user");
            String currentLogin = authentication.getName();
            Kibic kibic=daoKibic.getKibicByLogin(currentLogin);
            Adress adress = daoAdres.get(id);
            if (kibic!=null&&id==kibic.getNrAdresu()) {
                mav.addObject("adress", adress);
                return mav;
            } else {
                return new ModelAndView("redirect:/access-denied");
            }
        }
        else{
            ModelAndView mav = new ModelAndView("admin/edit_form_adresy_admin");
            Adress adress = daoAdres.get(id);
            mav.addObject("adress", adress);
            return mav;
        }

    }

    @RequestMapping(value = "/updateAdres", method = RequestMethod.POST)
    public String updateAdres(@ModelAttribute("adress") Adress adress,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            daoAdres.update(adress);
            return "redirect:/adresy";
        } else {
            daoAdres.update(adress);
            return "redirect:/adresy";
        }
    }

    @RequestMapping("/deleteAdres/{id}")
    public String deleteAdres(@PathVariable(name = "id") int id, Model model) {

        try {
            daoAdres.delete(id);
            return "redirect:/adresy";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego adresu.");
            return "errors/errorPage";
        }
    }
    @RequestMapping(path = "/registerAdres")
    public String showRegisterAdresForm(Model model) {
        Adress adress = new Adress();
        model.addAttribute("adress", adress);
        return "registerAdres";
    }

    @RequestMapping(value = "/saveRegisterAdres", method = RequestMethod.POST)
    public String saveRegisterAdres(@ModelAttribute("adress") Adress adress, Model model) {
        int nrAdresu = jdbcTemplate.queryForObject("SELECT NR_ADRESUS.NEXTVAL FROM dual", int.class)+1;
        daoAdres.save(adress);
        return "redirect:/registerKibic?nrAdresu=" + nrAdresu;
    }
}
