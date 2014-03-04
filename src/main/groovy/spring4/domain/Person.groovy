package spring4.domain

import org.springframework.orm.jpa.EntityManagerHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import spring4.SpringBeanUtil

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Query
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@Entity
@NamedQueries([
    @NamedQuery(name = "Person.ByFirstName", query = "from Person where firstName = ?1"),
    @NamedQuery(name = "Person.ByLastName", query = "from Person where lastName = ?1")
])
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    Long id

    @XmlElement
    String firstName
    @XmlElement
    String lastName

    static $static_methodMissing(String name, args) {
        if (name.startsWith("findAll")) {
            createNamedQuery(name.substring(7), args).resultList
        } else if (name.startsWith("find")) {
            createNamedQuery(name.substring(4), args).singleResult
        } else {
            throw new NoSuchMethodException();
        }
    }

    private static createNamedQuery(queryName, args) {
        EntityManagerFactory entityManagerFactory = SpringBeanUtil.getBean(EntityManagerFactory)
        EntityManagerHolder holder = TransactionSynchronizationManager.getResource(entityManagerFactory)
        EntityManager entityManager = holder.getEntityManager()

        Query query = entityManager.createNamedQuery("Person.${queryName}")
        args.eachWithIndex { arg, index ->
            query.setParameter(index+1, arg)
        }
        query
    }

}
