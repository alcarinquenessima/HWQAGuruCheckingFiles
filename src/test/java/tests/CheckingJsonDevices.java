package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class CheckingJsonDevices {
    private final ClassLoader cl = getClass().getClassLoader();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void openingFileJSON() throws Exception {
        try (InputStream is = cl.getResourceAsStream("devices.json")) {
            if (is == null) {
                Assertions.fail("Resource 'devices.json' not found");
                return;
            }

            try (Reader reader = new InputStreamReader(is)) {

                JsonNode actual = objectMapper.readTree(reader);

                Assertions.assertEquals("Apple", actual.get("brand").asText());
                Assertions.assertEquals(2023, actual.get("releaseYear").asInt());

                JsonNode inner = actual.get("specifications");
                JsonNode dimensionsNode = inner.get("dimensions");
                Assertions.assertEquals("147.5 mm", dimensionsNode.get("height").asText());

                JsonNode displayNode = inner.get("display");
                Assertions.assertEquals("Super Retina XDR", displayNode.get("type").asText());

                Assertions.assertEquals("iOS 17", inner.get("os").asText());

                JsonNode colorsArray = actual.get("colors");
                Assertions.assertEquals("Space Black", colorsArray.get(0).asText());
            }
        }
    }
}
