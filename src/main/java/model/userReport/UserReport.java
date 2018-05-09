package model.userReport;

import com.sun.jmx.snmp.EnumRowStatus;
import model.EventStatus;

public class UserReport {
    private int idUser;
    private int idReport;
    private EventStatus status;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public static class Build {
        UserReport userReport = new UserReport();

        public Build setIdUser(int idUser){
            userReport.setIdUser(idUser);
            return this;
        }

        public Build setIdReport(int idReport){
            userReport.setIdReport(idReport);
            return this;
        }

        public Build setStatus(EventStatus status){
            userReport.setStatus(status);
            return this;
        }

        public UserReport build(){
            return userReport;
        }
    }
}
