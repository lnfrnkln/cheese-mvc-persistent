package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

/**
 * Created by sl6059 on 4/23/2017.
 */
public class AddMenuItemForm {

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    private Menu menu;

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    private Iterable<Cheese> cheeses;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @NotNull
    private int menuId;

    public int getCheeseId() {
        return cheeseId;
    }

    @NotNull
    private int cheeseId;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu) {
        this.cheeses = cheeses;
        this.menu = menu;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }


////    public Iterable<Integer> getCheeseIds() {
////        return cheeseIds;
////    }
////
////    public void setCheeseIds(Iterable<Integer> cheeseIds) {
////        this.cheeseIds = cheeseIds;
////    }
////
////    private Iterable<Integer> cheeseIds;
//
//
//    public int getCheeseId() {
//        return cheeseId;
//    }
//
//    public void setCheeseId(int cheeseId) {
//        this.cheeseId = cheeseId;
//    }
//
//    private int cheeseId;
//
//
//
////    Need to add a default no-arg constructor and one that accepts and sets values for menu and cheeses
////    public MenuItemChoice(String menu, String cheeses) {
////   }

