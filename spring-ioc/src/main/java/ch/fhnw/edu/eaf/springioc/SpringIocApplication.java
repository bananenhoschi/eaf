package ch.fhnw.edu.eaf.springioc;

import edu.spring.domain.renderer.MessageRenderer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class SpringIocApplication {

    public static void main(String[] args) {

        BeanFactory factory = getBeanFactory();
        MessageRenderer mr = (MessageRenderer) factory.getBean("renderer");
        mr.render();

    }


    private static BeanFactory getBeanFactory()  {
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("/spring/helloConfig.xml"));
        return factory;
    }
}
