package service

import (
	"CVService/internal/core"
	"CVService/proto"
	"context"
)

type CVRepository interface {
	ListAll(ctx context.Context) (*[]core.CV, error)
}

type CVService struct {
	proto.CvServiceServer
	CVRepository CVRepository
}

func NewCVService(CVRepository CVRepository) *CVService {
	return &CVService{
		CVRepository: CVRepository,
	}
}

func (service *CVService) ListAll(request *proto.WhoAreYouParams, server proto.CvService_ListAllServer) (err error) {
	CVList, err := service.CVRepository.ListAll(context.Background())

	if err != nil {
		return err
	}

	for _, cv := range *CVList {
		err := server.Send(&proto.Cv{
			Id:     cv.Id.String(),
			User:   cv.Name,
			Age:    cv.Age,
			Skills: cv.HardSkills,
		})
		if err != nil {
			return err
		}
	}

	return nil
}
