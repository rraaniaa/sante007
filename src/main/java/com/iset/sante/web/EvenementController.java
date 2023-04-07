package com.iset.sante.web;


import com.iset.sante.entities.Evenement;
import com.iset.sante.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EvenementController {

    @Autowired
    private EvenementService EvenementService;

    // display list of events
    @GetMapping("/admin/home")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }



    @GetMapping("/showNewEventForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
            Evenement evenement = new Evenement();
        model.addAttribute("evenement", evenement);
        return "new_event";
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute("evenement") Evenement evenement
            , @RequestParam("time") String date ,
                            ModelMap modelMap ) throws ParseException {
        // save evenement to database
        //conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date Time = dateformat.parse(String.valueOf(date));
        evenement.setTimeme(Time);

        EvenementService.saveEvent(evenement);
        return "redirect:/admin/home";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get event from the service
        Evenement evenement = EvenementService.getEvenementById(id);

        // set event as a model attribute to pre-populate the form
        model.addAttribute("evenement", evenement);
        return "update_event";
    }

    @GetMapping("/deleteEvenement/{id}")
    public String deleteEvenement(@PathVariable (value = "id") long id) {

        // call delete event method
        this.EvenementService.deleteEvenementById(id);
        return "redirect:/admin/home";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Evenement> page = EvenementService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Evenement> listEvenement = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEvenement", listEvenement);
        return "adminhome";

    }




}
