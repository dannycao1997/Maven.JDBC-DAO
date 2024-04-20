package daos;

// https://dzone.com/articles/building-simple-data-access-layer-using-jdbc

public class Cars {

    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private String vin;

    public Cars(){
    }

    public Cars(int id, String make, String model, int year, String color, String vin){
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public Cars(String make, String model, int year, String color, String vin){
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear(){
        return year;
    }

    public String getColor(){
        return color;
    }

    public String getVin(){
        return vin;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setVin(String vin){
        this.vin = vin;
    }

    public int getId() {
        return id;
    }
}
