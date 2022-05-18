package ru.mikhail.determinatorapp.common;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDTO implements Parcelable {
    private String fio;
    private String email;
    private String universityName;
    private String groupName;

    public UserDTO() {
    }

    public UserDTO(String fio, String email, String universityName, String groupName) {
        this.fio = fio;
        this.email = email;
        this.universityName = universityName;
        this.groupName = groupName;
    }

    protected UserDTO(Parcel in) {
        fio = in.readString();
        email = in.readString();
        universityName = in.readString();
        groupName = in.readString();
    }

    public static final Creator<UserDTO> CREATOR = new Creator<>() {
        @Override
        public UserDTO createFromParcel(Parcel in) {
            return new UserDTO(in);
        }

        @Override
        public UserDTO[] newArray(int size) {
            return new UserDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fio);
        dest.writeString(email);
        dest.writeString(universityName);
        dest.writeString(groupName);
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}