package com.pbattles.entity;

/**
 * Created by Nazar_Sheremeta on 5/29/14.
 */
public class RegistrationInfo {

    private String login;
    private String name;
    private String password;
    private String passwordRepeat;

    public RegistrationInfo() {
    }

    public RegistrationInfo(String login, String name, String password, String passwordRepeat) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistrationInfo that = (RegistrationInfo) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (passwordRepeat != null ? !passwordRepeat.equals(that.passwordRepeat) : that.passwordRepeat != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordRepeat != null ? passwordRepeat.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RegistrationInfo{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", passwordRepeat='" + passwordRepeat + '\'' +
                '}';
    }
}
