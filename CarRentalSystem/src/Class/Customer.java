public class Customer{

}

/*
 * Function name: register
 * 
 * What it does:
 *  1. Register customer according to user data storage format
 *  2. Write into database Customer.txt
 */

/*
 * Function name: login
 * 
 * Discuss with Bryan: Is this function supposed to be using the login function from user class
 * 
 * What it does:
 *  1. Read the input
 *  2. Read the database Customer.txt
 *  3. Bring customer to the next page
 */

/*
 * Function name: viewCatalog
 * 
 * What it does:
 * 1. Let Customer to browse available car
 */

/*
 * Function name: changePersonalInfo
 * 
 * Discuss with Bryan: 2 ways to change info
 *    First: Use buttons to let customer choose what info to change
 *    
 *    Second: Use a single page to let customer change every info, but maybe
 *    we can set the text field to contain the old information so customer can leave
 *    it if they only want to change certain info
 * 
 * What it does:
 *  1. Read Customer.txt
 *  2. Overwrite the old information to new information into Customer.txt
 */

/*
 * Function name: bookCar
 * 
 * Discuss with Bryan: According to README.MD, you mentioned that we can try to 
 * make the approval done automatically, so can I assume that after customer selected
 * the car and date, then it proceeds to payment page.
 *
 * What it does:
 *  1. Show the information of car, amount, rent date, return date
 *  2. After confirmation, send a notification (either via email or text message)
 *  3. Then, remove date from car catalog (not sure about this)
 */

/*
 * Function name: returnCar
 * 
 * What it does:
 *  1. Let the dealer approve the return
 *  2. If approved, send a notification, then, add the date to car catalog?
 *  3. If rejected, semd a notification with a fine imposed
 */

/*
 * Function name: deleteAccount
 * 
 * What it does:
 *  1. Show a page asking for confirmation (confirm / cancel)
 *  2. If confirmed, read Customer.txt
 *  3. Remove everything about the customer
 */
