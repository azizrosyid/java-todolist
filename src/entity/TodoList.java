package entity;

public class TodoList {
    private int id;
    private String todo;
    private int idSection;

    public TodoList() {
    }

    public TodoList(int id, String name) {
        this.id = id;
        this.todo = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }
}
