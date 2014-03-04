package spring4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import spring4.domain.Person

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

    @RequestMapping(value = "/savePerson", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person savePerson(@RequestBody Map params) {
        Person person = new Person(params)
        personService.save(person)
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List people() {
        personService.list()
    }


}
