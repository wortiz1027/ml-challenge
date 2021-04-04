package co.com.merdadolibre.challenge.domain;

import lombok.Data;

@Data
public class Message implements java.io.Serializable {

    private String id;
    private String correlation;
    private String name;
    private double distance;
    private String message;
    private String sequence;

}
