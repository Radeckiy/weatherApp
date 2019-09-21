package com.testtasks.weatherApp.jsonModels.openWeatherModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temp",
        "pressure",
        "humidity"
})
@JsonRootName("main")
public class MainWeather {
    @JsonProperty("temp") private Integer temp;
    @JsonProperty("pressure") private Integer pressure;
    @JsonProperty("humidity") private Integer humidity;

    @JsonCreator
    public MainWeather(@JsonProperty("temp") Integer temp, @JsonProperty("pressure") Integer pressure, @JsonProperty("humidity") Integer humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @JsonGetter("temp")
    public Integer getTemp() {
        return temp;
    }

    @JsonSetter("temp")
    public void setTemp(@JsonProperty("temp") Integer temp) {
        this.temp = temp;
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
