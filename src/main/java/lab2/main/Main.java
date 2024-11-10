package lab2.main;

import lab2.main.comparators.RenterFirstNameComparator;
import lab2.main.exceptions.CarNotFoundException;
import lab2.main.models.Car;
import lab2.main.models.Rental;
import lab2.main.models.Renter;
import lab2.main.services.CarService;
import lab2.main.services.RenterService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> cars = Arrays.asList(
                new Car("Toyota", "VIN1", "ABC123", 2010, 120000),
                new Car("Honda", "VIN2", "DEF456", 2015, 90000),
                new Car("Ford", "VIN3", "GHI789", 2005, 150000)
        );

        // Створення об'єктів Renter
        List<Renter> renters = Arrays.asList(
                new Renter("John", "Doe", "ID123", "DL123"),
                new Renter("Alice", "Smith", "ID456", "DL456"),
                new Renter("Bob", "Brown", "ID789", "DL789")
        );

        List<Rental> rentals = new ArrayList<>();
        CarService carService = new CarService(cars);
        RenterService renterService = new RenterService(renters);


        for (Renter renter : renters) {
            Car freeCar = carService.getOneFreeCar(rentals);
            if (freeCar == null) {
                throw new CarNotFoundException("Free car not found");
            }
            rentals.add(
                    Rental.Builder.builder()
                            .setCar(freeCar)
                            .setRenter(renter)
                            .setPricePerDay(200.0)
                            .setStartDate(LocalDate.now())
                            .setEndDate(LocalDate.now().plusDays(3))
                            .build()
            );
        }


        System.out.println("Rentals" + rentals);

        // 1. Знаходження машини з найбільшим пробігом
        Car carWithHighestMileage = carService.findCarWithHighestMileage();
        System.out.println("1");
        System.out.println("Car with the highest mileage: " + carWithHighestMileage);

        // 2. Сортування рентерів за прізвищем
        List<Renter> sortedRenters = renterService.sortRentersByLastName();
        System.out.println("2");
        System.out.println("Sorted renters by last name: " + sortedRenters);

        // 3. Пошук машин, старіших за 10 років
        List<Car> oldCars = carService.getCarsOlderThan(10);
        System.out.println("3");
        System.out.println("Cars older than 10 years: " + oldCars);

        // 4. Сортування рентерів за ім'ям за допомогою Comparator
        RenterFirstNameComparator firstNameComparator = new RenterFirstNameComparator();
        renters.sort(firstNameComparator);
        System.out.println("4");
        System.out.println("Sorted renters by first name: " + renters);
    }
}