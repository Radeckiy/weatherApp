package com.testtasks.weatherApp;

import com.testtasks.weatherApp.jsonModels.ipStackModel.IpStack;
import com.testtasks.weatherApp.jsonModels.openWeatherModel.OpenWeather;
import com.testtasks.weatherApp.jsonModels.weatherstackModel.Weatherstack;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Controller
public class AppController {
    // экземпляр env для получения env переменных
    private final Environment env;

    // Spring сам достает env и присваивает его через этот конструктор
    public AppController(Environment env) {
        this.env = env;
    }

    /**
     * Как только пользователь первый раз заходит на сайт,
     * пытаемся определить его город по его ip,
     * в ином случае не делаем ничего, подразумевая,
     * что юзер введет город сам
     * */
    @RequestMapping("/")
    public String getIndexPage(Map<String, Object> model, @CookieValue(value = "city", defaultValue = "") String city,
                               @CookieValue(value = "weatherService", defaultValue = "") String weatherService) {

        if(city.length() == 0) {
           String potCity = getIpStack().getCity();
           if(!potCity.equals("none"))
               model.put("potentialCity", potCity);
        }
        else
            model.put("potentialCity", city);

        if(weatherService.length() != 0)
            model.put("weatherServiceSaved", weatherService);

        return "index";
    }

    /**
     * Получаем погоду
     * @param weatherService - название погодного сервиса (в формате String)
     * @param city - необходимый город на английском (в формате String)
     * @return в index.jsp появляется элемент с погодой
     */
    @RequestMapping("/weather")
    public String setWeatherIndexPage(Map<String, Object> model, @RequestParam("weatherService") String weatherService, @RequestParam("city") String city) {
        model.put("weatherCity", city);

        if(weatherService.equals("openWeather"))
            model.put("openWeatherData", getOpenWeather(city));
        else if(weatherService.equals("weatherstack"))
            model.put("WeatherstackData", getWeatherstack(city));

        return "forward:";
    }

    /**
     * Функция для получения объекта OpenWeather
     * @param city - необходимый город на английском (в формате String)
     * @return объект OpenWeather, если удалось получить, ИНАЧЕ NULL
     */
    private OpenWeather getOpenWeather(String city) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<OpenWeather> response = new RestTemplate().exchange(env.getProperty("open_weather.url_get_weather") +
                        "q=" + city + "&units=metric&appid=" + env.getProperty("open_weather.token"),
                HttpMethod.GET, new HttpEntity<OpenWeather>(headers), OpenWeather.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().getMain() != null)
            return response.getBody();

        return null;
    }

    /**
     * Функция для получения объекта Weatherstack
     * @param city - необходимый город на английском (в формате String)
     * @return объект Weatherstack, если удалось получить, ИНАЧЕ NULL
     */
    private Weatherstack getWeatherstack(String city) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<Weatherstack> response = new RestTemplate().exchange(env.getProperty("weatherstack.url_get_weather") +
                        "access_key=" + env.getProperty("weatherstack.token") + "&query=" + city,
                HttpMethod.GET, new HttpEntity<Weatherstack>(headers), Weatherstack.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null && response.getBody().getCurrent() != null)
            return response.getBody();

        return null;
    }

    /**
     * Делает запрос к api и отдает ip (IpStack)
     * Если не удалось, то в объекте IpStack будет "none"
     * @return объект типа IpStack
     */
    private IpStack getIpStack() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ResponseEntity<IpStack> response = new RestTemplate().exchange(env.getProperty("ipstack.url_get_user_location") +
                        "language=ru&access_key=" + env.getProperty("ipstack.token"),
                HttpMethod.GET, new HttpEntity<IpStack>(headers), IpStack.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null)
            return response.getBody();

        return new IpStack("none");
    }
}
