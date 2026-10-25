package mate.academy;

import java.util.concurrent.Semaphore;

public class TicketBookingSystem {
    private int totalSeats;
    private Semaphore semaphore = new Semaphore(totalSeats);

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        if (totalSeats == 0) {
            return new BookingResult(user,false,"All seats for the session have been purchased");
        }
        try {
            semaphore.acquire();
            setTotalSeats(totalSeats - 1);
            semaphore.release();
            return new BookingResult(user,true,"Seat successfully purchased");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}
