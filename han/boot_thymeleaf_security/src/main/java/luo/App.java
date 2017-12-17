package luo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements EmbeddedServletContainerCustomizer
{
    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8090);
    }
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
