package model.user;

import model.IdContainer;

import java.util.Objects;

public class User extends IdContainer {
    private String login;
    private String password;
    private String name;
    private String soname;
    private String email;
    private UserRole role;
    private int rating;
    private int pay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoname() {
        return soname;
    }

    public void setSoname(String soname) {
        this.soname = soname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        User user = new User();

        public Builder setId(int id) {
            user.setId(id);
            return this;
        }

        public Builder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setName(String name) {
            user.setName(name);
            return this;
        }

        public Builder setSoname(String soname) {
            user.setSoname(soname);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder setRole(UserRole userRole) {
            user.setRole(userRole);
            return this;
        }

        public Builder setRating(int rating) {
            user.setRating(rating);
            return this;
        }

        public Builder setPay(int pay) {
            user.setPay(pay);
            return this;
        }

        public User build() {
            return user;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return rating == user.rating &&
                pay == user.pay &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(soname, user.soname) &&
                Objects.equals(email, user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, soname, email, role, rating, pay);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", soname='" + soname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", rating=" + rating +
                ", pay=" + pay +
                '}';
    }
}
