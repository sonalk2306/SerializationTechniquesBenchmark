package com.wells.protosRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.wells.protoClass.MultipleFieldsClass.MultipleFields;

public class multipleMain {
	public static void main(String[] args) throws Exception {

		// Serialization
		long serialisationStartTime = System.nanoTime();

		MultipleFields.Builder objectBuilder = MultipleFields.newBuilder();
		objectBuilder.setA("a").setAa("aa").setR("r");

		MultipleFields serializedObject = objectBuilder.build();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		output.write(serializedObject.toByteArray());
		long serialisationEndTime = System.nanoTime();

		// Deserialization
		long deserialisationStartTime = System.nanoTime();
		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
		MultipleFields deserializedObject = MultipleFields.parseFrom(input);

		deserializedObject.getR();
		long deserialisationEndTime = System.nanoTime();
		long serializationDuration = (serialisationEndTime - serialisationStartTime) / 1000000;
		long deserializationDuration = (deserialisationEndTime - deserialisationStartTime) / 1000000;
		System.out.println(">>> Serialization duration in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration in millisec " + deserializationDuration);

	}
}
