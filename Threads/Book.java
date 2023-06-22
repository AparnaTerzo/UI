package Assessment;

public class Book {
    public static void main(String[] args) throws InterruptedException {
        Ticket reservation = new Ticket(9);


        Thread t1 = new Thread(() -> reservation.bookTicket("Aparna", 3));
        Thread t2 = new Thread(() -> reservation.bookTicket("Monisha", 4));
        Thread t3 = new Thread(() -> reservation.bookTicket("Dharunya", 3));


        t1.start();
        t2.start();

        t3.start();
    }
}
