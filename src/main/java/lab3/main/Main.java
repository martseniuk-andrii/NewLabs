package lab3.main;
import lab3.main.models.Car;
import lab3.main.models.Rental;
import lab3.main.models.Renter;
import lab3.main.serializers.IEntitySerializer;
import lab3.main.serializers.impl.JsonEntitySerializer;
import lab3.main.serializers.impl.XmlEntitySerializer;
import lab3.main.serializers.impl.YamlEntitySerializer;
import lab3.main.services.CarService;
import lab3.main.services.RenterService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        File carsDataFile = Paths.get("./data/cars.yaml").toFile();
        File renterDataFile = Paths.get("./data/renter.xml").toFile();
        File rentalsDataFile = Paths.get("./data/rental.json").toFile();

        IEntitySerializer<Car> carsSerializer = new YamlEntitySerializer<>();
        IEntitySerializer<Renter> rentersSerializer = new XmlEntitySerializer<>();
        IEntitySerializer<Rental> rentalsSerializer = new JsonEntitySerializer<>();

        List<Renter> renters = Arrays.stream(rentersSerializer.deserializeArray(renterDataFile, Renter[].class)).collect(Collectors.toList());
        List<Car> cars = Arrays.stream(carsSerializer.deserializeArray(carsDataFile, Car[].class)).collect(Collectors.toList());
        List<Rental> rentals = Arrays.stream(rentalsSerializer.deserializeArray(rentalsDataFile, Rental[].class)).collect(Collectors.toList());

        CarService carService = new CarService(cars);
        RenterService renterService = new RenterService(renters);

        System.out.println("Rentals" + rentals);

        File newCarsDataFile = Paths.get("./data/new-cars.yaml").toFile();
        File newRenterDataFile = Paths.get("./data/new-renter.xml").toFile();
        File newRentalsDataFile = Paths.get("./data/new-rental.json").toFile();

        carsSerializer.serializeArray(cars.toArray(new Car[]{}), newCarsDataFile);
        rentersSerializer.serializeArray(renters.toArray(new Renter[]{}), newRenterDataFile);
        rentalsSerializer.serializeArray( rentals.toArray(new Rental[]{}), newRentalsDataFile);
        System.out.println("all ok");
    }
}