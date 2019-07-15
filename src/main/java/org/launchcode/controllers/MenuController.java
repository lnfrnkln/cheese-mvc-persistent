
package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToMany;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private CheeseDao cheeseDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Menus");
        model.addAttribute("items", menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddMenu(Model model){
        model.addAttribute("title", "Create Menu");
        model.addAttribute(new Menu());
        model.addAttribute("items", menuDao.findAll());
        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddMenu(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Menu");
            model.addAttribute(new Error());
            model.addAttribute("menus", menuDao.findAll());
            return "menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/" + menu.getId();

    }

    @RequestMapping(value = "view/{idMenu}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int idMenu){
        Menu choiceMenu = menuDao.findOne(idMenu);
//        Cheese choiceCheese = cheeseDao.findOne();
        model.addAttribute("title", "Menu: " + choiceMenu.getName());
//        model.addAttribute("menu", choiceMenu);
        model.addAttribute("items", choiceMenu.getCheeses());

        Iterable<Menu> test = menuDao.findAll();
        model.addAttribute("num", test);
        return "menu/view";
    }

//    @RequestMapping(value = "view/{idMenu}", method = RequestMethod.POST)
//    public String processRemoveCheeseFromMenuForm(@PathVariable int idMenu,
//                                                  @RequestParam int [] itemIds) {
//        for (int itemId : itemIds) {
//            menuDao.delete(itemId);
//        }
//        return "redirect:";
//    }

    @RequestMapping(value = "add-item/{idMenu}", method = RequestMethod.GET)
    public  String addItem(Model model, @PathVariable int idMenu){
        Menu menu = menuDao.findOne(idMenu);
        Iterable<Cheese> cheeses = cheeseDao.findAll();

        AddMenuItemForm addMenuItemForm = new AddMenuItemForm(cheeses, menu);
//        ---------------
//        Cheese choiceCheese = cheeseDao.findAll();

        model.addAttribute("title", "Add item to menu: " + menuDao.findOne(idMenu).getName());
        model.addAttribute("cheeses", cheeseDao.findAll());
        return "menu/add-item";
    }

    @RequestMapping(value = "add-item/{idMenu}", method = RequestMethod.POST)
    public  String processAddItem(Model model, @PathVariable int idMenu,
                                  @ModelAttribute AddMenuItemForm addMenuItemForm, Errors errors,
                                  @RequestParam int cheeseId){

        if (errors.hasErrors()) {
            model.addAttribute("");
            return "menu/add-item";
        }

        Menu choiceMenu = menuDao.findOne(idMenu);
        Cheese choiceCheese = cheeseDao.findOne(cheeseId);
        choiceMenu.addItem(choiceCheese);
        menuDao.save(choiceMenu);

        return "redirect:/menu/view/"+idMenu;
    }


}