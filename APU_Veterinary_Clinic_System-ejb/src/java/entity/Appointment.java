/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.constant.AppointmentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Jackson Tai
 */
@Entity
@Table(name="APPOINTMENT")
@Data
@NoArgsConstructor
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="APPOINTMENT_ID")
    private String appointmentId;

    @Column(name="APPOINTMENT_DATE")
    private Date appointmentDate;

    @Column(name="APPOINTMENT_STATUS")
    private AppointmentStatus appointmentStatus;

    public Appointment(Date appointmentDate, AppointmentStatus appointmentStatus) {
        this.appointmentDate = appointmentDate;
        this.appointmentStatus = appointmentStatus;
    }

}
