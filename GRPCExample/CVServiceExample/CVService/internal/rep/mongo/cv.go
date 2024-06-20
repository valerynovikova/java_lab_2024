package mongo

import (
	"CVService/internal/core"
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"time"
)

type CVRepository struct {
	collection *mongo.Collection
}

func NewCVRepository(collection *mongo.Collection) *CVRepository {
	return &CVRepository{collection: collection}
}

func (repository *CVRepository) ListAll(ctx context.Context) (*[]core.CV, error) {
	ctxTimeout, cancel := context.WithTimeout(ctx, time.Second*5)
	defer cancel()

	CVChannel := make(chan *[]core.CV)
	var err error

	go func() {
		err = repository.ListAllInternal(ctxTimeout, CVChannel)
	}()

	if err != nil {
		return nil, err
	}

	var CVList *[]core.CV

	select {
	case <-ctxTimeout.Done():
		break
	case CVList = <-CVChannel:
	}

	return CVList, nil
}

func (repository *CVRepository) ListAllInternal(ctx context.Context, channel chan<- *[]core.CV) (err error) {

	queryResult, err := repository.collection.Find(ctx, bson.D{})

	if err != nil {
		return err
	}

	cvs := &[]core.CV{}
	err = queryResult.All(ctx, cvs)

	if err != nil {
		return err
	}

	channel <- cvs

	return nil
}
