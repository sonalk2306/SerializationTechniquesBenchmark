package com.wells.protosRunner;

import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wells.protoClass.PersonClass;
import com.wells.protoClass.PersonClass.Person;
import com.wells.protoClass.PersonClass.Person.Address;
import com.wells.protoClass.PersonClass.Person.Address.Building;


public class PersonMain {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		
		//Serialization
		
		long serializationStartTime=System.nanoTime();
		Person.Builder builder = Person.newBuilder();
		builder.setName("SOnal").setEmail("sonal.kumar@gmail.com").setAge(20);

		Person.Phone.Builder phoneBuilder = Person.Phone.newBuilder();
		phoneBuilder.setHome("yes").setWork("yes").setFriends("friends");
		Person.Phone phone = phoneBuilder.build();

		Person.Address.Building.Builder buildingBuilder = Person.Address.Building.newBuilder();
		buildingBuilder.setApartment(1);
		buildingBuilder.setFloor(10);
		buildingBuilder.setWing("A");
		Person.Address.Building building1=buildingBuilder.build();
		
		Person.Address.Builder addBuilder = Person.Address.newBuilder();
		addBuilder.setCity("Mathura").setState("UP").addBuilding(building1);
		Person.Address address = addBuilder.build();
		
		
		builder.addPhones(phone);
		builder.addAdds(address);
		builder.build();

		Person serializedPerson = builder.build();
		
		long serializationEndTime=System.nanoTime();
		
		//Deserialization
		
		long deserializationStartTime = System.nanoTime();

		byte[] personBytes = serializedPerson.toByteArray();

		Person deserializedPerson = Person.parseFrom(personBytes);
		
		String name=deserializedPerson.getName();
		String email= deserializedPerson.getEmail();
		int age=deserializedPerson.getAge();
		
		List<Address> addressList = deserializedPerson.getAddsList();
		String city=addressList.get(0).getCity();
		String state=addressList.get(0).getState();
		
		List<Building> buildingList=addressList.get(0).getBuildingList();
		String wing=buildingList.get(0).getWing();
		int floor=buildingList.get(0).getFloor();
		int apartment=buildingList.get(0).getApartment();
		

		long deserializationEndTime = System.nanoTime();
		long serializationDuration=(serializationEndTime-serializationStartTime)/1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;
		
		System.out.println(">>> Serialization duration in millisec "+ serializationDuration );
		System.out.println(">>> Deserialization duration in millisec "+ deserializationDuration );
	}
}
