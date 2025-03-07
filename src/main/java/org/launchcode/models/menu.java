package org.launchcode.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
public class Menu {


    @NotNull
    @Size(min=3, max=20, message = "Menu name must be between 3-20 characters")
    private String name;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Id
    @GeneratedValue
    private int id;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}


    public List<Cheese> getCheeses() {
        return cheeses;
    }

    @ManyToMany
    private List<Cheese> cheeses;


    public void addItem(Cheese item){cheeses.add(item);}

    public Menu(){}

    public Menu(String name){
        this.name = name;
    }

}
public class menu {
}
