package com.farhad.giveahand.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Areas {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("areas")
    @Expose
    private List<Area> areas = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

}
