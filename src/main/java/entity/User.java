package entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User extends Entity{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String mobilePhone;
    /** status of user (true - is active, false - is blocked) */
    private boolean blockStatus;
    private BigDecimal credit;
    private BigDecimal balance;
    private int documentNumber;
    private String role;
    private String login;
    private String password;


    public User(int id, String name, String surname, String email, String mobilePhone, boolean blockStatus,
                BigDecimal credit, BigDecimal balance, int documentNumber, String role, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.blockStatus = blockStatus;
        this.credit = credit;
        this.balance = balance;
        this.documentNumber = documentNumber;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(int documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                isBlockStatus() == user.isBlockStatus() &&
                getDocumentNumber() == user.getDocumentNumber() &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname()) &&
                getEmail().equals(user.getEmail()) &&
                getMobilePhone().equals(user.getMobilePhone()) &&
                getCredit().equals(user.getCredit()) &&
                getBalance().equals(user.getBalance()) &&
                getRole().equals(user.getRole()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getEmail(), getMobilePhone(), isBlockStatus(), getCredit(),
                getBalance(), getDocumentNumber(), getRole(), getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", blockStatus=" + blockStatus +
                ", credit=" + credit +
                ", balance=" + balance +
                ", documentNumber=" + documentNumber +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
