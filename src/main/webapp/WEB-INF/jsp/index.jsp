<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <!-- Подключаем bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <c:url value="/css/main.css" var="jstlCss"/>
    <link href="${jstlCss}" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/suggestions-jquery@19.8.0/dist/css/suggestions.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/suggestions-jquery@19.8.0/dist/js/jquery.suggestions.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg" style="background-color: darksalmon;">
    <div class="container">
        <p class="mb-1 text-white" style="font-size: 26pt;">Погодное приложение</p>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col">
            <div class="row mt-3">
                <div class="col">
                    <form action="getWeatherServlet" method="get">
                        <div class="form-group">
                            <label class="" style="font-size: 14pt;" for="weatherService">Погодный сервис:</label>
                            <select class="form-control" size="1" name="weatherService" id="weatherService">
                                <c:choose>
                                    <c:when test="${weatherServiceSaved != null}">
                                        <c:choose>
                                            <c:when test="${weatherServiceSaved.equals('weatherstack')}">
                                                <option value="openWeather">Open Weather</option>
                                                <option selected value="weatherstack">Weatherstack</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option selected value="openWeather">Open Weather</option>
                                                <option value="weatherstack">Weatherstack</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <option selected value="openWeather">Open Weather</option>
                                        <option value="weatherstack">Weatherstack</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>

                            <label class="mt-2" style="font-size: 14pt;" for="city">Ваш город:</label>
                            <input class="form-control" id="city" name="city" value="${potentialCity}"
                                   placeholder="Введите ваш город.." />
                            <script>
                                $("#city").suggestions({
                                    token: "8844aa96e79e47f1d4ecfa3e580fce0926f5e8ea",
                                    type: "ADDRESS",
                                    hint: false,
                                    bounds: "city",
                                    constraints: {
                                        label: "",
                                        locations: {city_type_full: "город"}
                                    },
                                    onSelect: function (suggestion) {
                                        document.getElementById('city').value = suggestion.data.city;
                                    }
                                });
                            </script>
                        </div>

                        <button class="btn text-white" style="background-color: darksalmon;" type="submit">Получить
                            погоду!
                        </button>
                    </form>
                </div>
            </div>

            <div class="mt-4 row">
                <div class="col">
                    <div class="card bg-light">
                        <c:if test="${openWeatherData != null}">
                            <div class="card-header">
                                <h2>Погода</h2>
                            </div>
                            <div class="card-body">
                                <h3 class="card-title">Город: ${weatherCity}</h3>
                                <hr/>
                                <h2>Температура: ${openWeatherData.getMain().getTemp()} С°</h2>
                                <h2>Ветер: ${openWeatherData.getWind().getSpeed()} м/с</h2>
                                <h2>Давление: ${openWeatherData.getMain().getPressure()} мм. рт. ст.</h2>
                                <h2>Влажность: ${openWeatherData.getMain().getHumidity()}%</h2>
                            </div>
                        </c:if>

                        <c:if test="${WeatherstackData != null}">
                            <div class="card-header">
                                <h2 class="card-title">Погода: </h2>
                            </div>
                            <div class="card-body">
                                <h3 class="card-title">Город: ${weatherCity}</h3>
                                <hr/>
                                <h2>Температура: ${WeatherstackData.getCurrent().getTemperature()} С°</h2>
                                <h2>Ветер: ${WeatherstackData.getCurrent().getWind_speed()} м/с</h2>
                                <h2>Давление: ${WeatherstackData.getCurrent().getPressure()} мм. рт. ст.</h2>
                                <h2>Влажность: ${WeatherstackData.getCurrent().getHumidity()}%</h2>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>