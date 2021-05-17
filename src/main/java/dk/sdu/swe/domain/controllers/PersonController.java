package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.PersonDAOImpl;
import dk.sdu.swe.domain.models.Person;
import dk.sdu.swe.domain.persistence.IPersonDAO;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

public class PersonController {

    private IPersonDAO personDAO;
    private static PersonController instance;

    private PersonController() {
        this.personDAO = PersonDAOImpl.getInstance();
    }

    public static PersonController getInstance() {
        if (instance == null) {
            instance = new PersonController();
        }
        return instance;
    }

    public List<Person> getAll() {
        List<Person> people = PersonDAOImpl.getInstance().getAll();
        people.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return people;
    }

    public List<Person> search(String searchTerm) {
        return PersonDAOImpl.getInstance().searchByName(searchTerm);
    }

    public void delete(Person person) {
        PersonDAOImpl.getInstance().delete(person);
    }

    public Person createPerson(String name, String image, String email, ZonedDateTime bday) {
        if (image == null) {
            image = "https://via.placeholder.com/150";
        }
        Person person = new Person(name, image, bday);
        person.putContactDetail("email", email);
        personDAO.save(person);
        return person;
    }

    public Person createPerson(String name, String image, ZonedDateTime bday) {
        Person person = new Person(name, image, bday);
        personDAO.save(person);
        return person;
    }

    public void update(Person personObj) {
        personDAO.update(personObj);
    }
}
