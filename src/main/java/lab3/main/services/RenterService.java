package lab3.main.services;


import lab3.main.models.Renter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to renters.
 */
public class RenterService {

    private final List<Renter> renters;

    public RenterService(List<Renter> renters) {
        this.renters = renters;
    }

    /**
     * Sorts the list of renters alphabetically by their last name.
     *
     * @return the sorted list of renters by last name
     */
    public List<Renter> sortRentersByLastName() {
        return renters.stream()
                .sorted(Comparator.comparing(Renter::getLastName))
                .collect(Collectors.toList());
    }

}