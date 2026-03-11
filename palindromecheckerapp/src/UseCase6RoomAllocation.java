import java.util.*;

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

}

class RoomInventory {

    private Map<String, Integer> inventory;

    RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single", 2);
        inventory.put("Suite", 1);

    }

    boolean isAvailable(String roomType) {

        return inventory.getOrDefault(roomType, 0) > 0;

    }

    void decrement(String roomType) {

        inventory.put(roomType, inventory.get(roomType) - 1);

    }

}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;
    private Map<String, Integer> roomCounters;

    RoomAllocationService() {

        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
        roomCounters = new HashMap<>();

    }

    void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.roomType;

        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for " + reservation.guestName);
            return;
        }

        String roomId = generateRoomId(roomType);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.decrement(roomType);

        System.out.println("Booking confirmed for Guest: " + reservation.guestName +
                ", Room ID: " + roomId);

    }

    private String generateRoomId(String roomType) {

        int count = roomCounters.getOrDefault(roomType, 0) + 1;
        roomCounters.put(roomType, count);

        return roomType + "-" + count;

    }

}

public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.add(new Reservation("Abhi", "Single"));
        requestQueue.add(new Reservation("Subha", "Single"));
        requestQueue.add(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService service = new RoomAllocationService();

        System.out.println("Room Allocation Processing");

        while (!requestQueue.isEmpty()) {

            Reservation reservation = requestQueue.poll();

            service.allocateRoom(reservation, inventory);

        }

    }

}