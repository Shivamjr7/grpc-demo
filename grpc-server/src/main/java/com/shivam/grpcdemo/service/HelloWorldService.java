package com.shivam.grpcdemo.service;

import com.grpc.examples.helloworld.GreeterGrpc;
import com.grpc.examples.helloworld.HelloReply;
import com.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        HelloReply response = HelloReply.newBuilder().setMessage("Message rcvd  : "+ request.getName()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
