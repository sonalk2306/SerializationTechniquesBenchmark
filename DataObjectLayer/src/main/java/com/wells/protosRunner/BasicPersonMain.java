package com.wells.protosRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.wells.protoClass.BasicPersonClass.BasicPerson;

public class BasicPersonMain {

	public static void main(String[] args) throws Exception {

		long serializationStartTime = System.nanoTime();
		
		BasicPerson.Builder personBuilder = BasicPerson.newBuilder();
		ByteArrayOutputStream singleObjectOutputStream = new ByteArrayOutputStream();
		BasicPerson serializedPerson1 = personBuilder.setName("Name").setAge(20).setEmail("John@xyz.com").build();
		singleObjectOutputStream.write(serializedPerson1.toByteArray());
		
		long serializationEndTime = System.nanoTime();
		
		long deserializationStartTime=System.nanoTime();
		
		ByteArrayInputStream singleObjectInputSteam = new ByteArrayInputStream(singleObjectOutputStream.toByteArray());
		BasicPerson deserializedPerson1 = BasicPerson.parseFrom(singleObjectInputSteam);
		String name=deserializedPerson1.getName();
		int age=deserializedPerson1.getAge();
		String email=deserializedPerson1.getEmail();
		long deserializationEndTime=System.nanoTime();
		long serializationDuration=(serializationEndTime-serializationStartTime)/1000000;
		long deserializationDuration=(deserializationEndTime-deserializationStartTime)/1000000;
		System.out.println(">>> Serialization duration for single object in millisec "+ serializationDuration );
		System.out.println(">>> Deserialization duration for single object in millisec "+ deserializationDuration );

		long startTime1=System.nanoTime();
		
		ByteArrayOutputStream multipleObjectOutputStream = new ByteArrayOutputStream();
		for (int i = 0; i < 5000000; i++) {
			BasicPerson person2 = personBuilder.setName("Name").setAge(20).setEmail("John@xyz.com").build();

			multipleObjectOutputStream.write(person2.toByteArray());
		}
		long endTime1 = System.nanoTime();
		long startTime2=System.nanoTime();
	
		ByteArrayInputStream multipleObjectInputStream = new ByteArrayInputStream(multipleObjectOutputStream.toByteArray());

		BasicPerson deserializedPerson = BasicPerson.parseFrom(multipleObjectInputStream);

		long endTime2 = System.nanoTime();
		long duration1 = ((endTime1 - startTime1) / 1000000);
		long duration2 = ((endTime2 - startTime2) / 1000000);
		
		System.out.println(">>> Serialization duration for multiple object in millisec "+ duration1 );
		System.out.println(">>> Deserialization duration for multiple object in millisec "+ duration2);

	}
}
