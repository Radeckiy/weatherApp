package com.testtasks.weatherApp.jsonModels.weatherstackModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temperature",
        "wind_speed",
        "pressure",
        "humidity"
})
@JsonRootName("location")
public class CurrentWeather {
    @JsonProperty("temperature") private Integer temperature;
    @JsonProperty("wind_speed") private Integer wind_speed;
    @JsonProperty("pressure") private Integer pressure;
    @JsonProperty("humidity") private Integer humidity;

    @JsonCreator
    public CurrentWeather(@JsonProperty("temperature") Integer temperature, @JsonProperty("wind_speed") Integer wind_speed,
                          @JsonProperty("pressure") Integer pressure, @JsonProperty("humidity") Integer humidity) {
        this.temperature = temperature;
        this.wind_speed = wind_speed;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @JsonGetter("temperature")
    public Integer getTemperature() {
        return temperature;
    }

    @JsonSetter("temperature")
    public void setTemperature(@JsonProperty("temperature") Integer temperature) {
        this.temperature = temperature;
    }

    @JsonGetter("wind_speed")
    public Integer getWind_speed() {
        return wind_speed;
    }

    @JsonSetter("wind_speed")
    public void setWind_speed(@JsonProperty("wind_speed") Integer wind_speed) {
        this.wind_speed = wind_speed;
    }

    @JsonGetter("pressure")
    public Integer getPressure() {
        return pressure;
    }

    @JsonSetter("pressure")
    public void setPressure(@JsonProperty("pressure") Integer pressure) {
        this.pressure = pressure;
    }

    @JsonGetter("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonSetter("humidity")
    public void setHumidity(@JsonProperty("humidity") Integer humidity) {
        this.humidity = humidity;
    }
}
