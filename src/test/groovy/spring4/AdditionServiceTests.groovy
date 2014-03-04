package spring4

import config.WebAppConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = WebAppConfig)
@WebAppConfiguration
class AdditionServiceTests {

    @Autowired
    AdditionService additionService

    @Test
    void addShouldReturn1WhenGiven1() {
        assert 1 == additionService.add([1])
    }

    @Test
    void addShouldReturnSumOfNumbers() {
        assert 10 == additionService.add([2,3,4,1])
    }

}
