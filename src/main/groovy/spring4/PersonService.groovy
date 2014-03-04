package spring4

import org.springframework.stereotype.Service
import spring4.domain.Person

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
@Transactional
class PersonService {

    @PersistenceContext
    EntityManager entityManager;

    Person save(Person person) {
        entityManager.persist(person)
        person
    }

    List save(List people) {
        people.each { entityManager.persist(it) }
        people
    }

    List list() {
        entityManager.createQuery("from Person").resultList
    }

}
