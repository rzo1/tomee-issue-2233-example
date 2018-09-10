package my.sample.jee.ws.xto;

import java.io.Serializable;
import java.util.Date;

public class DemoResponse implements Serializable {

    private String name;
    private Date theDate;

    public DemoResponse() {
        //needed to please cfx
    }

    public DemoResponse(String name, Date theDate) {
        this.name = name;
        this.theDate = theDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTheDate() {
        return theDate;
    }

    public void setTheDate(Date theDate) {
        this.theDate = theDate;
    }
}
