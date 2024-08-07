package jsonModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Device {

    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("releaseYear")
    private Integer releaseYear;

    @JsonProperty("specifications")
    private Specifications specifications;

    @JsonProperty("colors")
    private List<String> colors;

    // Getters and Setters

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    // Inner class for Specifications
    public static class Specifications {
        @JsonProperty("dimensions")
        private Dimensions dimensions;

        @JsonProperty("os")
        private String os;

        // Getters and Setters

        public Dimensions getDimensions() {
            return dimensions;
        }

        public void setDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }
    }

    // Inner class for Dimensions
    public static class Dimensions {
        @JsonProperty("height")
        private String height;

        // Getters and Setters

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }
}
