import java.util.*;

class ticket {
    public String from;
    public String to;
    public String userName;
    public String userEmail;
    public double pricePaid;
    public String seatSection;
    
    public ticket()
    {}

    public ticket(String from, String to, String userName, String userEmail, double pricePaid, String seatSection) {
    this.from = from;
    this.to = to;
    this.userName = userName;
    this.userEmail = userEmail;
    this.pricePaid = pricePaid;
    this.seatSection = seatSection;
    }
        
    public String getFrom() {
    return from;
    }
        
    public String getTo() {
    return to;
    }
        
    public String getUserName() {
    return userName;
    }
        
    public String getUserEmail() {
    return userEmail;
    }
        
    public double getPricePaid() {
    return pricePaid;
    }
        
    public String getSeatSection() {
    return seatSection;
    }
        
    public void setSeatSection(String seatSection) {
    this.seatSection = seatSection;
    }
}

public class bookTicket extends ticket{
   
    public static int TOTAL_SEATS=10;
    public static double TICKET_PRICE=20.0;
    public static String[] SEAT_SECTIONS={"Section A", "Section B"};
    
    ticket tickets[]= new ticket[TOTAL_SEATS];
    public String[] seatAllocations = new String[TOTAL_SEATS];
    public int ticketCount = 0;

    public static void main(String[] args) {

        bookTicket ticketSystem = new bookTicket();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Train Ticket Booking System");
        System.out.println("London to France ticket price: $" + TICKET_PRICE);

        int choice;
        do {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Purchase Train Tickets");
            System.out.println("2. View Ticket Receipts");
            System.out.println("3. View Users by Section");
            System.out.println("4. Cancel User's ticket");
            System.out.println("5. Modify User's Seat");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter your first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter your last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter your email address: ");
                    String email = scanner.nextLine();
                    ticket ticket = ticketSystem.purchaseTicket("London", "France", firstName, lastName, email);
                    if (ticket != null) {
                        System.out.println("Ticket purchased:");
                        System.out.println("From: " + ticket.getFrom());
                        System.out.println("To: " + ticket.getTo());
                        System.out.println("User: " + ticket.getUserName());
                        System.out.println("Price Paid: $" + ticket.getPricePaid());
                        System.out.println("Seat Section: " + ticket.getSeatSection());
                    }
                    break;
                case 2:
                    System.out.println("Enter your full name: ");
                    String fullName = scanner.nextLine();
                    ticket viewedTicket = ticketSystem.viewTicket(fullName);
                    if (viewedTicket != null) {
                        System.out.println("From: " + viewedTicket.getFrom());
                        System.out.println("To: " + viewedTicket.getTo());
                        System.out.println("User: " + viewedTicket.getUserName());
                        System.out.println("Price Paid: $" + viewedTicket.getPricePaid());
                        System.out.println("Seat Section: " + viewedTicket.getSeatSection());
                    } else {
                        System.out.println("Ticket not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter section (A or B): ");
                    String section = scanner.nextLine();
                    ticketSystem.viewUsersBySection(section);
                    break;

                case 4:
                    System.out.println("Enter full name of the user to remove: ");
                    String userToRemove = scanner.nextLine();
                    ticketSystem.removeUser(userToRemove);
                    System.out.println("User removed.");
                    break;

                case 5:
                    System.out.println("Enter full name of the user to modify seat: ");
                    String userToModify = scanner.nextLine();
                    System.out.println("Enter new seat section (A or B): ");
                    String newSeatSection = scanner.nextLine();
                    ticketSystem.modifySeat(userToModify, newSeatSection);
                    System.out.println("User's seat modified.");
                    break;

                 case 6:
                     System.out.println("Thank you.----HAPPY JOURNEY!!---- Exiting...");
                     break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    }

    // Method to purchase a ticket
    public ticket purchaseTicket(String from, String to, String firstName, String lastName, String email) {
        if (ticketCount >= TOTAL_SEATS) {
            System.out.println("Sorry, all seats are booked.");
            return null;
        }
        ticketCount++;
        String userName = firstName + " " + lastName;
        String seatSection = SEAT_SECTIONS[ticketCount % SEAT_SECTIONS.length];
        tickets[ticketCount] = new ticket(from, to, userName, email, TICKET_PRICE, seatSection);
        System.out.println(ticketCount);
        seatAllocations[ticketCount] = userName;
        return tickets[ticketCount];
    }

    //Method to view ticket details
    public ticket viewTicket(String userName) {
        int i;
        for (i=1;i<=ticketCount;i++) {   
            try{
                if (tickets != null && tickets[i].getUserName().equals(userName)) {
                    return tickets[i];
                }
            }
            catch (NullPointerException e)
            {
                continue;
            }
        } 
        return null;
    }

    // Method to view users and their allocated seats by section
    public void viewUsersBySection(String section) {
        section=section.toUpperCase();
        System.out.println("Users in Section " + section + ":");
        section = "Section "+ section;
        for (int i = 1; i <= ticketCount; i++) {
            if (seatAllocations[i] != null && tickets[i].getSeatSection().equals(section)) {
                System.out.println("User: " + tickets[i].getUserName() + ", Seat Section: " + tickets[i].getSeatSection());
            }
        }
    }

    // Method to remove a user from the train
    public void removeUser(String userName) {
        //System.out.println(ticketCount);
        for (int i = 1; i <= ticketCount; i++) {
            if (tickets[i] != null && tickets[i].getUserName().equals(userName)) {
                tickets[i] = null;
                seatAllocations[i] = null;
                //ticketCount--;
                //System.out.println(ticketCount);
                return;
            }
        }
        System.out.println("User not found.");
    }

    // Method to modify a user's seat
    public void modifySeat(String userName, String newSeatSection) {
        newSeatSection=newSeatSection.toUpperCase();
        newSeatSection = "Section "+ newSeatSection;
        for (int i = 1; i <= ticketCount; i++) {
            if (tickets[i] != null && tickets[i].getUserName().equals(userName)) {
                seatAllocations[i] = newSeatSection;
                tickets[i].setSeatSection(newSeatSection);
                return;

            }
        }
        System.out.println("User not found.");
    }    
}
