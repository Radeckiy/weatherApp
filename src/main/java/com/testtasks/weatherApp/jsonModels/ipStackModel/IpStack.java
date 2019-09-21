package com.testtasks.weatherApp.jsonModels.ipStackModel;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "city"
})
public class IpStack {
    @JsonProperty("city") private String city;

    @JsonCreator
    public IpStack(@JsonProperty("city") String city) {
        this.city = city;
    }

    @JsonGetter("city")
    public String getCity() {
        return city;
    }

    @JsonSetter("city")
    public void setCity(String city) {
        this.city = city;
    }
}
