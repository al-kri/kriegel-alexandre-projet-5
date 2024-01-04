package com.openclassrooms.safetynetalerts.repository;

import com.openclassrooms.safetynetalerts.entity.Firestation;
import com.openclassrooms.safetynetalerts.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final List<Person> personList = JsonData.persons;

    @Override
    public void delete(String firstName, String lastName) {
        personList
                .stream()
                        .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                        .findFirst()
                        .ifPresentOrElse(person -> {
                            personList.remove(person);
                            log.info(firstName + " " + lastName + " successfully deleted");
                        }, () -> log.warn(firstName + " " + lastName + " is not present in database and can't be deleted"));
    }

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return personList
                .stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName))
                .toList();
    }

    @Override
    public List<Person> save(Person personAdded) {
        personList.add(personAdded);
        return personList;
    }

    @Override
    public List<Person> update(Person personUpdated) {
        final var personFound = new boolean []{false}  ;
        personList.stream()
                .filter(p -> p.getFirstName().equalsIgnoreCase(personUpdated.getFirstName()) && p.getLastName().equalsIgnoreCase(personUpdated.getLastName()))
                .forEach(person -> {
                    person.setAddress(personUpdated.getAddress());
                    person.setCity(personUpdated.getCity());
                    person.setZip(personUpdated.getZip());
                    person.setEmail(personUpdated.getEmail());
                    person.setPhone(personUpdated.getPhone());
                    personFound[0] = true;
                });

        if(personFound[0]) {
            log.info("Info of : " + personUpdated.getFirstName() + " " + personUpdated.getLastName() + " successfully updated");
        } else {
            log.warn(personUpdated.getFirstName() + " " + personUpdated.getLastName() + " not found in database and can't be updated");
        }
        return personList;
    }

    @Override
    public List<String> getPersonsEmailByCity(String city) {
        return personList.stream()
                .filter(p -> p.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail).toList();
    }

    public List<Person> findAllByFirestation(List<Firestation> firestationList) {
        return personList
                .stream()
                .filter(p -> firestationList.stream().map(Firestation::getAddress).toList().contains(p.getAddress()))
                .toList();
    }
}


