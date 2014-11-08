package com.poornama.data.objects;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dedunu on 10/21/14.
 */
@Entity
@Table(name = "EmployeeAttendance", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "employeeId"}))
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "date")
    private Date date;
    private boolean attendance;
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

}
