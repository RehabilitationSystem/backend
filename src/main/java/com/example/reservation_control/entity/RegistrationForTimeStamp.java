package com.example.reservation_control.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class RegistrationForTimeStamp {



        private Integer registrationId;

        @Column(name = "patient_id")
        private Integer patientId;

        @Column(name = "reservation_id")
        private Integer reservationId;

        @Column(name = "registration_time")
        private Long registrationTime;

        @Column(name = "status")
        private String status;


        public Integer getRegistrationId() {
            return registrationId;
        }

        public void setRegistrationId(Integer registrationId) {
            this.registrationId = registrationId;
        }

        public Integer getPatientId() {
            return patientId;
        }

        public void setPatientId(Integer patientId) {
            this.patientId = patientId;
        }

        public Integer getReservationId() {
            return reservationId;
        }

        public void setReservationId(Integer reservationId) {
            this.reservationId = reservationId;
        }

        public Long getRegistrationTime() {
            return registrationTime;
        }

        public void setRegistrationTime(Long registrationTime) {
            this.registrationTime = registrationTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }




    public RegistrationForTimeStamp(Registration r) {
        this.registrationId = r.getRegistrationId();
        this.patientId = r.getPatientId();
        this.reservationId = r.getReservationId();
        this.status = r.getStatus();
    }
}
