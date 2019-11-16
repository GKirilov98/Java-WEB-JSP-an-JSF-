package meTube2.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfig {
    @Produces
    public ModelMapper modelMapper(){return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("me_tube2")
                .createEntityManager();
    }
}
