package com.poornama.api.objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @author dedunu
 */
@Entity
@Table(name = "EmployeeAttendance", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "employeeId"}))
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private boolean attendance;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "employeeId")
    private Employee employee;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the attendance
     */
    public boolean isAttendance() {
        return attendance;
    }

    /**
     * @param attendance the attendance to set
     */
    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
