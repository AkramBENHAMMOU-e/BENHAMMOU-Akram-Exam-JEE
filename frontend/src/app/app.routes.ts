import { Routes } from '@angular/router';
import { ClientListComponent } from './components/clients/client-list.component';
import { ClientFormComponent } from './components/clients/client-form/client-form.component';
import { PersonalCreditListComponent } from './components/credits/personal/personal-credit-list.component';
import { PersonalCreditFormComponent } from './components/credits/personal/personal-credit-form/personal-credit-form.component';
import { RealEstateCreditListComponent } from './components/credits/real-estate/real-estate-credit-list.component';
import { RealEstateCreditFormComponent } from './components/credits/real-estate/real-estate-credit-form/real-estate-credit-form.component';
import { ProfessionalCreditListComponent } from './components/credits/professional/professional-credit-list.component';
import { ProfessionalCreditFormComponent } from './components/credits/professional/professional-credit-form/professional-credit-form.component';
import { RepaymentListComponent } from './components/repayments/repayment-list.component';
import { RepaymentFormComponent } from './components/repayments/repayment-form/repayment-form.component';

export const routes: Routes = [
  { path: '', redirectTo: '/clients', pathMatch: 'full' },

  { path: 'clients', component: ClientListComponent },
  { path: 'clients/new', component: ClientFormComponent },
  { path: 'clients/:id', component: ClientListComponent },
  { path: 'clients/:id/edit', component: ClientFormComponent },
  { path: 'clients/:id/credits', component: ClientListComponent },

  { path: 'personal-credits', component: PersonalCreditListComponent },
  { path: 'personal-credits/new', component: PersonalCreditFormComponent },
  { path: 'personal-credits/:id', component: PersonalCreditListComponent },
  { path: 'personal-credits/:id/edit', component: PersonalCreditFormComponent },


  { path: 'real-estate-credits', component: RealEstateCreditListComponent },
  { path: 'real-estate-credits/new', component: RealEstateCreditFormComponent },
  { path: 'real-estate-credits/:id', component: RealEstateCreditListComponent },
  { path: 'real-estate-credits/:id/edit', component: RealEstateCreditFormComponent },

  { path: 'professional-credits', component: ProfessionalCreditListComponent },
  { path: 'professional-credits/new', component: ProfessionalCreditFormComponent },
  { path: 'professional-credits/:id', component: ProfessionalCreditListComponent },
  { path: 'professional-credits/:id/edit', component: ProfessionalCreditFormComponent },

  // Repayment routes
  { path: 'credits/:creditId/repayments', component: RepaymentListComponent },
  { path: 'credits/:creditId/repayments/new', component: RepaymentFormComponent },
  { path: 'credits/:creditId/repayments/:id/edit', component: RepaymentFormComponent },

  { path: '**', redirectTo: '/clients' }
];
