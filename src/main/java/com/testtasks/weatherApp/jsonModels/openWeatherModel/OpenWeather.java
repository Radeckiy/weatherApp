package com.testtasks.weatherApp.jsonModels.openWeatherModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "main",
        "wind"
})
public class OpenWeather {
    @JsonProperty("main") private MainWeather main;
    @JsonProperty("wind") private Wind wind;

    @JsonCreator
    public OpenWeather(@JsonProperty("main") MainWeather main, @JsonProperty("wind") Wind wind) {
        this.main = main;
        this.wind = wind;
    }

    @JsonGetter("main")
    public MainWeather getMain() {
        return main;
    }

    @JsonSetter("main")
    public void setMain(@JsonProperty("main") MainWeather main) {
        this.main = main;
    }

    @JsonGetter("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonSetter("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
