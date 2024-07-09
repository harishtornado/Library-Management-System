package models;

public class User {
    private static int id = 0;
    private int user_id;
    private String user_name;
    private String password;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String name, String password, Role role) {
        id = id + 1;
        this.user_name = name;
        this.password = password;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", role=" + role
                + "]";
    }
}
