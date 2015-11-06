/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.speedment.spring.boot.autoconfigure;

import com.speedment.Entity;
import com.speedment.Manager;
import com.speedment.Speedment;
import com.speedment.internal.core.runtime.SpeedmentApplicationLifecycle;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Set;

@Configuration
@EnableConfigurationProperties(SpeedmentProperties.class)
public class SpeedmentConfiguration {

    @Autowired
    SpeedmentProperties properties;

    @ConditionalOnProperty(prefix = "speedment",value = {"password","basePackage"})
    @Bean
    public Speedment speedment() throws Exception {
      Reflections reflections = new Reflections(properties.getBasePackage());

      Set<Class<? extends SpeedmentApplicationLifecycle>> speedmentApplications = reflections.getSubTypesOf(SpeedmentApplicationLifecycle.class);

      Speedment speedment = ((Class<? extends SpeedmentApplicationLifecycle>)speedmentApplications.toArray()[0])
              .newInstance()
              .withPassword(properties.getPassword())
              .build();

        //TODO: Below code block can be used to create the Manager instances as spring beans
       /* Set<Class<? extends Entity>> entities = reflections.getSubTypesOf(Entity.class);
        List<Manager> interfaces = entities.stream()
              .filter(Class::isInterface)
              .map(i -> speedment.managerOf(i))
              .collect(Collectors.toList());*/
      return speedment;
    }

}
