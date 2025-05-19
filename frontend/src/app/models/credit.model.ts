import { CreditStatus } from './enums/credit-status.enum';

export interface Credit {
  id?: number;
  requestDate: Date;
  status: CreditStatus;
  acceptanceDate?: Date;
  amount: number;
  repaymentDuration: number;
  interestRate: number;
  clientId: number;
  repaymentIds?: number[];
}
