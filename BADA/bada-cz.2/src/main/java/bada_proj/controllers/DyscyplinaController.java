package bada_proj.controllers;

import bada_proj.dyscyplina.Dyscyplina;
import bada_proj.dyscyplina.DyscyplinaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class DyscyplinaController {
    @Autowired
    private DyscyplinaDAO daoDyscyplina;

    @RequestMapping(path = "/dyscypliny")
    public String viewDyscyplinaPage(Model model) {
        List<Dyscyplina> listDyscyplina = daoDyscyplina.list();
        model.addAttribute("listDyscyplina", listDyscyplina);
        return "dyscypliny";
    }

    @RequestMapping(path = "/newDysycyplina")
    public String showNewDyscyplinaForm(Model model) {
        Dyscyplina dyscyplina = new Dyscyplina();
        model.addAttribute("dyscyplina", dyscyplina);
        return "newDyscyplina";
    }

    @RequestMapping(value = "/saveDyscyplina", method = RequestMethod.POST)
    public String saveDyscyplina(@ModelAttribute("dyscyplina") Dyscyplina dyscyplina) {
        daoDyscyplina.save(dyscyplina);

        return "redirect:/dyscypliny";
    }

    @RequestMapping("/editDyscyplina/{id}")
    public ModelAndView showDyscyplinaEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_form_dyscypliny");
        Dyscyplina dyscyplina = daoDyscyplina.get(id);
        mav.addObject("dyscyplina", dyscyplina);
        return mav;
    }

    @RequestMapping(value = "/updateDyscyplina", method = RequestMethod.POST)
    public String updateDyscyplina(@ModelAttribute("dyscyplina") Dyscyplina dyscyplina) {
        daoDyscyplina.update(dyscyplina);

        return "redirect:/dyscypliny";
    }

    @RequestMapping("/deleteDyscyplina/{id}")
    public String deleteDyscyplina(@PathVariable(name = "id") int id,Model model) {
        try {
            daoDyscyplina.delete(id);
            return "redirect:/dyscypliny";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tej dyscypliny.");
            return "errors/errorPage";
        }

    }

}
