package com.wells.protosRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wells.protoClass.PersonClass;
import com.wells.protoClass.NestedGroupClass.NestedGroup;
import com.wells.protoClass.PersonClass.Person;
import com.wells.protoClass.PersonClass.Person.Address;
import com.wells.protoClass.PersonClass.Person.Address.Building;

public class PersonMain {
	public static void main(String[] args) throws IOException {

		// Serialization

		long serializationStartTime = System.nanoTime();
		Person.Builder builder = Person.newBuilder();
		NestedGroup.Builder nestedGroupBuilder = NestedGroup.newBuilder();
		List<Person> person = new ArrayList<Person>();
		for (int i = 0; i < 1000; i++) {
			builder.setName("SOnal").setEmail("sonal.kumar@gmail.com").setAge(20);

			Person.Phone.Builder phoneBuilder = Person.Phone.newBuilder();
			phoneBuilder.setHome("yes").setWork("yes").setFriends("friends");
			Person.Phone phone = phoneBuilder.build();

			Person.Address.Building.Builder buildingBuilder = Person.Address.Building.newBuilder();
			buildingBuilder.setApartment(1);
			buildingBuilder.setFloor(10);
			buildingBuilder.setWing("A");
			Person.Address.Building building1 = buildingBuilder.build();

			Person.Address.Builder addBuilder = Person.Address.newBuilder();
			addBuilder.setCity("Mathura").setState("UP").addBuilding(building1);
			Person.Address address = addBuilder.build();

			builder.addPhones(phone);
			builder.addAdds(address);

			Person serializedPerson = builder.build();
			person.add(serializedPerson);
			// System.out.println(i+" Succeffully written");
		}
		nestedGroupBuilder.addAllPerson(person);
		NestedGroup nestedGroup = nestedGroupBuilder.build();
		long serializationEndTime = System.nanoTime();

		// Deserialization

		long deserializationStartTime = System.nanoTime();

		NestedGroup deserializedGroup = NestedGroup.parseFrom(nestedGroup.toByteArray());
		List<Person> personList = new ArrayList<Person>();
		personList = deserializedGroup.getPersonList();
		for (int i = 0; i < person.size(); i++) {
			List<Address> addressList = personList.get(i).getAddsList();
			personList.get(i).getAge();
			personList.get(i).getName();
			personList.get(i).getEmail();
			for (int j = 0; j < addressList.size(); j++) {
				//addressList.get(j).getCity();
				List<Building> buildingList = addressList.get(j).getBuildingList();

				for (int k = 0; k < buildingList.size(); k++) {
					buildingList.get(k).getApartment();
					/*
					 * buildingList.get(k).getFloor(); buildingList.get(k).getWing();
					 */
				}
			}
		}

		long deserializationEndTime = System.nanoTime();
		long serializationDuration = (serializationEndTime - serializationStartTime) / 1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;

		System.out.println(">>> Serialization duration in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration in millisec " + deserializationDuration);
	}
}
