public class Customer {
    private static final String NO_NAME_GIVEN = "DEFAULT";
    private static final String NO_SSN_GIVEN = "DEFAULT";
    private String name;
    private String ssn;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private Money money;

    public Customer(String name, String ssn, Money money){
        if (name == (null) || name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");
        if (ssn.length() != 10)
            throw new IllegalArgumentException("Should be 10 digits");
        this.name=name;
        this.ssn=ssn;
        this.money = money;
    }

    public Customer(Money money){
        this.money = money;
        name = NO_NAME_GIVEN;
        ssn = NO_SSN_GIVEN;
    }

    public String getName(){
        return name;
    }

    public String getSsn(){
        return ssn;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public String getAddress(){
        return address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress=emailAddress;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public Money getMoney(){
        return money;
    }

    @Override
    public String toString(){
        return "name: " + getName() + ", ssn: " + getSsn() + ", address: " + getAddress() + ", phone number: " + getPhoneNumber() + ", email address: " + getEmailAddress();
    }
}