package model.userMeeting;

import model.EventStatus;

public class UserMeeting {
    private int idUser;
    private int idMeeting;
    private EventStatus status;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(int idMeeting) {
        this.idMeeting = idMeeting;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public static class Builder {
        UserMeeting userMeeting = new UserMeeting();

        public Builder setIdUser(int idUser) {
            userMeeting.setIdUser(idUser);
            return this;
        }

        public Builder setIdMeeting(int idMeeting) {
            userMeeting.setIdMeeting(idMeeting);
            return this;
        }

        public Builder setStatus(EventStatus status) {
            userMeeting.setStatus(status);
            return this;
        }

        public UserMeeting build() {
            return userMeeting;
        }
    }
}
