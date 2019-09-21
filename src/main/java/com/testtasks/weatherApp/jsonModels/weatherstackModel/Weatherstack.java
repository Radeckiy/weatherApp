package com.testtasks.weatherApp.jsonModels.weatherstackModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "current"
})
public class Weatherstack {
    @JsonProperty("current") private CurrentWeather current;

    @JsonCreator
    public Weatherstack(@JsonProperty("current") CurrentWeather current) {
        this.current = current;
    }

    @JsonGetter("current")
    public CurrentWeather getCurrent() {
        return current;
    }

    @JsonSetter("current")
    public void setCurrent(@JsonProperty("current") CurrentWeather current) {
        this.current = current;
    }
}
