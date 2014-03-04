package spring4.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@Entity
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    Long id

    @XmlElement
    String firstName
    @XmlElement
    String lastName

}
