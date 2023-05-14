package sandro.objects;

public class UnparkingProcess implements Runnable {
    private final Parking parkingLot;

    public UnparkingProcess(Parking parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.park();
    }
}
