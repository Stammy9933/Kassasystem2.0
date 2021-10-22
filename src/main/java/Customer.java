public class Customer {
    private static final String NO_NAME_GIVEN = "DEFAULT";
    private static final String NO_SSN_GIVEN = "DEFAULT";
    private String name;
    private String ssn;
    private Money money;

    public Customer(String name, String ssn, Money money) {
        String nameRegex = "^[a-zA-Z]+[a-zA-Z]+[\s][a-zA-Z]+[a-zA-Z]+$";
        if(name == null){
            throw new NullPointerException("Name cannot be null");
        }
        if(!name.matches(nameRegex)) {
            throw new IllegalArgumentException("Incorrect entry of full name, cannot be empty, contain periods, numbers or be shorter than 4 characters");
        }
        String ssnRegex = "^[0-9]{2}((0[1-9])|(10|11|12))((0[1-9])|([1-2][0-9])|(30|31))-[0-9]{4}$";
        if (ssn == null){
            throw new NullPointerException("SSN cannot be null");
        }
        if(!ssn.matches(ssnRegex)){
            throw new IllegalArgumentException("Incorrect SSN, should follow this format: XXXXXX-XXXX");
        }
        if (money==null)
            throw new NullPointerException("Money cannot be null");
        this.name = name;
        this.ssn = ssn;
        this.money=money;
    }

    public Customer(Money money) {
        this.money = money;
        name = NO_NAME_GIVEN;
        ssn = NO_SSN_GIVEN;
    }

    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "name: " + getName() + ", ssn: " + getSsn() + ", money: " + getMoney().getAmount();
    }

}