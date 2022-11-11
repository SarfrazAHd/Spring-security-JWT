package com.learn.SpringApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRequest {
    private String RequestURL;
    private String RequestMethod;
    private String RequestBody;
    private String StartTime;
    private String EndTime;
    private Long TimeTaken;
    private String Response;
    private String ResponseStatus;
    private String Error;

    @Override
    public String toString() {
        return "AppRequest{" +
                " RequestURL=" + RequestURL +
                "| RequestMethod=" + RequestMethod +
                "| RequestBody=" + RequestBody +
                "| StartTime=" + StartTime +
                "| EndTime=" + EndTime +
                "| TimeTakenInMS=" + TimeTaken +
                "| Response=" + Response +
                "| ResponseStatus=" + ResponseStatus +
                "| Error=" + Error
                + "}";
    }
}
