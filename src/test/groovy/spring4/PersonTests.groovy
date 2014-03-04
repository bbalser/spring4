package spring4

import config.WebAppConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import spring4.domain.Person

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = WebAppConfig)
@WebAppConfiguration
@Transactional
class PersonTests {

    @PersistenceContext
    EntityManager entityManager

    @Test
    void personShouldHandleDynamicStaticMethodMapingToNamedQuery() {
        Person person1 = new Person(firstName: "Barney", lastName: "Rubble")
        Person person2 = new Person(firstName: "Fred", lastName: "Flintstone")
        [person1, person2].each { entityManager.persist(it) }

        def person = Person.findByFirstName("Barney")

        assert person1 == person
    }

    @Test
    void personShouldHandlFindAllMethodsAsWell() {
        Person person1 = new Person(firstName: "Barney", lastName: "Rubble")
        Person person2 = new Person(firstName: "Fred", lastName: "Flintstone")
        Person person3 = new Person(firstName: "Wilma", lastName: "Flintstone")
        [person1, person2, person3].each { entityManager.persist(it) }

        def result = Person.findAllByLastName("Flintstone")

        assert 2 == result.size()
        assert result.contains(person2)
        assert result.contains(person3)
    }



}
