package com.testtasks.weatherApp.jsonModels.openWeatherModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "speed"
})
@JsonRootName("wind")
public class Wind {
    @JsonProperty("speed") private String speed;

    @JsonCreator
    public Wind(@JsonProperty("speed") String speed) {
        this.speed = speed;
    }

    @JsonGetter("speed")
    public String getSpeed() {
        return speed;
    }

    @JsonSetter("speed")
    public void setSpeed(@JsonProperty("speed") String speed) {
        this.speed = speed;
    }
}
