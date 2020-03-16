package server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
public class Task extends Persistent {

    @NotBlank
    @Size(min = 5, max = 255)
    private String kurztext;

    @NotNull
    @Min(1)
    @Max(3)
    private int prio;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date faellig;

    @Formula("now() > faellig and not erledigt")
    private boolean ueberfaellig;

    @NotNull
    private boolean erledigt;

    @Lob
    @JsonIgnore
    private String beilage;

    @Formula("beilage is not null")
    private boolean mitBeilage;


    public String getKurztext() {
        return kurztext;
    }


    public void setKurztext(String kurztext) {
        this.kurztext = kurztext;
    }


    public int getPrio() {
        return prio;
    }


    public void setPrio(int prio) {
        this.prio = prio;
    }


    public Date getFaellig() { return faellig; }


    public void setFaellig(Date faellig) { this.faellig = faellig; }


    public boolean isUeberfaellig() { return ueberfaellig; }


    public void setUeberfaellig(boolean ueberfaellig) {}


    public boolean isErledigt() {
        return erledigt;
    }


    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }


    @JsonIgnore
    public String getBeilage() {
        return beilage;
    }

    @JsonProperty
    public void setBeilage(String beilage) {
        this.beilage = beilage;
    }


    public boolean isMitBeilage() {
        return mitBeilage;
    }

}
