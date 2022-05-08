package entity;

import java.util.List;

public class TodoSection {
    private int id;
    private String name;
    private User owner;
    private List<TodoList> todoLists;

    public TodoSection(){}

    public TodoSection(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }


}
