package com.shchayuk.library.SpringBoot.util;

import com.shchayuk.library.SpringBoot.models.Person;
import com.shchayuk.library.SpringBoot.servicies.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "", "No");

        if (personService.showPersonByName(person.getName()).isPresent()){
            errors.rejectValue("name", "","This name already exists");
        }

    }
}
