package br.una.vetnow.persistencia;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

/**
 * Configurações para acessar o AWS DynamoDB
 * <br>
 * Para rodar, antes sete a seguinte variável de ambiente:
 * -Dspring.config.location=C:/Users/jhcru/sdm/
 * <br>
 * Neste diretório, criar um arquivo application.properties contendo as
 * seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * 
 * @author jhcru
 *
 */
@Configuration
@EnableDynamoDBRepositories(basePackageClasses = { UsuarioRepository.class })
public class DynamoDBConfig {

	// @Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey = "AKIASEHXHLY2VJ7MPIJ6";

	// @Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey = "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";

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.US_EAST_1).build();
	}

}