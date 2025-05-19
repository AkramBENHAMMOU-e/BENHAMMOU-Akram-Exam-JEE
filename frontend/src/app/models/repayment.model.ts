import { RepaymentType } from './enums/repayment-type.enum';

export interface Repayment {
  id?: number;
  date: Date;
  amount: number;
  type: RepaymentType;
  creditId: number;
}
