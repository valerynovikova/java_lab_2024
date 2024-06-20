package service

import (
	"JobService/internal/core"
	"JobService/proto"
	"context"
)

type JobRepository interface {
	ListAll(ctx context.Context) (*[]core.Job, error)
}

type JobService struct {
	proto.JobServiceServer
	JobRepository JobRepository
}

func NewJobService(JobRepository JobRepository) *JobService {
	return &JobService{
		JobRepository: JobRepository,
	}
}

func (service *JobService) ListAll(request *proto.WhoAreYouParams, server proto.JobService_ListAllServer) (err error) {
	JobList, err := service.JobRepository.ListAll(context.Background())

	if err != nil {
		return err
	}

	for _, Job := range *JobList {
		err := server.Send(&proto.Job{
			Id:           Job.Id.String(),
			Organization: Job.Company,
			MinUserAge:   Job.WorkExperience,
		})
		if err != nil {
			return err
		}
	}

	return nil
}
