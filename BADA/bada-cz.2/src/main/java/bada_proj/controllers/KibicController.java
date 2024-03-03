package bada_proj.controllers;

import bada_proj.PasswordChange;
import bada_proj.kibic.Kibic;
import bada_proj.kibic.KibicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping(path = "/")
public class KibicController {

    @Autowired
    private KibicDAO daoKibic;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/kibice")
    public String viewKibicPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        List<Kibic> listKibic;
        if (isAdmin) {
            listKibic = daoKibic.list();
            model.addAttribute("listKibic", listKibic);
            return "admin/kibice_admin";
        } else {
            listKibic = daoKibic.findByLogin(currentUsername);
            model.addAttribute("listKibic", listKibic);
            return "user/kibice_user";
        }
    }


    @RequestMapping(path = "/newKibic")
    public String showNewKibicForm(Model model) {
        Kibic kibic = new Kibic();
        model.addAttribute("kibic", kibic);
        return "newKibic";
    }

    @RequestMapping(value = "/saveKibic", method = RequestMethod.POST)
    public String saveKibic(@ModelAttribute("kibic") Kibic kibic,Model model) {
        String hasloHash = passwordEncoder.encode(kibic.getHaslo());
        kibic.setHaslo(hasloHash);
        String login = kibic.getLogin();
        if (daoKibic.isLoginAlreadyExists(login)>0) {
            model.addAttribute("error", "Ten login jest już zajęty.");
            return "errors/errorPage";
        }
        daoKibic.save(kibic);


        return "redirect:/kibice";
    }

    @RequestMapping("/editKibic/{id}")
    public ModelAndView showKibicEditForm(@PathVariable(name = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            ModelAndView mav = new ModelAndView("user/edit_form_kibice_user");
            String currentUsername = authentication.getName();
            Kibic kibic = daoKibic.get(id);
            if ((kibic != null && currentUsername.equals(kibic.getLogin())) || isAdmin) {
                mav.addObject("kibic", kibic);
                return mav;
            } else {
                return new ModelAndView("redirect:/access-denied");
            }
        } else {
            Kibic kibic = daoKibic.get(id);
            ModelAndView mav = new ModelAndView("admin/edit_form_kibice_admin");
            mav.addObject("kibic", kibic);
            return mav;
        }

    }

    @RequestMapping(value = "/updateKibic", method = RequestMethod.POST)
    public String updateKibic(@ModelAttribute("kibic") Kibic kibic,Model model) {
        String login = kibic.getLogin();
        System.out.println(kibic.getLogin());
        System.out.println(kibic.getNrKibica());
        Kibic kibicOldData=daoKibic.get(kibic.getNrKibica());
        String oldlogin=kibicOldData.getLogin();
        System.out.println(login);
        System.out.println(oldlogin);
        if (daoKibic.isLoginAlreadyExists(login)>0 && !login.equals(oldlogin)) {
            model.addAttribute("error", "Ten login jest już zajęty.");
            return "errors/errorPage";
        }
        else if(daoKibic.isLoginAlreadyExists(login)>1 && login.equals(oldlogin)){
            model.addAttribute("error", "Ten login jest już zajęty.");
            return "errors/errorPage";
        }
        else {
            daoKibic.update(kibic);
            return "redirect:/kibice";
        }

    }

    @RequestMapping("/deleteKibic/{id}")
    public String deleteKibic(@PathVariable(name = "id") int id,Model model) {
        try {
            daoKibic.delete(id);
            return "redirect:/kibice";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Nie można usunąć tego kibica.");
            return "errors/errorPage";
        }

    }

    @RequestMapping(path = "/registerKibic")
    public String showRegisterKibicForm(Model model, @RequestParam("nrAdresu") int nrAdresu) {
        Kibic kibic = new Kibic();
        kibic.setNrAdresu(nrAdresu);
        model.addAttribute("kibic", kibic);
        return "registerKibic";
    }

    @RequestMapping(value = "/saveRegisterKibic", method = RequestMethod.POST)
    public String saveRegisterKibic(@ModelAttribute("kibic") Kibic kibic, Model model) {
        String hasloHash = passwordEncoder.encode(kibic.getHaslo());
        kibic.setHaslo(hasloHash);
        String login = kibic.getLogin();
        if (daoKibic.isLoginAlreadyExists(login)>0) {
            model.addAttribute("error", "Ten login jest już zajęty.");
            return "errors/errorPage";
        }
        daoKibic.save(kibic);
        return "redirect:/login";
    }

    @RequestMapping(path = "/password_change_user/{id}")
    public ModelAndView showPasswordChangeForm( @PathVariable(name = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        Kibic kibic = daoKibic.get(id);
        PasswordChange passwordChange=new PasswordChange();
        if(!isAdmin){
            if (login.equals(kibic.getLogin())) {
                ModelAndView mav = new ModelAndView("user/password_change_user");
                mav.addObject("passwordChange", passwordChange);
                return mav;
            } else {
                return new ModelAndView("redirect:/access-denied");
            }
        }
        else{
            ModelAndView mav = new ModelAndView("admin/password_change_admin");
            passwordChange.setKibic(daoKibic.get(id));
            mav.addObject("passwordChange", passwordChange);
            return mav;
        }

    }

    @RequestMapping(value = "/savePasswordChange", method = RequestMethod.POST)
    public String savePasswordChange(@ModelAttribute("passwordChange") PasswordChange passwordChange, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            Kibic kibic = daoKibic.getKibicByLogin(login);
            if (daoKibic.isOldPasswordCorrect(login, passwordChange.getStareHaslo())) {
                String newHashedPassword = passwordEncoder.encode(passwordChange.getNoweHaslo());
                kibic.setHaslo(newHashedPassword);
                daoKibic.updatePassword(kibic);
                return "redirect:/kibice";
            } else {
                model.addAttribute("error", "Błędne hasło, spróbuj ponownie.");
                return "errors/errorPage";
            }
        } else {
            String newHashedPassword = passwordEncoder.encode(passwordChange.getNoweHaslo());
            int nrKibica = passwordChange.getKibic().getNrKibica();
            Kibic kibic = daoKibic.get(nrKibica);
            kibic.setHaslo(newHashedPassword);
            daoKibic.updatePassword(kibic);
            return "redirect:/kibice";
        }
    }

}
