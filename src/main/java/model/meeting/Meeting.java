package model.meeting;

import model.EventStatus;

import java.util.Date;
import java.util.Objects;

public class Meeting {
    private Date time;
    private String place;
    private int register;
    private EventStatus status;
    private int idReport;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRegister() {
        return register;
    }

    public void setRegister(int register) {
        this.register = register;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public static class Builder {
        Meeting meeting = new Meeting();

        public Builder setDate(Date time){
            meeting.setTime(time);
            return this;
        }

        public Builder setPlace(String place){
            meeting.setPlace(place);
            return this;
        }

        public Builder setRegister(int register){
            meeting.setRegister(register);
            return this;
        }

        public Builder setStasus(EventStatus stasus){
            meeting.setStatus(stasus);
            return this;
        }

        public Builder setIdReport(int idReport){
            meeting.setIdReport(idReport);
            return this;
        }

        public Meeting build(){
            return meeting;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return register == meeting.register &&
                idReport == meeting.idReport &&
                Objects.equals(time, meeting.time) &&
                Objects.equals(place, meeting.place) &&
                status == meeting.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, place, register, status, idReport);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "time=" + time +
                ", place='" + place + '\'' +
                ", register=" + register +
                ", status=" + status +
                ", idReport=" + idReport +
                '}';
    }
}
