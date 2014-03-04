package spring4.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String firstName
    String lastName

}
