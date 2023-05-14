package sandro.objects;

public class ParkingProcess implements Runnable {
    private final Parking parkingLot;

    public ParkingProcess(Parking parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        parkingLot.park();
    }
}
