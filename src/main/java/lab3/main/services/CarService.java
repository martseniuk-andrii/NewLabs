package lab3.main.services;

import lab3.main.models.Car;
import lab3.main.models.Rental;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {
    private final List<Car> cars;

    public CarService(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Finds the car with the highest mileage from the collection of cars.
     *
     * @param cars the collection of cars
     * @return the car with the highest mileage
     */
    public Car findCarWithHighestMileage() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getMileage))
                .orElse(null);
    }

    /**
     * Filters and returns a list of cars that are older than a specified number of years.
     *
     * @param cars the collection of cars
     * @param minAge the minimum age of the car in years
     * @return a list of cars older than the specified number of years
     */
    public List<Car> getCarsOlderThan(int minAge) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return cars.stream()
                .filter(car -> (currentYear - car.getYearOfManufacture()) > minAge)
                .collect(Collectors.toList());
    }

    /**
     * Return one free car or null
     *
     * @param cars the collection of cars
     * @param rentals list of Rental
     * @return a free car
     */
    public Car getOneFreeCar(List<Rental> rentals) {
        for (Car car : cars) {
            boolean isCarRented = rentals.stream().anyMatch(rental1 -> rental1.getCar().equals(car) && LocalDate.now().isBefore(rental1.getEndDate()));
            if (!isCarRented){
                return car;
            }
        }
        return null;
    }

}

