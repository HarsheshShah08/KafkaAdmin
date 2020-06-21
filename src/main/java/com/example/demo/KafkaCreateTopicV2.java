package com.example.demo;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Map;

public class KafkaCreateTopicV2 {

	public static void main(String[] args) {

		Admin admin = Admin.create(
				Map.of("bootstrap.servers", "localhost:9092")
		);
		try {
			NewTopic newTopic = new NewTopic("MySecondTopicForDemo", 2, (short) 1);
			admin.createTopics(List.of(newTopic))
				.all()
				.get();
		
			printAllTopics(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		admin.close();
	}

	static void printAllTopics(Admin client) throws Exception {
		var topics = client.listTopics().names().get();
		System.out.println("Topics in the cluster:");
		topics.forEach(System.out:: println);
		System.out.println();
	}


}


