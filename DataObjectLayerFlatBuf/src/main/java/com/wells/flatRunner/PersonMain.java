package com.wells.flatRunner;

import java.nio.ByteBuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.wells.flatbufClass.Address;
import com.wells.flatbufClass.Building;
import com.wells.flatbufClass.NestedGroup;
import com.wells.flatbufClass.Person;

public class PersonMain {
	public static void main(String[] args) {

		long serializationStartTime = System.nanoTime();
		FlatBufferBuilder builder = new FlatBufferBuilder(0);
		int[] personsOffset = new int[1000];
		for (int i = 0; i < 1000; i++) {
			/// Building Object Start
			int[] buildingOffsets = new int[2];

			// Building 1
			int apartment1Offset = builder.createString("kidzee");
			int floor1Offset = builder.createString("second");
			int wing1Offset = builder.createString("x");

			int building1Offset = Building.createBuilding(builder, apartment1Offset, floor1Offset, wing1Offset);

			// Building 2
			int apartment2Offset = builder.createString("mickey");
			int floor2Offset = builder.createString("third");
			int wing2Offset = builder.createString("y");

			int building2Offset = Building.createBuilding(builder, apartment2Offset, floor2Offset, wing2Offset);

			buildingOffsets[0] = building1Offset;
			buildingOffsets[1] = building2Offset;
			/// Building Object End

			/// Address Object Start
			int[] addressOffsets = new int[2];

			// Address 1
			int state1Offset = builder.createString("Telangana");
			int buildingsAddress1Offset = Address.createBuildingsVector(builder, buildingOffsets);
			int address1Offset = Address.createAddress(builder, state1Offset, buildingsAddress1Offset);

			// Address 2
			int state2Offset = builder.createString("Maharashtra");
			int buildingsAddress2Offset = Address.createBuildingsVector(builder, buildingOffsets);
			int address2Offset = Address.createAddress(builder, state2Offset, buildingsAddress2Offset);

			addressOffsets[0] = address1Offset;
			addressOffsets[1] = address2Offset;

			/// Address Object End

			/// Depth Object Start
			int nameOffset = builder.createString("Sonal");
			int emailOffset = builder.createString("sonal.kumar@wellsfargo.com");
			int addressOffset = Person.createAddressVector(builder, addressOffsets);

			// int depthOffset = Depth.createDepth(builder, nameOffset, emailOffset,
			// addressOffset);
			personsOffset[i] = Person.createPerson(builder, nameOffset, emailOffset, addressOffset);

		}
		int personVectorOffset = NestedGroup.createPersonVector(builder, personsOffset);
		int nestgroupOffset = NestedGroup.createNestedGroup(builder, personVectorOffset);
		builder.finish(nestgroupOffset);

		long serializationEndTime = System.nanoTime();
		long deserializationStartTime = System.nanoTime();
		ByteBuffer byteBuf = builder.dataBuffer();

		NestedGroup nestedGroup = NestedGroup.getRootAsNestedGroup(byteBuf);
		for (int i = 0; i < 1000; i++) {

			nestedGroup.person(i).address(0).buildings(0).apartment();
			nestedGroup.person(i).email();

			nestedGroup.person(i).name();
			for (int j = 0; j < nestedGroup.person(i).addressLength(); j++) {
				for (int k = 0; k < nestedGroup.person(i).address(j).buildingsLength(); k++) {
					nestedGroup.person(i).address(j).buildings(k).apartment();
					nestedGroup.person(i).address(j).buildings(k).floor();
					nestedGroup.person(i).address(j).buildings(k).wing();
					nestedGroup.person(i).address(j).state();
				}
			}

		}
		long deserializationEndTime = System.nanoTime();
		long serializationDuration = (serializationEndTime - serializationStartTime) / 1000000;
		long deserializationDuration = (deserializationEndTime - deserializationStartTime) / 1000000;
		System.out.println(">>> Serialization in millisec " + serializationDuration);
		System.out.println(">>> Deserialization duration in millisec " + deserializationDuration);
	}

}
