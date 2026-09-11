class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] newSeats = seatNumbers.clone();
        newSeats[index] = newSeat;
        return new BookingReceipt(bookingId, newSeats);
    }

    public String getBookingId() {
        return bookingId;
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

class SettlementProcessor {
    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0, nullSkipped = 0, group = 0, individual = 0;
        for (BookingReceipt r : receipts) {
            if (r == null) { nullSkipped++; continue; }
            processed++;
            if (r instanceof GroupBookingReceipt) group++;
            else individual++;
        }
        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt[] batch = {
            new GroupBookingReceipt("CH-2002", new String[]{"B1","B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
    }
}
