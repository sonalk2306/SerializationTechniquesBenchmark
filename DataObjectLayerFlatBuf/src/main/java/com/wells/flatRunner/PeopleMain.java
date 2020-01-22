package com.wells.flatRunner;

import java.nio.ByteBuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.wells.flatbufClass.BasicPerson;
import com.wells.flatbufClass.People;
import com.wells.flatbufClass.Person;

public class PeopleMain {
	public static void main(String[] args) {
		long serializationStartTime = System.nanoTime();
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		int[] personsOffset=new int[4];

		int name1Offset=builder.createString("a");
		int email1Offset=builder.createString("z");
		
		personsOffset[0]=BasicPerson.createBasicPerson(builder, name1Offset, email1Offset);
		
		
		int name2Offset=builder.createString("b");
		int email2Offset=builder.createString("y");
		
		personsOffset[1]=BasicPerson.createBasicPerson(builder, name2Offset, email2Offset);
		
		int name3Offset=builder.createString("c");
		int email3Offset=builder.createString("x");
		
		personsOffset[2]=BasicPerson.createBasicPerson(builder, name3Offset, email3Offset);
		
		int name4Offset=builder.createString("d");
		int email4Offset=builder.createString("w");
		
		personsOffset[3]=BasicPerson.createBasicPerson(builder, name4Offset, email4Offset);
		
		int peopleOffset=People.createBasicPersonVector(builder, personsOffset);
		People.startPeople(builder);
		People.addBasicPerson(builder, peopleOffset);
		int offset=People.endPeople(builder);
		builder.finish(offset);
		
		long serializationEndTime = System.nanoTime();
		long deserializationStartTime = System.nanoTime();
		ByteBuffer byteBuf = builder.dataBuffer();

		People people = People.getRootAsPeople(byteBuf);
		System.out.println(people.basicPersonLength());
		
		long deserializationEndTime = System.nanoTime();
		long serializationDuration = (serializationEndTime - serializationStartTime) / 1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;
		System.out.println(">>> Serialization in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration in millisec " + deserializationDuration);
		
		
	}
}
