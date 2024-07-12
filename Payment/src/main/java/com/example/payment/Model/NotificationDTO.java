package com.example.payment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotificationDTO {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String message;
private Long receiverID;
private String typeNotification;

public NotificationDTO(){}
public NotificationDTO(Long receiverID, String message) {
    this.receiverID = receiverID;
    this.message = message;
}

//Getters et Setters
public Long getId() {
    return id;
}

public String getMessage() {
    return message;
}

public void setMessage(String message) {
    this.message = message;
}

public Long getReceiverID() {
    return receiverID;
}

public void setReceiverID(Long receiverID) {
    this.receiverID = receiverID;
}

public String getTypeNotification() {
    return typeNotification;
}

public void setTypeNotification(String typeNotification) {
    this.typeNotification = typeNotification;
}
}
