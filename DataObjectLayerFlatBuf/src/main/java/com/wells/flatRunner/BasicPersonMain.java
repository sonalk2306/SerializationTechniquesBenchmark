package com.wells.flatRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;

import com.google.flatbuffers.FlatBufferBuilder;
import com.wells.flatbufClass.BasicPerson;
import com.wells.flatbufClass.Group;

import java.nio.ByteBuffer;
import com.google.flatbuffers.FlatBufferBuilder;

public class BasicPersonMain {
	public static void main(String[] args) throws Exception {

		long serializationStartTime = System.nanoTime();
		int[] personOffset=new int[500000];
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		
		for (int i = 0; i < 500000; i++) {
			int nameOffset = builder.createString("John");
			int emailOffset = builder.createString("John@xyz.com");
			int person=BasicPerson.createBasicPerson(builder, nameOffset, emailOffset);

			personOffset[i] = person;
		}
		int personVectorOffset=Group.createPersonVector(builder, personOffset);
		int groupOffset=Group.createGroup(builder, personVectorOffset);
		builder.finish(groupOffset);
		
		long serializationEndTime = System.nanoTime();
		long deserializationStartTime = System.nanoTime();
		ByteBuffer buffer=builder.dataBuffer();
		Group group=Group.getRootAsGroup(buffer);
		
		for (int i = 0; i < 500000; i++) {
			
			String deserializedEmail = group.person(i).email();
			 //String deserializedName = group.person(i).name(); 
		}

		long deserializationEndTime = System.nanoTime();
		long serializationDuration = (serializationEndTime - serializationStartTime) / 1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;
		System.out.println(">>> Serialization in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration in millisec " + deserializationDuration);

	}
}
