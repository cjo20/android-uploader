// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: /Users/klee/Projects/Nightscout/android-uploader/core/src/main/java/com/nightscout/core/protobuf/G4Download.proto
package com.nightscout.core.protobuf;

import com.squareup.wire.ProtoEnum;

public enum GlucoseUnit
        implements ProtoEnum {
    MGDL(0),
    MMOL(1);

    private final int value;

    private GlucoseUnit(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}