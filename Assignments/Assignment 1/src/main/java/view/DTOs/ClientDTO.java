package view.DTOs;

public class ClientDTO {
    private String name;
    private String address;
    private Integer identityCardNumber;
    private String personalNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(Integer identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
