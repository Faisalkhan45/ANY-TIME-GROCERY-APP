package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ListDocumentsResponseOrBuilder extends MessageLiteOrBuilder {
    Document getDocuments(int i);

    int getDocumentsCount();

    List<Document> getDocumentsList();

    String getNextPageToken();

    ByteString getNextPageTokenBytes();
}
