package mongo

import (
	"JobService/internal/core"
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"time"
)

type JobRepository struct {
	collection *mongo.Collection
}

func NewJobRepository(collection *mongo.Collection) *JobRepository {
	return &JobRepository{collection: collection}
}

func (repository *JobRepository) ListAll(ctx context.Context) (*[]core.Job, error) {
	ctxTimeout, cancel := context.WithTimeout(ctx, time.Second*5)
	defer cancel()

	JobChannel := make(chan *[]core.Job)
	var err error

	go func() {
		err = repository.ListAllInternal(ctxTimeout, JobChannel)
	}()

	if err != nil {
		return nil, err
	}

	var JobList *[]core.Job

	select {
	case <-ctxTimeout.Done():
		break
	case JobList = <-JobChannel:
	}

	return JobList, nil
}

func (repository *JobRepository) ListAllInternal(ctx context.Context, channel chan<- *[]core.Job) (err error) {

	queryResult, err := repository.collection.Find(ctx, bson.D{})

	if err != nil {
		return err
	}

	Jobs := &[]core.Job{}
	err = queryResult.All(ctx, Jobs)

	if err != nil {
		return err
	}

	channel <- Jobs

	return nil
}
