import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * Created by TimoHäckel on 21.02.2017.
 */
public class TempDataTest {
    private static final String URI = "http://localhost:8091/";
    private static final String PATH = "tempdata/insert";

    @Test
    public void testTempDataInsert() throws Exception {
        String sensorID = "TempSensorTest";
        double temperature = 25.634;
        String query = URI + PATH +
                "?srcid=" + sensorID +
                "&temperature=" + temperature;

        RestTemplate restTemplate = new RestTemplate();

        String ret = restTemplate.getForObject(query, String.class);
        System.out.println(ret);
    }
}
