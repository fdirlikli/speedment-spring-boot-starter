package com.speedment.spring.boot.autoconfigure;/*
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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Objects;

@ConfigurationProperties(prefix = "speedment", ignoreUnknownFields = false)
public class SpeedmentProperties implements Validator {

  @Value("#{environment.getProperty('password')}")
  private String password;

  @Value("#{environment.getProperty('basePackage')}")
  private String basePackage;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(SpeedmentProperties.class);
  }

  //TODO: Validate the properties before continuing with auto configuration
  @Override
  public void validate(Object target, Errors errors) {
    //  SpeedmentProperties speedmentProperties = (SpeedmentProperties) target;
    //  errors.rejectValue("contextPath", "context.path.null", "Context path cannot be null");
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBasePackage() {
    return basePackage;
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }
}
