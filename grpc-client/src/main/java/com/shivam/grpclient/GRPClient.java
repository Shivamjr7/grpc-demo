package com.shivam.grpclient;

import com.grpc.examples.helloworld.GreeterGrpc;
import com.grpc.examples.helloworld.HelloReply;
import com.grpc.examples.helloworld.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@SpringBootApplication
public class GRPClient {

    public static void main(String[] args) {
        SpringApplication.run(DemoClientRunner.class, args);
    }

    @Component
    public static class DemoClientRunner implements CommandLineRunner {

        private static final Logger LOG = LoggerFactory.getLogger(DemoClientRunner.class);

        @Value("${host:localhost}")
        private String host;

        @Value("${port:9090}")
        private int port;

        public void run(String... strings) throws Exception {
            String name = "Hello";
            ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
            GreeterGrpc.GreeterBlockingStub rpc = GreeterGrpc.newBlockingStub(channel);
            HelloRequest request = HelloRequest.newBuilder().setName(name).build();
            HelloReply response = rpc.sayHello(request);
            System.out.println(response);
            channel.shutdownNow();
        }
    }

}
