package main

import (
	"JobService/internal/config"
	"JobService/internal/rep/mongo"
	"JobService/internal/service"
	"JobService/proto"
	"context"
	"fmt"
	"github.com/spf13/viper"
	"google.golang.org/grpc"
	"log"
	"net"
)

var (
	port = "5002"
)

func main() {
	ctx := context.Background()

	err := setupViper()

	tryEnvironmentalVariables := false

	if err != nil {
		log.Printf("error reading yml file: %v", err)
		tryEnvironmentalVariables = true
	}

	addr := fmt.Sprintf(":%s", port)
	lis, err := net.Listen("tcp", addr)

	if err != nil {
		log.Fatalf("error starting tcp listener: %v", err)
	}

	mongoDataBase, err := config.SetupMongoDataBase(ctx, tryEnvironmentalVariables)

	if err != nil {
		log.Fatalf("error starting mongo: %v", err)
	}

	JobRepository := mongo.NewJobRepository(mongoDataBase.Collection("jobs"))
	JobService := service.NewJobService(JobRepository)

	grpcServer := grpc.NewServer()

	proto.RegisterJobServiceServer(grpcServer, JobService)

	log.Printf("gRPC stated at %v\n", port)

	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("error statring gRPC: %v", err)
	}

}

func setupViper() error {
	viper.AddConfigPath("configs")
	viper.SetConfigName("config")

	if err := viper.ReadInConfig(); err != nil {
		return err
	}

	return nil
}
