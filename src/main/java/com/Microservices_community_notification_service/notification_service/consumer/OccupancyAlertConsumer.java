package com.Microservices_community_notification_service.notification_service.consumer;

import com.Microservices_community_notification_service.notification_service.model.OccupancyAlertEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OccupancyAlertConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "center-occupancy-alert", groupId = "notification-group")
    public void receiveAlert(ConsumerRecord<String, String> record) {
        try {
            String json = record.value();
            OccupancyAlertEvent event = objectMapper.readValue(json, OccupancyAlertEvent.class);

            System.out.println("🚨 ALERTA: Centro comunitário com ocupação total!");
            System.out.println("🏠 Centro: " + event.getName());
            System.out.println("📍 Localização: " + event.getLocation());
            System.out.println("👥 Ocupação: " + event.getOccupancy() + "/" + event.getCapacity());
            System.out.println("📧 Notificação enviada para coordenadores ✅");

        } catch (Exception e) {
            System.err.println("❌ Erro ao processar evento Kafka: " + e.getMessage());
        }
    }
}
