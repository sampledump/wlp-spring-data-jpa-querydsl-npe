/*
 * Copyright 2018 the original author or authors.
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
package myapp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;

/**
 * @author Mark Paluch
 */
@Configuration
@ComponentScan
@EnableJpaRepositories()
public class MyConfig {

	@Bean
	PlatformTransactionManager transactionManager() {
		return new WebSphereUowTransactionManager();
	}

	@Bean
	EntityManagerFactory entityManagerFactory() throws Exception {

		Context ctx = new InitialContext();
		EntityManager entityManager = (EntityManager) ctx.lookup("java:comp/env/jpasample/entitymanager");

		return entityManager.getEntityManagerFactory();
	}
}
