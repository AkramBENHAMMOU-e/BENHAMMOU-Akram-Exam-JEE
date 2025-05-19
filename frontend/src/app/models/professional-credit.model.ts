import { Credit } from './credit.model';

export interface ProfessionalCredit extends Credit {
  reason: string;
  companyName: string;
}
