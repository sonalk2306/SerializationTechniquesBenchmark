package com.wells.protosRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.Descriptors.Descriptor;
import com.wells.protoClass.BasicPersonClass.BasicPerson;
import com.wells.protoClass.GroupClass.Group;

public class BasicPersonMain {

	public static void main(String[] args) throws Exception {

		BasicPerson.Builder personBuilder = BasicPerson.newBuilder();
		Group.Builder groupBuilder = Group.newBuilder();
		long serializationStartTime = System.nanoTime();
		List<BasicPerson> basicPerson = new ArrayList<BasicPerson>();
		for (int i = 0; i < 500000; i++) {

			basicPerson.add(personBuilder.setName("Name").setAge(20).setEmail("John@xyz.com").build());

		}
		groupBuilder.addAllPerson(basicPerson);
		Group serializedGroup = groupBuilder.build();
		byte[] byteArray=serializedGroup.toByteArray();
		// System.out.println(singleObjectOutputStream.size());
		long serializationEndTime = System.nanoTime();

		long deserializationStartTime = System.nanoTime();
		Group deserializedGroup = Group.parseFrom(byteArray);
		List<BasicPerson> deserializedBasicPerson = new ArrayList<BasicPerson>();
		for (int i = 0; i < deserializedBasicPerson.size(); i++) {

			/*
			 * deserializedBasicPerson.get(i).getAge();
			 * deserializedBasicPerson.get(i).getEmail();
			 */

			deserializedBasicPerson.get(i).getName();
		}
		long deserializationEndTime = System.nanoTime();
		long serializationDuration = (serializationEndTime - serializationStartTime) / 1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;
		System.out.println(">>> Serialization duration for single object in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration for single object in millisec " + deserializationDuration);

	}
}
