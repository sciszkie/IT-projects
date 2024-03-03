package bada_proj.controllers;

import bada_proj.kibic.Kibic;
import bada_proj.kibic.KibicDAO;
import bada_proj.kibic_wydarzenie.KibicWydarzenie;
import bada_proj.kibic_wydarzenie.KibicWydarzenieDAO;
import bada_proj.wydarzenie.Wydarzenie;
import bada_proj.wydarzenie.WydarzenieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class KibiceWydarzeniaController {

    @Autowired
    private KibicWydarzenieDAO daoKibicWydarzenie;
    @Autowired
    private KibicDAO daoKibic;

    @RequestMapping(path = "/kibiceWydarzenia")
    public String viewKibicWydarzeniePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin){
            List<KibicWydarzenie> listKibicWydarzenie = daoKibicWydarzenie.list();
            model.addAttribute("listKibicWydarzenie", listKibicWydarzenie);
            return "admin/kibiceWydarzenia_admin";
        }
        else{
            Kibic kibic=daoKibic.getKibicByLogin(currentUsername);
            List<KibicWydarzenie> listKibicWydarzenie = daoKibicWydarzenie.findByNrKibica(kibic.getNrKibica());
            model.addAttribute("listKibicWydarzenie", listKibicWydarzenie);
            return "user/kibiceWydarzenia_user";
        }
    }

    @RequestMapping(path = "/newKibicWydarzenie")
    public String showNewKibicWydarzenieForm(Model model) {
        KibicWydarzenie kibicWydarzenie = new KibicWydarzenie();
        model.addAttribute("kibicWydarzenie", kibicWydarzenie);
        return "newKibicWydarzenie";
    }

    @RequestMapping(value = "/saveKibicWydarzenie", method = RequestMethod.POST)
    public String saveKibicWydarzenie(@ModelAttribute("kibicWydarzenie") KibicWydarzenie kibicWydarzenie) {
        daoKibicWydarzenie.save(kibicWydarzenie);

        return "redirect:/kibiceWydarzenia";
    }

    @RequestMapping("/editKibicWydarzenie/{nrWydarzenia}/{nrKibica}")
    public ModelAndView showKibicWydarzenieEditForm(@ModelAttribute("kibicWydarzenie") KibicWydarzenie kibicWydarzenie) {
        ModelAndView mav = new ModelAndView("edit_form_kibiceWydarzenia");
        mav.addObject("kibicWydarzenie", kibicWydarzenie);
        return mav;
    }

    @RequestMapping(value = "/updateKibicWydarzenie", method = RequestMethod.POST)
    public String updateKibicWydarzenie(@ModelAttribute("kibicWydarzenie") KibicWydarzenie kibicWydarzenie) {
        daoKibicWydarzenie.update(kibicWydarzenie);

        return "redirect:/kibiceWydarzenia";
    }

    @RequestMapping("/deleteKibicWydarzenie/{nrWydarzenia}/{nrKibica}")
    public String deleteKibiceWydarzenia(@PathVariable(name = "nrWydarzenia") int nrWydarzenia,
                                         @PathVariable(name = "nrKibica") int nrKibica,Model model) {
        try {
            daoKibicWydarzenie.delete(nrWydarzenia, nrKibica);
            return "redirect:/kibiceWydarzenia";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego wpisu.");
            return "errors/errorPage";
        }

    }

}
