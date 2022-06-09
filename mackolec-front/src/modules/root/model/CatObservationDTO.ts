import { CatInfoDTO } from "./CatInfoDTO";
import { SymptomDTO } from "./SymptomDTO";

export interface CatObservationDTO {
    observedSymptoms: SymptomDTO[],
    catInfo: CatInfoDTO
}