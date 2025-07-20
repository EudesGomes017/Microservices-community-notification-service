package com.Microservices_community_notification_service.notification_service.model;

import lombok.Data;

@Data
public class OccupancyAlertEvent {

    private String centerId;
    private String name;
    private String location;
    private int capacity;
    private int occupancy;
}
