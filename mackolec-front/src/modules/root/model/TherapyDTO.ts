import { CatInfoStringDTO } from "./CatInfoDTO";
import { ObservedSymptomDTO } from "./ObservedSymptomDTO";

export interface TherapyDTO {
    therapyStrength: string;
    date: number;
    hospitalization: string;
    cat: CatInfoStringDTO;
    catAge: number;
    catWeight: number;
    observedSymptoms: ObservedSymptomDTO[];
    diseaseName: string;
    medicineName:string;
}