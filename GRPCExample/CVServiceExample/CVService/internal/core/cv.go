package core

import "go.mongodb.org/mongo-driver/bson/primitive"

type CV struct {
	Id         primitive.ObjectID `bson:"_id,omitempty"`
	Name       string             `bson:"name,omitempty"`
	Age        int32              `bson:"age,omitempty"`
	HardSkills []string           `bson:"hard_skills,omitempty"`
}
