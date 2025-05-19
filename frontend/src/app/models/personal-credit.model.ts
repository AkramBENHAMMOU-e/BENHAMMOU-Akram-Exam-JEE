import { Credit } from './credit.model';

export interface PersonalCredit extends Credit {
  reason: string;
}
