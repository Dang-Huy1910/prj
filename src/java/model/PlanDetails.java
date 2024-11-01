/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class PlanDetails {
    private int pdid;
    private PlanHeader planHeader;
    private Shift shift;
    private java.sql.Date date;
    private int quantity;

    public PlanDetails(int pdid, PlanHeader planHeader, Shift shift, Date date, int quantity) {
        this.pdid = pdid;
        this.planHeader = planHeader;
        this.shift = shift;
        this.date = date;
        this.quantity = quantity;
    }

    public int getPdid() {
        return pdid;
    }

    public void setPdid(int pdid) {
        this.pdid = pdid;
    }

    public PlanHeader getPlanHeader() {
        return planHeader;
    }

    public void setPlanHeader(PlanHeader planHeader) {
        this.planHeader = planHeader;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
