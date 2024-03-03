package bada_proj.controllers;

import bada_proj.obiekt.Obiekt;
import bada_proj.obiekt.ObiektDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping(path = "/")
public class ObiektController {

    @Autowired
    private ObiektDAO daoObiekt;


    @RequestMapping(path = "/obiekty")
    public String viewObiektPage(Model model){
        List<Obiekt> listObiekt = daoObiekt.list();
        model.addAttribute("listObiekt", listObiekt);
        return "obiekty";
    }

    @RequestMapping(path = "/newObiekt")
    public String showNewObiektForm(Model model){
        Obiekt obiekt = new Obiekt();
        model.addAttribute("obiekt", obiekt);
        return "newObiekt";
    }

    @RequestMapping(value = "/saveObiekt", method = RequestMethod.POST)
    public String saveObiekt(@ModelAttribute("obiekt") Obiekt obiekt){
        daoObiekt.save(obiekt);

        return "redirect:/obiekty";
    }

    @RequestMapping("/editObiekt/{id}")
    public ModelAndView showObiektEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_form_obiekty");
        Obiekt obiekt = daoObiekt.get(id);
        mav.addObject("obiekt", obiekt);
        return mav;
    }

    @RequestMapping(value = "/updateObiekt", method = RequestMethod.POST)
    public String updateObiekt(@ModelAttribute("obiekt") Obiekt obiekt){
        daoObiekt.update(obiekt);

        return "redirect:/obiekty";
    }

    @RequestMapping("/deleteObiekt/{id}")
    public String deleteObiekt(@PathVariable(name = "id") int id,Model model){
        try {
            daoObiekt.delete(id);
            return "redirect:/obiekty";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego obiektu.");
            return "errors/errorPage";
        }
    }
}
