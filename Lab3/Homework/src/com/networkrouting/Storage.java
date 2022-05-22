package com.networkrouting;

public interface Storage {
       int getCapacityStorage();
       default int storage(String type, int storage) {
              switch(type)
              {
              case "megabyte" :
                     return storage*1024;
              case "kilobyte" :
                     return storage*1024*1024;
              case "byte" :
                     return storage*1024*1024*1024;
              default: return storage;
              }
       }
}
