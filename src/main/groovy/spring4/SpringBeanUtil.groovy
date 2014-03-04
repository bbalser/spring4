package spring4

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
class SpringBeanUtil implements ApplicationContextAware {

    static ApplicationContext context;

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext
    }

    static <T> T getBean(Class<T> clazz) {
        context.getBean(clazz)
    }
}
