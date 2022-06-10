import { Breed } from "./enum/Breed";
import { Gender } from "./enum/Gender";

export interface CatInfoDTO {
    jmbm: string,
    name: string,
    age: number,
    weight: number,
    breed: Breed,
    gender: Gender
}

export interface CatInfoStringDTO {
    jmbm: string,
    name: string,
    age: number,
    weight: number,
    breed: string,
    gender: string
}