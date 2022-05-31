package Storage.java;

public interface Storage {
    int getStorageCapacity();
    default int getStorageCapacityInMB() {
        return getStorageCapacity()*1024;
    }
    default int getStorageCapacityInKB() {return getStorageCapacityInMB()* 1024;}
    default int getStorageCapacityInB() {
        return getStorageCapacityInKB() * 1024;
    }

}
