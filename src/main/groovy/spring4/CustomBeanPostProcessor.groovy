package spring4

import org.springframework.aop.framework.Advised
import org.springframework.aop.support.AopUtils
import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

import javax.persistence.PersistenceContext
import java.lang.reflect.Field

@Component
class CustomBeanPostProcessor implements ApplicationContextAware, BeanPostProcessor {

    ApplicationContext applicationContext;
    String packageName = "spring4."

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext
    }

    @Override
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean
    }

    @Override
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = unwrap(bean)
        if (isEligible(beanClass)) {
            getFields(beanClass).each { Field field ->
                if (!field.getAnnotation(Autowired) && !field.getAnnotation(PersistenceContext)) {
                    Map beans = applicationContext.getBeansOfType(field.type)
                    if (beans) {
                        bean[field.name] = beans.find { it != null }.value
                    }
                }
            }
        }
        bean
    }

    private Class unwrap(Object proxy) {
        AopUtils.isAopProxy(proxy) ? AopUtils.getTargetClass(proxy) : proxy.getClass()
    }

    private boolean isEligible(beanClass) {
        beanClass.canonicalName?.startsWith(packageName)
    }

    private List<Field> getFields(Class beanClass) {
        beanClass.getDeclaredFields().findAll { !it.synthetic }
    }

}
