package spring4

import config.WebAppConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
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
class PersonServiceTests {

    @PersistenceContext
    EntityManager entityManager

    @Autowired
    PersonService personService

    @Test
    void saveShouldPersistPerson() {
        Person person = new Person(firstName: "Johnny", lastName: "Eagle")
        personService.save(person)

        def people = entityManager.createQuery("from Person").resultList
        assert [person] == people
    }

    @Test
    void saveShouldSaveListOfPersons() {
        Person person1 = new Person(firstName: "Frank", lastName: "Burns")
        Person person2 = new Person(firstName: "Joe", lastName: "Walsh")
        personService.save([person1, person2])

        def people = entityManager.createQuery("from Person order by lastName").resultList
        assert [person1, person2] == people
    }

    @Test
    void listShouldReturnAllPeople() {
        Person person1 = new Person(firstName: "Frank", lastName: "Burns")
        Person person2 = new Person(firstName: "Steve", lastName: "Guttenberg")

        [person1, person2].each { entityManager.persist(it) }

        def result = personService.list()
        assert 2 == result.size()
        assert result.contains(person1)
        assert result.contains(person2)
    }

}
