package model.report;

import model.EventStatus;
import model.IdContainer;

import java.util.Date;
import java.util.Objects;

public class Report extends IdContainer {
    private String subject;
    private String place;
    private Date time;
    private int register;
    private int idSpeaker;
    private EventStatus status;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

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

    public int getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(int id_speaker) {
        this.idSpeaker = id_speaker;
    }

    public static class Builder {
        Report report = new Report();

        public Builder setId(int id) {
            report.setId(id);
            return this;
        }

        public Builder setSubject(String subject) {
            report.setSubject(subject);
            return this;
        }

        public Builder setPlace(String place) {
            report.setPlace(place);
            return this;
        }

        public Builder setTime(Date time) {
            report.setTime(time);
            return this;
        }

        public Builder setRegister(int register) {
            report.setRegister(register);
            return this;
        }

        public Builder setIdSpeaker(int idSpeaker) {
            report.setIdSpeaker(idSpeaker);
            return this;
        }

        public Builder setStatus(EventStatus status) {
            report.setStatus(status);
            return this;
        }

        public Report build() {
            return report;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return register == report.register &&
                idSpeaker == report.idSpeaker &&
                Objects.equals(subject, report.subject) &&
                Objects.equals(place, report.place) &&
                Objects.equals(time, report.time) &&
                status == report.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(subject, place, time, register, idSpeaker, status);
    }
}