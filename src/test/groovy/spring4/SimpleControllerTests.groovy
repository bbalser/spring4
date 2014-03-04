package spring4

import config.WebAppConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import javax.transaction.Transactional

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = WebAppConfig)
@WebAppConfiguration
@Transactional
class SimpleControllerTests {

    @Autowired
    WebApplicationContext wac
    MockMvc mockMvc

    @Before
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    @Test
    void add() {
        mockMvc.perform(post("/add").accept(MediaType.TEXT_PLAIN).contentType(MediaType.APPLICATION_JSON).content('{"numbers":[1,2,3]}'))
            .andExpect(status().isOk())
            .andExpect(content().string('6'))
    }


}
