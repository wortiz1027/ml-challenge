package co.com.merdadolibre.challenge.domain;

import lombok.Data;

@Data
public class ReportSatellites implements java.io.Serializable {

    private String name;
    private float distance;
    private String[] message;

}
