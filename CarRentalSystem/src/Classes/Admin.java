package Classes;

public class Admin extends User {
    
    public Admin(String fullname, String identification, String dateOfBirth, String email, String contact, String username, String password) {
        super(fullname, identification, dateOfBirth, email, contact, username, password);
    }

    @Override
    public void login() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void logout() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void viewCatalog() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void saveData() {
        // TODO Auto-generated method stub
        
    }
    
    public void editCarInfo() {
        // TODO: Access the Car profile and change the Car details, require the fields of the Car class
    }

    public void checkRentalHistory() {
        // TODO: Read Customer.txt and get the history section of the field
    }

    /**
     * Name: approve
     * 
     * @param transactionType
     * 
     * What it does:
     *  1. Check what type of transaction is being done, booking or car return
     *  2. If booking, only approve if the car is actually available and is ready for rent, customer basic information are filled up, and customer have enough balance to pay for the entire rental period. Else reject
     *  3. If car return, only approve if the car is not damaged in any way. Else, impose fines. 
     */
    public void approve(String transactionType) {
        // TODO: Approve either the booking or return of the car
    }

    public void imposeFines() {
        // TODO: Get the result from approve, and if return car failed, impose fines to the customer, send notification and deduct from customer's balance
    }

    public void collectPayment() {
        // TODO: If approve successful and is booking, collect payment from customer.
    }

    public void requestReports() {
        // TODO: Request reports generated from the Store
    }

}
