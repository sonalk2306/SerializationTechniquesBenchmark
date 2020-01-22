package com.wells.flatRunner;
import java.nio.ByteBuffer;

import com.google.flatbuffers.FlatBufferBuilder;
import com.wells.flatbufClass.MultipleFields;
public class MultipleFieldsMain {
	public static void main(String[] args){
		long serializationStartTime=System.nanoTime();
		FlatBufferBuilder builder=new FlatBufferBuilder(0);
		int aOffset=builder.createString("a");
		int bOffset=builder.createString("b");
		int cOffset=builder.createString("c");
		int dOffset=builder.createString("d");
		int eOffset=builder.createString("e");
		int fOffset=builder.createString("f");
		int gOffset=builder.createString("g");
		int hOffset=builder.createString("h");
		int kOffset=builder.createString("k");
		int rOffset=builder.createString("r");
		
		int buffer=0;
		MultipleFields.startMultipleFields(builder);
		MultipleFields.addA(builder, aOffset);
		MultipleFields.addB(builder, bOffset);
		MultipleFields.addC(builder, cOffset);
		MultipleFields.addD(builder, dOffset);
		MultipleFields.addR(builder, rOffset);
		buffer=MultipleFields.endMultipleFields(builder);
		builder.finish(buffer);
		
		ByteBuffer buf=builder.dataBuffer();
		long serializationEndTime=System.nanoTime();
		long deserializationStartTime = System.nanoTime();
		MultipleFields x=MultipleFields.getRootAsMultipleFields(buf);
		x.a();
		x.r();
		long deserializationEndTime = System.nanoTime();
		long serializationDuration=(serializationEndTime-serializationStartTime)/1000000;
		long deserializationDuration=(deserializationEndTime-deserializationStartTime)/1000000;
		System.out.println(">>> Serialization in millisec "+ serializationDuration );
		System.out.println(">>> Deserialization duration in millisec "+ deserializationDuration);

	}
}
