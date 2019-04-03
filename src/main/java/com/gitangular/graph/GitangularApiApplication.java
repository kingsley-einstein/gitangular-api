package com.gitangular.graph;

import com.gitangular.graph.resolvers.Mutation;
import com.gitangular.graph.resolvers.Query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitangularApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitangularApiApplication.class, args);
	}

	@Bean
	public Mutation mutation() {
		return new Mutation();
	}

	@Bean
	public Query query() {
		return new Query();
	}

}
