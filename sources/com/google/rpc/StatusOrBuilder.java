package com.google.rpc;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface StatusOrBuilder extends MessageLiteOrBuilder {
    int getCode();

    Any getDetails(int i);

    int getDetailsCount();

    List<Any> getDetailsList();

    String getMessage();

    ByteString getMessageBytes();
}
