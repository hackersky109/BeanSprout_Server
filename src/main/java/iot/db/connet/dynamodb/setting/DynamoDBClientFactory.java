package iot.db.connet.dynamodb.setting;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import iot.utils.PropertiesLoader;

public class DynamoDBClientFactory {
	private static AmazonDynamoDBClient client;
	private static BasicAWSCredentials awsCreds;
	
	public static AmazonDynamoDBClient getDynamoDBClient(){
		if (client==null)
			return createDynamoDBClientFactory();
		return client;
	}
	
	private static AmazonDynamoDBClient createDynamoDBClientFactory(){
		awsCreds = new BasicAWSCredentials(PropertiesLoader.getProperty("iot.aws.accesskeyid"), PropertiesLoader.getProperty("iot.aws.secretkeyid"));
		client = new AmazonDynamoDBClient(
				new AWSStaticCredentialsProvider(awsCreds));
		client.setEndpoint("http://dynamodb.ap-northeast-1.amazonaws.com");
		return client;
	}
	
}