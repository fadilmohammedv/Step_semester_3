public class MovieTicket {
    private int seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(int seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}