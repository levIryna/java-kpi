public class JournalEntry {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phone;
    private String address;

    public JournalEntry(String lastName, String firstName, String birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Студент: %s %s | ДН: %s | Тел: %s | Адреса: %s", 
                lastName, firstName, birthDate, phone, address);
    }
}