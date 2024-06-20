package mongo

import (
	"context"
	"fmt"
	"gRPC_Example/internal/core"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"time"
)

type UserRepository struct {
	collection *mongo.Collection
}

func NewUserRepository(collection *mongo.Collection) *UserRepository {
	return &UserRepository{collection: collection}
}

func (repository *UserRepository) GetById(ctx context.Context, id string) (*core.User, error) {
	ctxTimeout, cancel := context.WithTimeout(ctx, time.Second*5)
	defer cancel()

	userChannel := make(chan *core.User)
	var err error

	go func() {
		err = repository.retrieveUser(ctxTimeout, id, userChannel)
	}()

	if err != nil {
		return nil, err
	}

	var user *core.User

	select {
	case <-ctxTimeout.Done():
		fmt.Println("Processing timeout in Mongo")
		break
	case user = <-userChannel:
		fmt.Println("Finished processing in Mongo")
	}

	return user, nil
}

func (repository *UserRepository) retrieveUser(ctx context.Context, id string, channel chan<- *core.User) (err error) {
	objectId, err := primitive.ObjectIDFromHex(id)

	if err != nil {
		return err
	}

	user := &core.User{}

	//TODO: ошибка после FindOne не ловится
	err = repository.collection.FindOne(ctx, bson.M{"_id": objectId}).Decode(user)

	if err != nil {
		return err
	}

	channel <- user

	return nil
}
