package com.wells.flatRunner;
import java.nio.ByteBuffer;
import com.google.flatbuffers.FlatBufferBuilder;
import com.wells.flatbufClass.BasicPerson;

import java.nio.ByteBuffer;
import com.google.flatbuffers.FlatBufferBuilder;

public class BasicPersonMain {
	public static void main(String[] args) {
		
		long serializationStartTime=System.nanoTime();
		
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		
		int nameOffset = builder.createString("John");
		int ageOffset = 20;
		int emailOffset = builder.createString("John@xyz.com");
		int person1Offset = 0;

		person1Offset=BasicPerson.createBasicPerson(builder, nameOffset, emailOffset);
		builder.finish(person1Offset);

		long serializationEndTime=System.nanoTime();
		
		long deserializationStartTime = System.nanoTime();
		
		ByteBuffer buf = builder.dataBuffer();
		BasicPerson deserializedPerson = BasicPerson.getRootAsBasicPerson(buf);
		String deserializedEmail=deserializedPerson.email();
		String deserializedName=deserializedPerson.name();
		 
		long deserializationEndTime = System.nanoTime();
		long serializationDuration=(serializationEndTime-serializationStartTime)/1000000;
		long deserializationDuration=(deserializationEndTime-deserializationStartTime)/1000000;
		System.out.println(">>> Serialization in millisec "+ serializationDuration );
		System.out.println(">>> Deserialization duration in millisec "+ deserializationDuration);

	}
}


