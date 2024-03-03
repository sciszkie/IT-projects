package bada_proj.controllers;

import bada_proj.address.Adress;
import bada_proj.address.AdressDAO;
import bada_proj.klub.Klub;
import bada_proj.klub.KlubDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class KlubController {
    @Autowired
    private KlubDAO daoKlub;

    @RequestMapping(path = "/kluby")
    public String viewKlubPage(Model model) {
        List<Klub> listKlub = daoKlub.list();
        model.addAttribute("listKlub", listKlub);
        return "kluby";
    }

    @RequestMapping(path = "/newKlub")
    public String showNewKlubForm(Model model) {
        Klub klub = new Klub();
        model.addAttribute("klub", klub);
        return "newKlub";
    }

    @RequestMapping(value = "/saveKlub", method = RequestMethod.POST)
    public String saveKlub(@ModelAttribute("klub") Klub klub) {
        daoKlub.save(klub);

        return "redirect:/kluby";
    }

    @RequestMapping("/editKlub/{id}")
    public ModelAndView showKlubEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_form_kluby");
        Klub klub = daoKlub.get(id);
        mav.addObject("klub", klub);
        return mav;
    }

    @RequestMapping(value = "/updateKlub", method = RequestMethod.POST)
    public String updateKlub(@ModelAttribute("klub") Klub klub) {
        daoKlub.update(klub);

        return "redirect:/kluby";
    }

    @RequestMapping("/deleteKlub/{id}")
    public String deleteKlub(@PathVariable(name = "id") int id,Model model) {
        try {
            daoKlub.delete(id);
            return "redirect:/kluby";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego klubu.");
            return "errors/errorPage";
        }
    }
}
