// automatically generated by the FlatBuffers compiler, do not modify

package com.wells.flatbufClass;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Group extends Table {
  public static Group getRootAsGroup(ByteBuffer _bb) { return getRootAsGroup(_bb, new Group()); }
  public static Group getRootAsGroup(ByteBuffer _bb, Group obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public Group __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public BasicPerson person(int j) { return person(new BasicPerson(), j); }
  public BasicPerson person(BasicPerson obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int personLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createGroup(FlatBufferBuilder builder,
      int personOffset) {
    builder.startObject(1);
    Group.addPerson(builder, personOffset);
    return Group.endGroup(builder);
  }

  public static void startGroup(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addPerson(FlatBufferBuilder builder, int personOffset) { builder.addOffset(0, personOffset, 0); }
  public static int createPersonVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPersonVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endGroup(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

