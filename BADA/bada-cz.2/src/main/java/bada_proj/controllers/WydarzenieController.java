package bada_proj.controllers;

import bada_proj.kibic.Kibic;
import bada_proj.kibic.KibicDAO;
import bada_proj.kibic_wydarzenie.KibicWydarzenie;
import bada_proj.kibic_wydarzenie.KibicWydarzenieDAO;
import bada_proj.obiekt.Obiekt;
import bada_proj.obiekt.ObiektDAO;
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
public class WydarzenieController {
    @Autowired
    private WydarzenieDAO daoWydarzenie;
    @Autowired
    private KibicWydarzenieDAO daoKibicWydarzenie;
    @Autowired
    private KibicDAO daoKibic;
    @Autowired
    private ObiektDAO daoObiekt;

    @RequestMapping(path = "/wydarzenia")
    public String viewWydarzeniePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        String login = authentication.getName();
        List<Wydarzenie> listWydarzenia;
        if (isAdmin) {
            listWydarzenia= daoWydarzenie.list();
            model.addAttribute("listWydarzenia", listWydarzenia);
            return "admin/wydarzenia_admin";
        } else {
            listWydarzenia = daoWydarzenie.listUser();
            Kibic kibic=daoKibic.getKibicByLogin(login);
            for (Wydarzenie wydarzenie : listWydarzenia) {
                int isUserSignedUp = checkIfUserSignedUp(kibic.getNrKibica(), wydarzenie.getNrWydarzenia());
                wydarzenie.setUserSignedUp(isUserSignedUp);
                Obiekt obiekt = daoObiekt.get(wydarzenie.getNrObiektu());
                int ileZapisanych=daoKibicWydarzenie.getHowManySignedUpCountForWydarzenie(wydarzenie.getNrWydarzenia());
                wydarzenie.setLiczbaDostepnychMiejsc(obiekt.getPojemnosc()-ileZapisanych);
                if (wydarzenie.getLiczbaDostepnychMiejsc()<=0){
                    if(isUserSignedUp==0) {
                        wydarzenie.setUserSignedUp(2);
                    }
                }
            }
            model.addAttribute("listWydarzenia", listWydarzenia);
            return "user/wydarzenia_user";
        }
    }
    @RequestMapping(path = "/wydarzenia_non_login")
    public String viewWydarzenienonLoginPage(Model model) {
        List<Wydarzenie> listWydarzenia;
            listWydarzenia= daoWydarzenie.listUser();
            model.addAttribute("listWydarzenia", listWydarzenia);
            return "non_login_wydarzenia";
        }

    @RequestMapping(path = "/newWydarzenie")
    public String showNewWydarzenieForm(Model model) {
        Wydarzenie wydarzenie = new Wydarzenie();
        model.addAttribute("wydarzenie", wydarzenie);
        return "newWydarzenie";
    }

    @RequestMapping(value = "/saveWydarzenie", method = RequestMethod.POST)
    public String saveWydarzenie(@ModelAttribute("wydarzenie") Wydarzenie wydarzenie) {
        daoWydarzenie.save(wydarzenie);

        return "redirect:/wydarzenia";
    }

    @RequestMapping("/editWydarzenie/{id}")
    public ModelAndView showWydarzenieEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_form_wydarzenia");
        Wydarzenie wydarzenie = daoWydarzenie.get(id);
        mav.addObject("wydarzenie", wydarzenie);
        return mav;
    }

    @RequestMapping(value = "/updateWydarzenie", method = RequestMethod.POST)
    public String updateWydarzenie(@ModelAttribute("wydarzenie") Wydarzenie wydarzenie) {
        daoWydarzenie.update(wydarzenie);

        return "redirect:/wydarzenia";
    }

    @RequestMapping("/deleteWydarzenie/{id}")
    public String deleteWydarzenia(@PathVariable(name = "id") int id,Model model) {
        try {
            daoWydarzenie.delete(id);
            return "redirect:/wydarzenia";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego wydarzenia.");
            return "errors/errorPage";
        }
    }
    @RequestMapping(value = "/saveSignUpForWydarzenie", method = RequestMethod.POST)
    public String saveSignUpForWydarzenie(@ModelAttribute("nrWydarzenia") int nrWydarzenia, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Kibic kibic = daoKibic.getKibicByLogin(currentUserName);
        KibicWydarzenie kibicWydarzenie = new KibicWydarzenie(nrWydarzenia, kibic.getNrKibica());
        model.addAttribute("isUserSignedUp", 1);
        daoKibicWydarzenie.save(kibicWydarzenie);

        return "redirect:/wydarzenia";
    }
    @RequestMapping(value = "/deleteSignUpForWydarzenie", method = RequestMethod.POST)
    public String deleteSignUpForWydarzenie(@ModelAttribute("nrWydarzenia") int nrWydarzenia, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Kibic kibic = daoKibic.getKibicByLogin(currentUserName);
        KibicWydarzenie kibicWydarzenie = new KibicWydarzenie(nrWydarzenia, kibic.getNrKibica());
        daoKibicWydarzenie.delete(kibicWydarzenie.getNrWydarzenia(), kibicWydarzenie.getNrKibica());

        return "redirect:/wydarzenia";
    }
    private int checkIfUserSignedUp(int nrKibica, int nrWydarzenia) {
        return daoKibicWydarzenie.isUserSignedUp(nrKibica, nrWydarzenia);
    }

    }
