package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface OAuthRequirementsOrBuilder extends MessageLiteOrBuilder {
    String getCanonicalScopes();

    ByteString getCanonicalScopesBytes();
}
