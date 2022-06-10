import { CatInfoStringDTO } from "./CatInfoDTO";
import { TherapyReportDTO } from "./TherapyReportDTO";

export interface ReportDTO {
    cat: CatInfoStringDTO,
    therapies: TherapyReportDTO[]
}