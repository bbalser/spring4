package spring4

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SimpleController {

    @RequestMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody Map params) {
        def numbers = params.numbers as List
        numbers.sum()
    }

}
