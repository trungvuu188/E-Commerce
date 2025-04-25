package com.e_commerce.grocery_mart.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "dmh9icxvu");
        config.put("api_key", "714527356323454");
        config.put("api_secret", "2yxW_yKp60K5CTLlNIx4VQN3blo");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
