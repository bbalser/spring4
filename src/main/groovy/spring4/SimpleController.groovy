package spring4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import spring4.domain.Person

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper
import javax.xml.bind.annotation.XmlRootElement

@Controller
class SimpleController {

    @Autowired
    AdditionService additionService

    @Autowired
    PersonService personService

    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Map params) {
        additionService.add(params.numbers)
    }

    @RequestMapping(value = "/savePerson", consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
            method = RequestMethod.POST, produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    @ResponseBody
    public Person savePerson(@RequestBody Person person) {
        personService.save(person)
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> peopleInJson() {
        personService.list()
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public People peopleInXml() {
        new People(people: personService.list())
    }

}


@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "people")
class People {

    @XmlElement(name = "person")
    List<Person> people

}