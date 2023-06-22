package Assessment;

class Ticket {
    private int availableTickets;
    private Object lock = new Object();

    public Ticket(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    public void bookTicket(String passengerName, int numTickets) {
        synchronized (lock) {
            if (numTickets <= availableTickets) {
                System.out.println(passengerName + " successfully booked " + numTickets + " ticket");
                availableTickets -= numTickets;
            } else {
                System.out.println(passengerName + " failed to book tickets. Tickets are not available.");
            }
        }
    }
}
