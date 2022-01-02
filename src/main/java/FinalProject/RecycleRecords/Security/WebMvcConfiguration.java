package FinalProject.RecycleRecords.Security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/login_error").setViewName("login_error");
        //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    
    //for viewing vinyl cover photos
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path vinylUploadDir = Paths.get("./vinyl-photos");
        String vinylUploadPath = vinylUploadDir.toFile().getAbsolutePath();
        
        registry.addResourceHandler("/vinyl-photos/**").addResourceLocations("file:/" + vinylUploadPath + "/");
    }

}//class