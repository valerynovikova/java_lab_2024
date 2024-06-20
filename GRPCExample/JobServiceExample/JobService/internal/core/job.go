package core

import "go.mongodb.org/mongo-driver/bson/primitive"

type Job struct {
	Id             primitive.ObjectID `bson:"_id,omitempty"`
	Company        string             `bson:"company,omitempty"`
	WorkExperience int32              `bson:"work_experience,omitempty"`
}
