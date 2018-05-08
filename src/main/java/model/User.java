package model;

public class User extends IdContainer{
    private String login;
    private String password;

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

    public static class Builder{
        User user=new User();

        public Builder setId(int id){
            user.setId(id);
            return this;
        }

        public Builder setLogin(String login){
            user.setLogin(login);
            return this;
        }

        public Builder setPassword(String password){
            user.setPassword(password);
            return this;
        }

        public User build(){
            //TODO check values
            return user;
        }
    }

}
